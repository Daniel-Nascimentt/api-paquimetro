package br.com.fiap.alerta.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@Schema
public class AlertaResponse {

    private long alertasAtivos;
    private long alertasFinalizados;

    public AlertaResponse(long alertasAtivos, long alertasFinalizados) {
        this.alertasAtivos = alertasAtivos;
        this.alertasFinalizados = alertasFinalizados;
    }
}
