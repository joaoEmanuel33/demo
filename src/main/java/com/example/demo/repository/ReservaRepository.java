package com.example.demo.repository;

import java.time.LocalDateTime;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Reserva;

@Repository
public interface ReservaRepository extends BaseRepository<Reserva, Long>{

    @Query("SELECT COUNT(r) > 0 FROM Reserva r WHERE r.ambiente.id = :ambienteId AND r.dataFim > :now AND r.ativo = true")
    boolean hasFutureOrOngoingReservations(Long ambienteId, LocalDateTime now);

    @Query("SELECT COUNT(r) > 0 FROM Reserva r WHERE r.ambiente.id = :ambienteId AND r.ativo = true AND " +
           "((r.dataInicio < :dataFim AND r.dataFim > :dataInicio))")
    boolean existsOverlappingReservation(Long ambienteId, LocalDateTime dataInicio, LocalDateTime dataFim);

}
