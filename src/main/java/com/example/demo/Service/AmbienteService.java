package com.example.demo.Service;

import org.springframework.stereotype.Service;

import com.example.demo.DTO.AmbienteDTO;
import com.example.demo.entity.Ambiente;
import com.example.demo.repository.AmbienteRepository;
import com.example.demo.repository.ReservaRepository;


@Service
public class AmbienteService extends BaseService<Ambiente, AmbienteDTO> {

    private final AmbienteRepository ambienteRepository;

    protected AmbienteService(AmbienteRepository ambienteRepository, ReservaRepository reservaRepository) {
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
}
