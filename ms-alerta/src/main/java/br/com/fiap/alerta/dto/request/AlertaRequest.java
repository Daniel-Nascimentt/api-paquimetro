package br.com.fiap.alerta.dto.request;

import br.com.fiap.alerta.dominio.Alerta;
import br.com.fiap.alerta.dominio.StatusEstacionado;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AlertaRequest {

    private String idPaquimetro;
    private LocalDateTime inicio;
    private StatusEstacionado status;
    private String notificarEmail;

    public Alerta toModel() {
        return new Alerta(
            this.inicio,this.status, this.notificarEmail, this.idPaquimetro
        );
    }
}
