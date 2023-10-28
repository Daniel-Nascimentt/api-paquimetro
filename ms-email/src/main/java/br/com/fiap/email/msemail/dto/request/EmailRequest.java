package br.com.fiap.email.msemail.dto.request;

import br.com.fiap.email.msemail.dominio.ServiceRequestSendEmail;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EmailRequest {

    private String idRecibo;

    @NotBlank
    private String titulo;

    @NotBlank
    private String destinatario;

    @NotBlank
    private String texto;

    @NotNull
    private String service;

}
