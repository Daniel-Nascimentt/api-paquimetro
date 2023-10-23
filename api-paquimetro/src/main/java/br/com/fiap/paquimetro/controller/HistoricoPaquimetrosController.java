package br.com.fiap.paquimetro.controller;

import br.com.fiap.paquimetro.service.HistoricoPaquimetrosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/historico")
public class HistoricoPaquimetrosController {

    @Autowired
    private HistoricoPaquimetrosService historicoPaquimetrosService;

}
