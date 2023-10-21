package br.com.fiap.paquimetro.dto.response;

import br.com.fiap.paquimetro.dominio.Veiculo;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class VeiculoResponse {

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String id;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String marca;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String modelo;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String placa;

    public VeiculoResponse toResponse(Veiculo veiculo) {
        this.id = veiculo.getId();
        this.marca = veiculo.getMarca();
        this.modelo = veiculo.getModelo();
        this.placa = veiculo.getPlaca();
        return this;
    }
}
