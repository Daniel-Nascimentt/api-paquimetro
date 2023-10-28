package br.com.fiap.paquimetro.client.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@Schema
public class ReciboResponse {
    private long quantidadeRecibos;
    private long quantidadeRecibosPHora;
    private long quantidadeRecibosFixo;
    private long quantidadeRecibosNotificados;
}
