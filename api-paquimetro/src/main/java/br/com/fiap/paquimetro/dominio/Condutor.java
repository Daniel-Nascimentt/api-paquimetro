package br.com.fiap.paquimetro.dominio;

import br.com.fiap.paquimetro.dto.request.CondutorRequest;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document
public class Condutor {

    @Id
    private String id;

    private String nome;

    private String documentoIdentificacao;

    private String email;

    private FormaPagamento prefPagamento;

    private String endereco;

    public Condutor(String nome, String documentoIdentificacao, String email, FormaPagamento prefPagamento, String endereco) {
        this.nome = nome;
        this.documentoIdentificacao = documentoIdentificacao;
        this.email = email;
        this.prefPagamento = prefPagamento;
        this.endereco = endereco;
    }

    public void update(CondutorRequest request) {
        this.nome = (request.getNome() != null && !request.getNome().isEmpty()) ? request.getNome() : this.nome;
        this.email = (request.getEmail() != null && !request.getEmail().isEmpty()) ? request.getEmail() : this.email;
        this.endereco = (request.getEndereco() != null && !request.getEndereco().isEmpty()) ? request.getEndereco() : this.endereco;
        this.documentoIdentificacao = (request.getDocumentoIdentificacao() != null && !request.getDocumentoIdentificacao().isEmpty()) ? request.getDocumentoIdentificacao() : this.documentoIdentificacao;
        this.prefPagamento = request.getPrefPagamento() != null ? request.getPrefPagamento() : this.prefPagamento;
    }
}
