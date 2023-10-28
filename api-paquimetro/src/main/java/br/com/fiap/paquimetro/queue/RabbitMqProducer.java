package br.com.fiap.paquimetro.queue;

import br.com.fiap.paquimetro.dominio.Paquimetro;
import br.com.fiap.paquimetro.dto.request.AlertaRequest;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
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

    public void criarAlerta(AlertaRequest request) throws JsonProcessingException {
        rabbitTemplate.convertAndSend(
                queueCriarAlerta().getName(),
                objectMapper.writeValueAsString(request)
        );
    }

    @Bean
    @Qualifier(value = "queueEmissaoRecibo")
    public Queue queueEmissaoRecibo() {
        return new Queue("emissao-recibo", true);
    }

    @Bean
    @Qualifier(value = "queueCriarAlerta")
    public Queue queueCriarAlerta() {
        return new Queue("criar-alerta", true);
    }

}
