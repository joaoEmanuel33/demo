package com.example.demo.Service;


import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.DTO.ReservaDTO;
import com.example.demo.entity.Ambiente;
import com.example.demo.entity.Reserva;
import com.example.demo.repository.AmbienteRepository;
import com.example.demo.repository.ReservaRepository;

@Service
public class ReservaService extends BaseService<Reserva, ReservaDTO>{

    private ReservaRepository reservaRepository;

    @Autowired
    AmbienteRepository ambienteRepository;

    protected ReservaService(ReservaRepository repository) {
        super(repository);
        this.reservaRepository = repository;
    }

   public ReservaDTO create(ReservaDTO dto){
      Ambiente ambiente = ambienteRepository.findById(dto.getAmbiente().getId()).orElseThrow(() -> new IllegalStateException("Ambiente não existe"));
      if(!ambiente.isAtivo()){
        throw new IllegalStateException("Não é possível criar a reserva: o ambiente está inativo.");
      }
      boolean disponivel = reservaRepository.temDisponivel(ambiente.getId(), dto.getDataInicio(), dto.getDataFim());
      if(!disponivel){
        throw new IllegalStateException("O ambiente já está ocupado.");
      }

      return super.create(dto);
   }

   public ReservaDTO update(Long id, ReservaDTO dto){
     Reserva reserva = reservaRepository.findById(id).orElseThrow(() -> new IllegalStateException("Reserva não encontrada."));
     if(reserva.getDataInicio().isBefore(LocalDateTime.now())){
        throw new IllegalStateException("Não é possível alterar uma reserva que já iniciou.");
     }
     return super.update(id, dto);
   }

   public void delete(Long id){
    Reserva reserva = reservaRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Reserva não encontrada com id: " + id));
    reserva.setAtivo(false);
    reservaRepository.save(reserva);
     //fazer que cancele a reserva com regstro data de cancelamento
   }
  }
   


