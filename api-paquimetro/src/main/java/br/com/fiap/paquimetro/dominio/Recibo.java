package br.com.fiap.paquimetro.dominio;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document
public class Recibo {

    @Id
    private String id;

    private LocalDateTime periodoEstacionado;

    @DBRef
    private Veiculo veiculo;

    private FormaPagamento formaPagamento;

    private OpcaoEstacionamento opcaoEstacionamento;

    private BigDecimal valorTotalPago;

}
