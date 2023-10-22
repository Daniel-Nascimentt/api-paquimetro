package br.com.fiap.paquimetro.dto.request;

import br.com.fiap.paquimetro.dominio.FormaPagamento;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PaquimetroPagamentoRequest {

    @NotNull(message = "A forma de pagamento deve ser válida!")
    private FormaPagamento formaPagamento;

    @Email
    @NotBlank(message = "Por favor, informe o e-mail para que seja enviado o recibo.")
    private String email;

}
