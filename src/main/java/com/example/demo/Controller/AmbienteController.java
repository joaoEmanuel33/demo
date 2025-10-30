package com.example.demo.Controller;

import com.example.demo.DTO.AmbienteDTO;
import com.example.demo.Service.AmbienteService;

public class AmbienteController extends BaseController<AmbienteDTO>{

    protected AmbienteController(AmbienteService service){
        super(service);
    }

}
