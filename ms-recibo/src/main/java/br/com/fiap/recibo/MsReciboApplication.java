package br.com.fiap.recibo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class MsReciboApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsReciboApplication.class, args);
	}

}
