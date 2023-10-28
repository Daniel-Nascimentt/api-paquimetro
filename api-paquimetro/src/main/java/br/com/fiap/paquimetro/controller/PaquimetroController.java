package br.com.fiap.paquimetro.controller;

import br.com.fiap.paquimetro.dto.request.PaquimetroPagamentoRequest;
import br.com.fiap.paquimetro.dto.request.PaquimetroRequest;
import br.com.fiap.paquimetro.dto.response.PaquimetroResponse;
import br.com.fiap.paquimetro.exception.DocNotFoundException;
import br.com.fiap.paquimetro.exception.PagamentoInvalidoException;
import br.com.fiap.paquimetro.service.PaquimetroService;
import com.fasterxml.jackson.core.JsonProcessingException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/paquimetro")
public class PaquimetroController {

    @Autowired
    private PaquimetroService paquimetroService;


    @PostMapping(value = "/iniciar")
    public ResponseEntity<?> iniciarPaquimentro(@RequestBody @Valid PaquimetroRequest request) throws DocNotFoundException, JsonProcessingException {
        return ResponseEntity.status(HttpStatus.CREATED).body(paquimetroService.iniciarPaquimetro(request));
    }


    @PostMapping(value = "/finalizar/{paquimetroId}")
    public ResponseEntity<?> finalizarPaquimentro(@PathVariable String paquimetroId,  @RequestBody @Valid PaquimetroRequest request) throws DocNotFoundException {
        return ResponseEntity.ok(paquimetroService.finalizarEstacionamento(paquimetroId, request));
    }

    @PostMapping(value = "/pagar/{paquimetroId}")
    public ResponseEntity<?> pagarPaquimetro(@PathVariable String paquimetroId, @RequestBody @Valid PaquimetroPagamentoRequest request) throws DocNotFoundException, JsonProcessingException, PagamentoInvalidoException {
        paquimetroService.pagar(paquimetroId, request);
        return ResponseEntity.ok("Pagamento efetuado, em breve enviaremos o recibo no seu e-mail de cadastro");
    }

}
