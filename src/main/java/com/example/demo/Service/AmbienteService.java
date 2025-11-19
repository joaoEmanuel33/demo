package com.example.demo.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.DTO.AmbienteDTO;
import com.example.demo.DTO.RecursoDTO;
import com.example.demo.entity.Ambiente;
import com.example.demo.repository.AmbienteRepository;
import com.example.demo.repository.RecursoRepository;


@Service
public class AmbienteService extends BaseService<Ambiente, AmbienteDTO> {

    private final AmbienteRepository ambienteRepository;

    @Autowired
    RecursoRepository recursoRepository;


    protected AmbienteService(AmbienteRepository ambienteRepository) {
        super(ambienteRepository);
        this.ambienteRepository = ambienteRepository;
    }

    @Override
    public void delete(Long id) {
       boolean reservado = ambienteRepository.temReservaFutura(id);
       if(reservado){
        throw new IllegalStateException("Não é possível deletar o ambiente: existem reservas futuras vinculadas.");
       }
       super.delete(id);
    }

   public RecursoDTO adicionarRecurso(Long ambienteId, Long recursoId) {
        Ambiente ambiente = ambienteRepository.findById(ambienteId)
            .orElseThrow(() -> new IllegalStateException("Ambiente não encontrado."));

        if (ambiente.getRecursos().stream().anyMatch(r -> r.getId().equals(recursoId))) {
            throw new IllegalStateException("Recurso já adicionado ao ambiente.");
        }

        AmbienteDTO recursoDTO = new AmbienteDTO();
        recursoDTO.setId(recursoId);
        ambiente.getRecursos().add(toEntity(recursoDTO));

        ambienteRepository.save(ambiente);

        return recursoDTO;



   }   public RecursoDTO existeId(Long ambienteId, Long recursoId) {
        Ambiente ambiente = ambienteRepository.findById(ambienteId)
            .orElseThrow(() -> new IllegalStateException("Ambiente não encontrado."));

        boolean exists = ambiente.getRecursos().stream()
            .anyMatch(r -> r.getId().equals(recursoId));

        if (!exists) {
            throw new IllegalStateException("Recurso não encontrado no ambiente.");
        }

        RecursoDTO recursoDTO = new RecursoDTO();
        recursoDTO.setId(recursoId);
        return recursoDTO;
   }
            
}
