package br.com.fiap.paquimetro.client.response;

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
}
