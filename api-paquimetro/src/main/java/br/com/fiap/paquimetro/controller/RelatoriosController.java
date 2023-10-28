package br.com.fiap.paquimetro.controller;

import br.com.fiap.paquimetro.service.RelatorioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/relatorios")
public class RelatoriosController {

    @Autowired
    private RelatorioService relatorioService;

}
