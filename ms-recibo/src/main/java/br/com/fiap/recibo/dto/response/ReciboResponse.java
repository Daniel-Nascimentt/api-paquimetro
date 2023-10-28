package br.com.fiap.recibo.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
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
