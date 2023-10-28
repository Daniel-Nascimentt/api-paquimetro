package br.com.fiap.paquimetro.dto.request;

import br.com.fiap.paquimetro.dominio.Paquimetro;
import br.com.fiap.paquimetro.dominio.StatusEstacionado;
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

    public AlertaRequest(Paquimetro paquimetro) {
        this.idPaquimetro = paquimetro.getId();
        this.status = paquimetro.getStatus();
        this.notificarEmail = paquimetro.getCondutor().getEmail();
        this.inicio = paquimetro.getInicio();
    }
}
