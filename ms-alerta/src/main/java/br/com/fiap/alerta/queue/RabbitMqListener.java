package br.com.fiap.alerta.queue;

import br.com.fiap.alerta.dominio.Alerta;
import br.com.fiap.alerta.dominio.StatusAlerta;
import br.com.fiap.alerta.dominio.StatusEstacionado;
import br.com.fiap.alerta.dto.request.AlertaRequest;
import br.com.fiap.alerta.repository.AlertaRepository;
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
    private ObjectMapper objectMapper;

    @Autowired
    private AlertaRepository alertaRepository;

    @RabbitListener(queues = "cancelar-alerta")
    public void darBaixaNoAlerta(@Payload String mensagem){
        Optional<Alerta> possivelAlerta = alertaRepository.findByIdPaquimetroAndStatusPaquimetroAndStatusAlerta(mensagem);

        if(possivelAlerta.isPresent()){
            Alerta alerta = possivelAlerta.get();

            alerta.setStatusAlerta(StatusAlerta.PAQ_ENCERRADO);
            alerta.setStatusPaquimetro(StatusEstacionado.FINALIZADO);

            alertaRepository.save(alerta);

        }

    }

    @RabbitListener(queues = "criar-alerta")
    public void criarAlerta(@Payload String mensagem) throws JsonProcessingException {
        AlertaRequest alertaRequest = objectMapper.readValue(mensagem, AlertaRequest.class);
        Alerta alerta = alertaRequest.toModel();

        alertaRepository.save(alerta);

    }


}
