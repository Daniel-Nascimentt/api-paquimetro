package br.com.fiap.email.msemail.controller;


import br.com.fiap.email.msemail.dto.request.EmailRequest;
import br.com.fiap.email.msemail.service.EmailSerivce;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmailController {

    @Autowired
    private EmailSerivce service;

    @PostMapping(value = "/enviar")
    public ResponseEntity<?> enviarEmail(@RequestBody @Valid EmailRequest request){
        service.enviarEmail(request);
        return ResponseEntity.ok().build();
    }
}
