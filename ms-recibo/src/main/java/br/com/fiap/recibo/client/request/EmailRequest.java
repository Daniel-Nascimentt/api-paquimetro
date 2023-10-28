package br.com.fiap.recibo.client.request;

import br.com.fiap.recibo.constant.ConstantRecibo;
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

    private static final String MS_NOME = "MS_RECIBO";

    @NotBlank
    private String idRecibo;

    @NotBlank
    private String titulo;

    @NotBlank
    private String destinatario;

    @NotBlank
    private String texto;

    private String service = MS_NOME;


    public EmailRequest(String destinatario, String idRecibo, String corpoEmail) {
        this.idRecibo = idRecibo;
        this.titulo = ConstantRecibo.TITULO_RECIBO;
        this.destinatario = destinatario;
        this.texto = corpoEmail;
    }
}
