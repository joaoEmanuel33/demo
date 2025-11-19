package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.DTO.RecursoDTO;
import com.example.demo.entity.Recurso;

@Repository
public interface RecursoRepository extends BaseRepository<Recurso, Long>{

    @Query("""
            SELECT r FORM Recurso r WHERE r.ambienteId = :ambieneId AND r.recursoId = :recursoId
            """

    )
    List<RecursoDTO> findByAmbienteId(long ambienteId);
    

}
