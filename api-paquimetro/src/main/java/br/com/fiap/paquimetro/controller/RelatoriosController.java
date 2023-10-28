package br.com.fiap.paquimetro.controller;

import br.com.fiap.paquimetro.client.response.AlertaResponse;
import br.com.fiap.paquimetro.client.response.ReciboResponse;
import br.com.fiap.paquimetro.dto.response.EstacionamentoResponse;
import br.com.fiap.paquimetro.service.RelatorioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/relatorios")
public class RelatoriosController {

    @Autowired
    private RelatorioService relatorioService;

    @GetMapping(value = "/recibos")
    public ResponseEntity<ReciboResponse> obterRelatorioRecibos(){
        return ResponseEntity.ok(relatorioService.obterRelatorioRecibos());
    }

    @GetMapping(value = "/alertas")
    public ResponseEntity<AlertaResponse> obterRelatorioAlertas(){
        return ResponseEntity.ok(relatorioService.obterRelatorioAlertas());
    }

    @GetMapping(value = "/estacionamento")
    public ResponseEntity<EstacionamentoResponse> obterRelatorioEstacionamento(){
        return ResponseEntity.ok(relatorioService.obterRelatorioEstacionamento());
    }

}
