package com.example.demo.Service;

import org.springframework.stereotype.Service;

import com.example.demo.DTO.ReservaDTO;
import com.example.demo.entity.Reserva;
import com.example.demo.repository.ReservaRepository;

@Service
public class ReservaService extends BaseService<Reserva, ReservaDTO>{

    private ReservaRepository reservaRepository;

    protected ReservaService(ReservaRepository repository) {
        super(repository);
        this.reservaRepository = repository;
    }

    @Override
    public ReservaDTO create(ReservaDTO dto) {
        if (dto.getDataInicio().isAfter(dto.getDataFim()) || dto.getDataInicio().equals(dto.getDataFim())) {
            throw new IllegalArgumentException("A data de início deve ser anterior à data de fim.");
        }
        if (reservaRepository.existsOverlappingReservation(dto.getAmbiente().getId(), dto.getDataInicio(), dto.getDataFim())) {
            throw new IllegalStateException("O ambiente já está reservado no período solicitado.");
        }
        return super.create(dto);
    }

}
