package br.com.fiap.paquimetro.dominio;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document
public class Veiculo {

    @Id
    private String id;

    private String marca;

    private String modelo;

    private String placa;

    @DBRef
    private Condutor condutor;

}
