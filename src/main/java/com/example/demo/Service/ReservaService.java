package com.example.demo.Service;


import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.DTO.ReservaDTO;
import com.example.demo.entity.Ambiente;
import com.example.demo.entity.Reserva;
import com.example.demo.repository.AmbienteRepository;
import com.example.demo.repository.ReservaRepository;

@Service
public class ReservaService extends BaseService<Reserva, ReservaDTO>{

    private ReservaRepository repository;

    @Autowired
    AmbienteRepository ambienteRepository;

    protected ReservaService(ReservaRepository repository) {
        super(repository);
        this.repository = repository;
    }

    public ReservaDTO create(ReservaDTO dto) {
      //Verificar se o ambiente existe
      Ambiente ambiente = ambienteRepository.findById(dto.getAmbiente().getId())
          .orElseThrow(() -> new IllegalStateException("Ambiente não existe."));
      
      //Verificar se o ambiente está ativo
      if(!ambiente.isAtivo()){
          throw new IllegalStateException("O ambiente não está ativo.");
      }        

      //Verficiar se o ambiente tem horários sobrepostos
      boolean disponivel = repository.temDisponivel(
          ambiente.getId(), 
          dto.getDataInicio(), 
          dto.getDataFim()
      );

      if(!disponivel){
          throw new IllegalStateException("O ambiente já está ocupado.");
      }


      return super.create(dto);
  }

  public ReservaDTO update(Long id, ReservaDTO dto){
      Reserva reserva = repository.findById(id).orElseThrow(() -> new IllegalStateException("Reserva não encontrada."));

      if(reserva.getDataInicio().isBefore(LocalDateTime.now())){
          throw new IllegalStateException("Não pode, já iniciou");
      }

      return super.update(id, dto);
  }

  public List<ReservaDTO> listaPorData(String dataInicio, String dataFim){
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    LocalDateTime inicio = LocalDate.parse(dataInicio, formatter).atStartOfDay();
    LocalDateTime fim = LocalDate.parse(dataFim, formatter).atTime(23, 59, 59);
    List<Reserva> reservas = repository.findByData(inicio, fim);
    List<ReservaDTO> dtos = new ArrayList<>();
    for(Reserva reserva : reservas){
        dtos.add(super.toDto(reserva));
    }
    return dtos;

  }

  public List<ReservaDTO> listaPorAmbiente(Long id){
    List<Reserva> reservas = repository.findByAmbienteId(id);
    List<ReservaDTO> dtos = new ArrayList<>();
    for(Reserva reserva : reservas){
        dtos.add(super.toDto(reserva));
    }
    return dtos;
    
  }

  public List<ReservaDTO> listaPorUsuario(String nome){
    List<Reserva> reservas = repository.findByUsuario(nome);
    List<ReservaDTO> dtos = new ArrayList<>();
    for(Reserva reserva : reservas){
        dtos.add(super.toDto(reserva));
    }
    return dtos;
  }

  public List<ReservaDTO> calendarioSemanal(){
    LocalDate hoje = LocalDate.now();
    LocalDateTime inicioSemana = hoje.with(DayOfWeek.MONDAY).atStartOfDay();
    LocalDateTime fimSemana = inicioSemana.plusDays(6).withHour(23).withMinute(59).withSecond(59);
    List<Reserva> reservas = repository.findByData(inicioSemana, fimSemana);
    List<ReservaDTO> dtos = new ArrayList<>();
    for(Reserva reserva : reservas){
        dtos.add(super.toDto(reserva));
    }
    return dtos;
  }


}
   


