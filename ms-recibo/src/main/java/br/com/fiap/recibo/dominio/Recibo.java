package br.com.fiap.recibo.dominio;

import com.fasterxml.jackson.annotation.JsonFormat;
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
    private String codigo;

    private String tempoEstacionado;

    @DBRef
    private Veiculo veiculo;

    private OpcaoEstacionamento opcaoEstacionamento;

    private BigDecimal valorTotalPago;

    @DBRef
    private Condutor condutor;

    private long periodoHoras;

    private boolean enviadoPorEmail = false;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime inicio;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime fim;

    public Recibo(Paquimetro paquimetro) {
        this.tempoEstacionado = paquimetro.getTempoEstacionado();
        this.veiculo = paquimetro.getVeiculo();
        this.opcaoEstacionamento = paquimetro.getOpcaoEstacionamento();
        this.valorTotalPago = paquimetro.getValorAPagar();
        this.condutor = paquimetro.getCondutor();
        this.periodoHoras = paquimetro.getPeriodoHoras();
        this.inicio = paquimetro.getInicio();
        this.fim = paquimetro.getFim();
    }


}