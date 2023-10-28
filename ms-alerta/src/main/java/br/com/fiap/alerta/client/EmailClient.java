package br.com.fiap.alerta.client;

import br.com.fiap.alerta.client.request.EmailRequest;
import jakarta.validation.Valid;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "EmailClient", url = "${ms.email.endpoint}", path = "/ms-email")
public interface EmailClient {

    @PostMapping(value = "/enviar")
    void enviarEmail(@RequestBody @Valid EmailRequest request);

}
