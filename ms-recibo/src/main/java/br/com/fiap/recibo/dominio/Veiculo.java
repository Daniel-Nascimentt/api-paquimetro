package br.com.fiap.recibo.dominio;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.index.TextIndexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document
public class Veiculo {

    private String id;

    private String marca;

    private String modelo;

    @TextIndexed
    private String placa;

    @DBRef
    private Condutor condutor;

}
