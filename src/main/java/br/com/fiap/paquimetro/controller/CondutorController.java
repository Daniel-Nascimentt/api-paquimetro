package br.com.fiap.paquimetro.controller;

import br.com.fiap.paquimetro.service.CondutorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/condutor")
public class CondutorController {

    @Autowired
    private CondutorService condutorService;

}
