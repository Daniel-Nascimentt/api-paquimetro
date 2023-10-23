package br.com.fiap.paquimetro.controller.advice;

import br.com.fiap.paquimetro.controller.advice.dto.ErrorResponseDetails;
import br.com.fiap.paquimetro.exception.DocNotFoundException;
import br.com.fiap.paquimetro.exception.PagamentoInvalidoException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@org.springframework.web.bind.annotation.ControllerAdvice
public class ControllerAdvice {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ResponseEntity<?> handlerMethodArgumentNotValidException(MethodArgumentNotValidException ex){

        List<String> fieldsError = new ArrayList<>();

        ex.getFieldErrors().forEach(f -> fieldsError.add("PARAMETRO: [" + f.getField() + "] Mensagem: [" + f.getDefaultMessage() + "]"));

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponseDetails(
                "Por favor, verifique se todos os campos foram preenchidos corretamente!",
                HttpStatus.BAD_REQUEST.value(),
                fieldsError,
                new Date().getTime()));
    }

    @ExceptionHandler(DocNotFoundException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public ResponseEntity<?> handlerDocNotFoundException(DocNotFoundException ex){

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponseDetails(
                "Documento não encontrado!!",
                HttpStatus.NOT_FOUND.value(),
                Arrays.asList(ex.getMessage()),
                new Date().getTime()));
    }

    @ExceptionHandler(PagamentoInvalidoException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ResponseEntity<?> handlerPagamentoInvalidoException(PagamentoInvalidoException ex){

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponseDetails(
                "Forma de pagamento inválida!",
                HttpStatus.BAD_REQUEST.value(),
                Arrays.asList(ex.getMessage()),
                new Date().getTime()));
    }

}

