package br.com.fiap.paquimetro.client.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class AlertaResponse {

    private long alertasAtivos;
    private long alertasFinalizados;

}
