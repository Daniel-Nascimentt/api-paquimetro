package br.com.fiap.email.msemail.queue;

import com.fasterxml.jackson.core.JsonProcessingException;
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

    public void confirmarEnvioDeEmail(String idRecibo) {
        rabbitTemplate.convertAndSend(
                queueConfirmacaoDeEnvioEmail().getName(),
                idRecibo
        );
    }

    @Bean
    public Queue queueConfirmacaoDeEnvioEmail() {
        return new Queue("email-recibo-enviado", true);
    }

}
