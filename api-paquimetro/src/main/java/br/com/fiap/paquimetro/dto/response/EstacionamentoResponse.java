package br.com.fiap.paquimetro.dto.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class EstacionamentoResponse {

    private  long estacionados;
    private  long finalizados;

    public EstacionamentoResponse(long estacionados, long finalizados) {
        this.estacionados = estacionados;
        this.finalizados = finalizados;
    }
}
