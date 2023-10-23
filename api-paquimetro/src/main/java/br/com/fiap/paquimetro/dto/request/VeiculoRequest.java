package br.com.fiap.paquimetro.dto.request;

import br.com.fiap.paquimetro.dominio.Condutor;
import br.com.fiap.paquimetro.dominio.Veiculo;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class VeiculoRequest {

    @NotBlank(message = "A marca deve ser preenchida!")
    private String marca;

    @NotBlank(message = "O Modelo deve ser preenchido!")
    private String modelo;

    @NotBlank(message = "A placa deve ser informada!")
    private String placa;

    @NotBlank(message = "O CondutorId precisa ser informado!")
    private String condutorId;

    public Veiculo toDoc(Condutor condutor) {
        return new Veiculo(this.marca, this.modelo, this.placa, condutor);
    }
}
