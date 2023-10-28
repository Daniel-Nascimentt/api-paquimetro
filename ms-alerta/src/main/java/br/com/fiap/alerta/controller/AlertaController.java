package br.com.fiap.alerta.controller;

import br.com.fiap.alerta.dto.response.AlertaResponse;
import br.com.fiap.alerta.service.AlertaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/relatorios")
public class AlertaController {

    @Autowired
    private AlertaService alertaService;

    @GetMapping(value = "/contagem")
    public ResponseEntity<AlertaResponse> obterContagemAlertas(){
        return ResponseEntity.ok(alertaService.obterContagemAlertas());
    }

}
