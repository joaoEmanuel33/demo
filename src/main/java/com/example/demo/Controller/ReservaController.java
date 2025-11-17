package com.example.demo.Controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.DTO.ReservaDTO;
import com.example.demo.Service.ReservaService;

@RestController
@RequestMapping("/reservas")
public class ReservaController extends BaseController<ReservaDTO> {
 
    private final ReservaService service;

    protected ReservaController(ReservaService service){
        super(service);
        this.service = service;
    }

  @GetMapping("/por-data/{dataInicio}/{dataFim}")
  public List<ReservaDTO> reservaPorData(@PathVariable("dataInicio") String dataInicio, @PathVariable("dataFim") String dataFim){
     return service.listaPorData(dataInicio, dataFim);
     
  }

  @GetMapping("/por-ambiente/{ambienteId}")
  public List<ReservaDTO> reservaPorAmbiente(@PathVariable("ambienteId") Long ambienteId){
     return service.listaPorAmbiente(ambienteId);
  }

  @GetMapping("/por-usuario/{usuario}")
  public List<ReservaDTO> reservaPorUsuario(@PathVariable("usuario") String usuario){
     return service.listaPorUsuario(usuario);
  }

  @GetMapping("/calendario-semanal")
  public List<ReservaDTO> reservaCalendarioSemanal(){
     return service.calendarioSemanal();
  }
}
