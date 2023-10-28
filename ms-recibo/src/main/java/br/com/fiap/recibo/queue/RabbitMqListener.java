package br.com.fiap.recibo.queue;

import br.com.fiap.recibo.dominio.Paquimetro;
import br.com.fiap.recibo.dominio.Recibo;
import br.com.fiap.recibo.repository.ReciboRepository;
import br.com.fiap.recibo.service.ReciboService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.handler.annotation.Payload;

import java.util.Optional;

@Configuration
@EnableRabbit
public class RabbitMqListener {

    @Autowired
    private ReciboService reciboService;

    @Autowired
    private ReciboRepository reciboRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @RabbitListener(queues = "emissao-recibo")
    public void processaEnviaRecibo(@Payload String mensagem) throws JsonProcessingException {

        Paquimetro paquimetro = objectMapper.readValue(mensagem, Paquimetro.class);

        reciboService.save(paquimetro);

    }

    @RabbitListener(queues = "email-recibo-enviado")
    public void confirmacaoEnvioEmail(@Payload String mensagem) throws JsonProcessingException {

        Optional<Recibo> possivelRecibo = reciboRepository.findById(mensagem);

        if(possivelRecibo.isPresent()){
            Recibo recibo = possivelRecibo.get();
            recibo.setEnviadoPorEmail(true);
            reciboRepository.save(recibo);
        }

    }


}
