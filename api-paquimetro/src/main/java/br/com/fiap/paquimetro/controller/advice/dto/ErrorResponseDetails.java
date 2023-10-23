package br.com.fiap.paquimetro.controller.advice.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@NoArgsConstructor
@Schema
public class ErrorResponseDetails {

    private String titulo;

    private int status;

    private List<String> detail;

    private long timestamp;

    public ErrorResponseDetails(String titulo, int status, List<String> detail, long timestamp) {
        this.titulo = titulo;
        this.status = status;
        this.detail = detail;
        this.timestamp = timestamp;
    }

}
