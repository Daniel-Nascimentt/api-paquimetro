package br.com.fiap.paquimetro.dto.response;

import br.com.fiap.paquimetro.dominio.FormaPagamento;
import br.com.fiap.paquimetro.dominio.Paquimetro;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

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

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private VeiculoResponse veiculo;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<FormaPagamento> formasPagamentosDisponivis;

    public PaquimetroResponse toResponse(Paquimetro paquimetro) {
        this.id = paquimetro.getId();
        this.valorAPagar = paquimetro.getValorAPagar();
        this.tempoEstacionado = paquimetro.getTempoEstacionado();
        this.veiculo = new VeiculoResponse().toResponse(paquimetro.getVeiculo());
        this.formasPagamentosDisponivis = paquimetro.getOpcaoEstacionamento().getFormasPagamentoPermitido();
        return this;
    }
}
