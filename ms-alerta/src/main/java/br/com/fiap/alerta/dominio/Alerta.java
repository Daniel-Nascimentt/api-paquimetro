package br.com.fiap.alerta.dominio;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document
public class Alerta {

    private static final long MINUTOS_PARA_ALERTA = 45;

    @Id
    private String id;

    private String idPaquimetro;
    private LocalDateTime proximaAlerta;
    private LocalDateTime ultimoAlerta;
    private StatusEstacionado statusPaquimetro;
    private String notificarEmail;
    private StatusAlerta statusAlerta = StatusAlerta.PENDENTE;
    private Long notificacoes = 0L;

    public Alerta(LocalDateTime inicio, StatusEstacionado status, String email, String idPaquimetro) {
        this.proximaAlerta = inicio.plusMinutes(MINUTOS_PARA_ALERTA);
        this.statusPaquimetro = status;
        this.notificarEmail = email;
        this.idPaquimetro = idPaquimetro;
    }

    public void notificarNovamente(LocalDateTime ultimoAlerta){
        this.ultimoAlerta = ultimoAlerta;
        this.proximaAlerta = ultimoAlerta.plusMinutes(MINUTOS_PARA_ALERTA);
        this.notificacoes++;
    }

}
