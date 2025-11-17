package com.example.demo.repository;



import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Reserva;

@Repository
public interface ReservaRepository extends BaseRepository<Reserva, Long>{

    @Query("""
            SELECT COUNT(r) <= 0
            FROM Reserva r
            WHERE r.ambiente.id = :id
            AND r.ativo =  true
            AND r.dataInicio < :dataFim
            AND r.dataFim > :dataInicio
            """)
    boolean temDisponivel(Long id, LocalDateTime dataInicio, LocalDateTime dataFim);

   @Query("""
        SELECT 
             r.dataInicio <= CURRENT_TIMESTAMP 
        
        FROM Reserva r
        WHERE r.id = :id
    """)
   boolean iniciada(Long id);

     @Query("""
               SELECT r 
               FROM Reserva r
               WHERE r.ativo = true
               AND (r.dataInicio BETWEEN :dataInicio AND :dataFim OR :dataFim BETWEEN :dataInicio AND :dataFim)
               """)

     List<Reserva> findByData(LocalDateTime dataInicio, LocalDateTime dataFim);

     @Query("""
               SELECT r 
               FROM Reserva r
               WHERE r.ativo = true
               AND r.ambiente.id = :ambienteId
               """)
     List<Reserva> findByAmbienteId(Long ambienteId);


     @Query("""
               SELECT r 
               FROM Reserva r
               WHERE r.ativo = true
               AND r.usuario = :usuario
               """)
     List<Reserva> findByUsuario(String usuario);


}
