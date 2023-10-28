package br.com.fiap.recibo.queue;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableRabbit
public class RabbitMqProducer {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private ObjectMapper objectMapper;


    public void sendCancelarAlerta(String idPaquimetro) {
        rabbitTemplate.convertAndSend(
                cancelarAlerta().getName(),
                idPaquimetro
        );
    }

    @Bean
    public Queue cancelarAlerta() {
        return new Queue("cancelar-alerta", true);
    }

}
