package br.com.fiap.paquimetro.queue;

import br.com.fiap.paquimetro.dominio.Paquimetro;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.core.Queue;
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


    public void sendEmitirRecibo(Paquimetro paquimetro) throws JsonProcessingException {
        rabbitTemplate.convertAndSend(
                queueEmissaoRecibo().getName(),
                objectMapper.writeValueAsString(paquimetro)
        );
    }

    @Bean
    public Queue queueEmissaoRecibo() {
        return new Queue("emissao-recibo", true);
    }

}
