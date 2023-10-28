package br.com.fiap.recibo.service;

import br.com.fiap.recibo.client.EmailClient;
import br.com.fiap.recibo.client.request.EmailRequest;
import br.com.fiap.recibo.dominio.OpcaoEstacionamento;
import br.com.fiap.recibo.dominio.Paquimetro;
import br.com.fiap.recibo.dominio.Recibo;
import br.com.fiap.recibo.dto.response.ReciboResponse;
import br.com.fiap.recibo.queue.RabbitMqProducer;
import br.com.fiap.recibo.repository.ReciboRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.RoundingMode;
import java.time.format.DateTimeFormatter;

@Service
public class ReciboService {

    @Autowired
    private ReciboRepository reciboRepository;

    @Autowired
    private EmailClient emailClient;

    @Autowired
    private RabbitMqProducer rabbitMqProducer;


    @Transactional
    public void save(Paquimetro paquimetro) {

        Recibo recibo = new Recibo(paquimetro);
        Recibo reciboSaved = reciboRepository.save(recibo);

        enviarEmail(reciboSaved, paquimetro.getId());

    }


    public void enviarEmail(Recibo recibo, String idPaquimetro){
        String corpoEmail = prepararEmail(recibo);
        emailClient.enviarEmail(new EmailRequest(recibo.getCondutor().getEmail(), recibo.getCodigo(), corpoEmail));
        rabbitMqProducer.sendCancelarAlerta(idPaquimetro);
    }

    private String prepararEmail(Recibo recibo) {

        return "RECIBO DE ESTACIONAMENTO \n"
                .concat("\n")
                .concat("Veículo: ").concat(recibo.getVeiculo().getModelo()).concat(" - Placa: ").concat(recibo.getVeiculo().getPlaca()).concat("\n")
                .concat("Modalidade de estacionamento: ").concat(recibo.getOpcaoEstacionamento().toString()).concat("\n")
                .concat("De: ").concat(recibo.getInicio().format(getPatternFormat())).concat(" - Até: ").concat(recibo.getFim().format(getPatternFormat())).concat("\n")
                .concat("Periodo estacionado: ").concat(recibo.getTempoEstacionado()).concat("\n")
                .concat("Total pago: ").concat(recibo.getValorTotalPago().setScale(2, RoundingMode.HALF_UP).toString()).concat("\n")
                .concat("\n")
                .concat("Atenciosamente: Equipe FIAP de paquimetro.");
    }

    private DateTimeFormatter getPatternFormat() {
        return DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
    }

    public ReciboResponse obterContagemRecibos() {

        long quantidadeRecibos = reciboRepository.count();
        long quantidadeRecibosPHora = reciboRepository.countByOpcaoEstacionamento(OpcaoEstacionamento.P_HORA);
        long quantidadeRecibosFixo = reciboRepository.countByOpcaoEstacionamento(OpcaoEstacionamento.FIXO);
        long quantidadeRecibosNotificados = reciboRepository.countByEnviadoPorEmail(true);

        return new ReciboResponse(quantidadeRecibos, quantidadeRecibosPHora, quantidadeRecibosFixo, quantidadeRecibosNotificados);
    }
}
