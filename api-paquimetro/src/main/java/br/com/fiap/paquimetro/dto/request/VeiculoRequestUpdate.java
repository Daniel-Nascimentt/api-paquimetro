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
public class VeiculoRequestUpdate {

    private String marca;

    private String modelo;

    private String placa;

    private String condutorId;

}
