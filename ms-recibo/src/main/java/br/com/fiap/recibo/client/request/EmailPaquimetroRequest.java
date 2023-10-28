package br.com.fiap.recibo.client.request;

import br.com.fiap.recibo.constant.ConstantRecibo;
import br.com.fiap.recibo.dominio.Recibo;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EmailPaquimetroRequest {

    @NotBlank
    private String idPaquimetro;

    @NotBlank
    private String titulo;

    @NotBlank
    private String destinatario;

    @NotBlank
    private String texto;


    public EmailPaquimetroRequest(String destinatario, String idPaquimetro, String corpoEmail) {
        this.idPaquimetro = idPaquimetro;
        this.titulo = ConstantRecibo.TITULO_RECIBO;
        this.destinatario = destinatario;
        this.texto = corpoEmail;
    }
}
