package br.com.fiap.paquimetro.controller;

import br.com.fiap.paquimetro.service.HistoricoEstacionadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/historico")
public class HistoricoEstacinamentosController {

    @Autowired
    private HistoricoEstacionadoService historicoEstacionadoService;

}
