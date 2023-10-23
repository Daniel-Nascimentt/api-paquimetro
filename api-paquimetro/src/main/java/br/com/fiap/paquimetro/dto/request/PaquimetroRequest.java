package br.com.fiap.paquimetro.dto.request;

import br.com.fiap.paquimetro.dominio.OpcaoEstacionamento;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PaquimetroRequest {

    private long periodoHoras;

    @NotBlank(message = "Por favor, informe a placa do seu veiculo!")
    private String placa;

    @NotNull(message = "Por favor, informe uma opção válida de estacionamento!")
    private OpcaoEstacionamento opcaoEstacionamento;

    private LocalDateTime mockData;

}
