package br.com.fiap.paquimetro.client;

import br.com.fiap.paquimetro.client.response.AlertaResponse;
import br.com.fiap.paquimetro.client.response.ReciboResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "AlertaClient", url = "${ms.alerta.endpoint}")
public interface AlertaClient {

    @GetMapping(value = "/relatorios/contagem")
    public AlertaResponse obterRelatorio();

}