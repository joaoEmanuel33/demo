package com.example.demo.Controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.DTO.AmbienteDTO;
import com.example.demo.DTO.RecursoDTO;
import com.example.demo.Service.AmbienteService;
import com.example.demo.Service.RecursoService;

@RestController
@RequestMapping("/recursos")
public class RecursoController extends BaseController<RecursoDTO>{

    private RecursoService service;
    private AmbienteService ambienteService;

    protected RecursoController(RecursoService service){
       super(service);
       this.service = service;
    }
    @GetMapping("/recursos/{ambienteId}/{recursoId}")
    public RecursoDTO adicionarRecurso(@PathVariable Long ambienteId, @PathVariable Long recursoId) {
        return ambienteService.adicionarRecurso(ambienteId, recursoId);
    
    }
    @GetMapping("/id/{ambienteId}/{recursoId}")
    public RecursoDTO existeId(@PathVariable Long ambienteId, @PathVariable Long recursoId) {
        return ambienteService.existeId(ambienteId, recursoId); 
    }

    @GetMapping("listagem/{recursoId}")
    public List<AmbienteDTO> listaRecurso(@PathVariable("recursoId") Long recursoId){
        return service.listagem(recursoId);
    }
}
