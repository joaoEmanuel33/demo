package com.example.demo.Service;

import java.util.ArrayList;
import java.util.List;

import com.example.demo.DTO.AmbienteDTO;
import com.example.demo.DTO.RecursoDTO;
import com.example.demo.DTO.ReservaDTO;
import com.example.demo.entity.Ambiente;
import com.example.demo.entity.Recurso;
import com.example.demo.entity.Reserva;
import com.example.demo.repository.RecursoRepository;

public class RecursoService extends BaseService<Recurso, RecursoDTO>{

    private final RecursoRepository recursoRepository;

     protected RecursoService(RecursoRepository recursoRepository) {
        super(recursoRepository);
        this.recursoRepository = recursoRepository;
    }

    public List<AmbienteDTO> listagem(Long id){
    List<Ambiente> ambientes = recursoRepository.findByAmbienteId(id);
    List<AmbienteDTO> dtos = new ArrayList<>();
    for(Ambiente ambiente : ambientes){
        dtos.add(super.toDto(ambiente));
    }
    return dtos;
    
  }
    
}
