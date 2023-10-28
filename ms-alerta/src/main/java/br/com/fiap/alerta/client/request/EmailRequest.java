package br.com.fiap.alerta.client.request;

import br.com.fiap.alerta.constant.ConstantRecibo;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EmailRequest {

    private static final String MS_NOME = "MS_ALERTA";

    @NotBlank
    private String titulo;

    @NotBlank
    private String destinatario;

    @NotBlank
    private String texto;

    private String service = MS_NOME;


    public EmailRequest(String destinatario, String corpoEmail) {
        this.titulo = ConstantRecibo.TITULO_ALERTA;
        this.destinatario = destinatario;
        this.texto = corpoEmail;
    }

}
