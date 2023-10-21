package br.com.fiap.paquimetro.dominio;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.Duration;
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

    @DBRef
    private Veiculo veiculo;

    @DBRef
    private Condutor condutor;

    private StatusEstacionado status = StatusEstacionado.ATIVO;

    private BigDecimal valorAPagar;

    private String tempoEstacionado;

    private long periodoHoras;


    public Paquimetro(OpcaoEstacionamento opcaoEstacionamento, Veiculo veiculo, Condutor condutor) {
        this.opcaoEstacionamento = opcaoEstacionamento;
        this.veiculo = veiculo;
        this.condutor = condutor;
    }

    public Paquimetro(long periodoHoras, OpcaoEstacionamento opcaoEstacionamento, Veiculo veiculo, Condutor condutor) {
        this.periodoHoras = periodoHoras;
        this.opcaoEstacionamento = opcaoEstacionamento;
        this.veiculo = veiculo;
        this.condutor = condutor;
    }

    public void finalizar() {
        this.status = StatusEstacionado.FINALIZADO;
        this.fim = LocalDateTime.now();
    }


    public void calcularTempoEValor() {

        Duration duration = Duration.between(this.inicio, this.fim);

        long hours = duration.toHours();
        long minutes = duration.toMinutes() % 60;

        if(this.opcaoEstacionamento.equals(OpcaoEstacionamento.FIXO)){
            this.valorAPagar = this.opcaoEstacionamento.getPreco();
        } else {
            processaValorPHora(hours);
        }

        this.tempoEstacionado = "Tempo estacionado: ".concat(String.valueOf(hours)).concat("HH:").concat(String.valueOf(minutes).concat("mm"));


    }

    private void processaValorPHora(long hours) {

        if(hours > 0) {
            this.valorAPagar = this.opcaoEstacionamento.getPreco().multiply(new BigDecimal(hours)).setScale(2, RoundingMode.HALF_UP);
            return;
        }

        this.valorAPagar = this.opcaoEstacionamento.getPreco();
    }
}
