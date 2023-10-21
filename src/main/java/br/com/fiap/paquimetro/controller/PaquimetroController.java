package br.com.fiap.paquimetro.controller;

import br.com.fiap.paquimetro.service.PaquimetroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/paquimetro")
public class PaquimetroController {

    @Autowired
    private PaquimetroService paquimetroService;

}
