package br.com.fiap.recibo.dominio;

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

}
