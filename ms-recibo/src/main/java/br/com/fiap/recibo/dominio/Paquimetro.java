package br.com.fiap.recibo.dominio;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document
public class Paquimetro {

    private String id;

    private LocalDateTime inicio = LocalDateTime.now();

    private LocalDateTime fim;

    private OpcaoEstacionamento opcaoEstacionamento;

    private Veiculo veiculo;

    private Condutor condutor;

    private StatusEstacionado status = StatusEstacionado.ATIVO;

    private BigDecimal valorAPagar;

    private String tempoEstacionado;

    private long periodoHoras;

    private boolean pago = false;

}
