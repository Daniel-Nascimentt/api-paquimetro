package br.com.fiap.recibo.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
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

    public ReciboResponse(long quantidadeRecibos, long quantidadeRecibosPHora, long quantidadeRecibosFixo, long quantidadeRecibosNotificados) {
        this.quantidadeRecibos = quantidadeRecibos;
        this.quantidadeRecibosPHora = quantidadeRecibosPHora;
        this.quantidadeRecibosFixo = quantidadeRecibosFixo;
        this.quantidadeRecibosNotificados = quantidadeRecibosNotificados;
    }
}
