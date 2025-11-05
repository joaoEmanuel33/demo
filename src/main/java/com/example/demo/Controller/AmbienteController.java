package com.example.demo.Controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.DTO.AmbienteDTO;
import com.example.demo.Service.AmbienteService;

@RestController
@RequestMapping("/ambientes")
public class AmbienteController extends BaseController<AmbienteDTO>{

    private AmbienteService ambienteService;

    protected AmbienteController(AmbienteService service){
        super(service);
        this.ambienteService = service;
    }

    @Override
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        ambienteService.deleteAmbienteSeNaoTiverReservasFuturas(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
