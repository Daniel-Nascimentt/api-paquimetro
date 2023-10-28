package br.com.fiap.paquimetro.dto.request;

import br.com.fiap.paquimetro.dominio.FormaPagamento;
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

}
