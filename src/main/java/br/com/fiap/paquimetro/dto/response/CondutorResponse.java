package br.com.fiap.paquimetro.dto.response;

import br.com.fiap.paquimetro.dominio.Condutor;
import br.com.fiap.paquimetro.dominio.FormaPagamento;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Schema
public class CondutorResponse {

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String id;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String nome;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String documentoIdentificacao;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String email;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private FormaPagamento prefPagamento;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String endereco;

    public CondutorResponse toResponse(Condutor condutorSaved) {
        this.id = condutorSaved.getId();
        this.nome = condutorSaved.getNome();
        this.email = condutorSaved.getEmail();
        return this;
    }

    public CondutorResponse toResponseAll(Condutor condutorSaved) {
        this.id = condutorSaved.getId();
        this.nome = condutorSaved.getNome();
        this.email = condutorSaved.getEmail();
        this.documentoIdentificacao = condutorSaved.getDocumentoIdentificacao();
        this.prefPagamento = condutorSaved.getPrefPagamento();
        return this;
    }
}
