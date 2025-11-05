package com.example.demo.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.DTO.ReservaDTO;
import com.example.demo.Service.ReservaService;

@RestController
@RequestMapping("/reservas")
public class ReservaController extends BaseController<ReservaDTO> {
 
    protected ReservaController(ReservaService service){
        super(service);
    }


}
