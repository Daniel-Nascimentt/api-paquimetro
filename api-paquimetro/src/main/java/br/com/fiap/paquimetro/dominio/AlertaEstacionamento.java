package br.com.fiap.paquimetro.dominio;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.cglib.core.Local;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AlertaEstacionamento {

    private final long MINUTOS_PARA_ALERTA = 45;

    private LocalDateTime proximaAlerta;
    private LocalDateTime ultimoAlerta;
    private StatusEstacionado status;

    public AlertaEstacionamento(LocalDateTime inicio, Condutor condutor,  StatusEstacionado status) {
        this.proximaAlerta = inicio.plusMinutes(MINUTOS_PARA_ALERTA);
        this.status = status;
    }

    public void notificarNovamente(LocalDateTime ultimoAlerta, StatusEstacionado status){
        this.ultimoAlerta = ultimoAlerta;
        this.proximaAlerta = ultimoAlerta.plusMinutes(MINUTOS_PARA_ALERTA);
    }

}
