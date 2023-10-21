package br.com.fiap.paquimetro.controller;

import br.com.fiap.paquimetro.service.ReciboService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/recibo")
public class ReciboController {

    @Autowired
    private ReciboService reciboService;

}
