package br.com.fiap.paquimetro.dto.request;

import br.com.fiap.paquimetro.dominio.Condutor;
import br.com.fiap.paquimetro.dominio.FormaPagamento;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.br.CNPJ;
import org.hibernate.validator.constraints.br.CPF;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CondutorRequest {

    private String nome;

    @CPF
    @NotBlank(message = "Documento de validação não pode ser Null/Vazio!")
    private String documentoIdentificacao;

    @Email(message = "E-mail invalido!")
    private String email;

    private FormaPagamento prefPagamento;

    private String endereco;

    public Condutor toDocument() {
        return new Condutor(this.nome, this.documentoIdentificacao, this.email, this.prefPagamento, this.endereco);
    }
}
