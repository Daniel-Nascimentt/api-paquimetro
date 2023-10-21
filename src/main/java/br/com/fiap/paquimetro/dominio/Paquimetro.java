package br.com.fiap.paquimetro.dominio;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document
public class Paquimetro {

    private String id;

    private LocalDateTime inicio;

    private LocalDateTime fim;

    private OpcaoEstacionamento opcaoEstacionamento;

    @DBRef
    private Veiculo veiculo;

    @DBRef
    private Condutor condutor;

}
