package com.example.demo.Service;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import com.example.demo.DTO.AmbienteDTO;
import com.example.demo.entity.Ambiente;
import com.example.demo.repository.AmbienteRepository;
import com.example.demo.repository.ReservaRepository;

import jakarta.transaction.Transactional;

@Service
public class AmbienteService extends BaseService<Ambiente, AmbienteDTO> {

    private final AmbienteRepository ambienteRepository;
    private final ReservaRepository reservaRepository;

    public AmbienteService(AmbienteRepository ambienteRepository, ReservaRepository reservaRepository) {
        super(ambienteRepository);
        this.ambienteRepository = ambienteRepository;
        this.reservaRepository = reservaRepository;
    }

    @Transactional
    public void deleteAmbienteSeNaoTiverReservasFuturas(Long id) {
        boolean hasFutureOrOngoingReservations = reservaRepository.hasFutureOrOngoingReservations(id, LocalDateTime.now());
        if (!hasFutureOrOngoingReservations) {
            delete(id);
        } else {
            throw new IllegalStateException("Não é possível deletar o ambiente: existem reservas futuras ou em andamento vinculadas.");
        }
    }
}
