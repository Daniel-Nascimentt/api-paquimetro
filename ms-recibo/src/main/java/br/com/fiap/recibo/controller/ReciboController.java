package br.com.fiap.recibo.controller;

import br.com.fiap.recibo.dto.response.ReciboResponse;
import br.com.fiap.recibo.service.ReciboService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/relatorios")
public class ReciboController {

    @Autowired
    private ReciboService reciboService;

    @GetMapping(value = "/contagem")
    public ResponseEntity<ReciboResponse> obterContagemRecibos(){
        return ResponseEntity.ok(reciboService.obterContagemRecibos());
    }

}
