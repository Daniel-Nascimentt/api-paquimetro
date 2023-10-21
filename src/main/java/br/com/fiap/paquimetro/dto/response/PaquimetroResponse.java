package br.com.fiap.paquimetro.dto.response;

import br.com.fiap.paquimetro.dominio.Paquimetro;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Schema
public class PaquimetroResponse {

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String id;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private BigDecimal valorAPagar;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String tempoEstacionado;

    private VeiculoResponse veiculo;

    public PaquimetroResponse toResponse(Paquimetro paquimetro) {
        this.id = paquimetro.getId();
        this.valorAPagar = paquimetro.getValorAPagar();
        this.tempoEstacionado = paquimetro.getTempoEstacionado();
        this.veiculo = new VeiculoResponse().toResponse(paquimetro.getVeiculo());
        return this;
    }
}
