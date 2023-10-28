package br.com.fiap.paquimetro;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class ApiPaquimetroApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiPaquimetroApplication.class, args);
	}

}
