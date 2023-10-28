package br.com.fiap.paquimetro.client;

import br.com.fiap.paquimetro.client.response.ReciboResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "ReciboClient", url = "${ms.recibo.endpoint}")
public interface ReciboClient {

    @GetMapping(value = "/relatorios/contagem")
    public ReciboResponse obterRelatorio();

}
