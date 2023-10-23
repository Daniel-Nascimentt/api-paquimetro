package br.com.fiap.paquimetro.controller;

import br.com.fiap.paquimetro.dto.request.CondutorRequest;
import br.com.fiap.paquimetro.dto.response.CondutorResponse;
import br.com.fiap.paquimetro.exception.DocNotFoundException;
import br.com.fiap.paquimetro.service.CondutorService;
import jakarta.validation.Valid;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/condutor")
public class CondutorController {

    @Autowired
    private CondutorService condutorService;

    @GetMapping(value = "/{identificacao}")
    public ResponseEntity<CondutorResponse> buscarCondutorPorIdentificacao(@PathVariable String identificacao) throws DocNotFoundException {
        return ResponseEntity.ok(condutorService.buscarCondutorPorIdentificacao(identificacao));
    }

    @DeleteMapping(value = "/{identificacao}")
    public ResponseEntity<?> deletarCondutorPorIdentificacao(@PathVariable String identificacao) throws DocNotFoundException {
        condutorService.deletarCondutorPorIdentificacao(identificacao);
        return ResponseEntity.ok().build();
    }

    @PutMapping
    public ResponseEntity<CondutorResponse> atualizarCondutor(@RequestBody @Valid CondutorRequest request) throws DocNotFoundException {
        return ResponseEntity.ok(condutorService.autualizarCondutor(request));
    }

    @PostMapping
    public ResponseEntity<CondutorResponse> criarCondutor(@RequestBody @Valid CondutorRequest request){
        return ResponseEntity.status(HttpStatus.CREATED).body(condutorService.criarCondutor(request));
    }
}
