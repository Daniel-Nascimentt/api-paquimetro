package br.com.fiap.recibo.service;

import br.com.fiap.recibo.client.EmailClient;
import br.com.fiap.recibo.client.request.EmailPaquimetroRequest;
import br.com.fiap.recibo.dominio.Paquimetro;
import br.com.fiap.recibo.dominio.Recibo;
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
        emailClient.enviarEmail(new EmailPaquimetroRequest(recibo.getCondutor().getEmail(), idPaquimetro, corpoEmail));

        recibo.setEnviadoPorEmail(true);
        reciboRepository.save(recibo);

        rabbitMqProducer.sendCancelarAlerta(idPaquimetro);
    }

    private String prepararEmail(Recibo recibo) {

        return "RECIBO DE ESTACIONAMENTO \n"
                .concat("\n")
                .concat("Veículo: ").concat(recibo.getVeiculo().getModelo()).concat(" - Placa: ").concat(recibo.getVeiculo().getPlaca()).concat("\n")
                .concat("Modalidade de estacionamento: ").concat(recibo.getOpcaoEstacionamento().toString()).concat("\n")
                .concat("De: ").concat(recibo.getInicio().format(getPatternFormat())).concat(" - Até: ").concat(recibo.getFim().format(getPatternFormat())).concat("\n")
                .concat("Periodo estacionado: ").concat(recibo.getTempoEstacionado()).concat("\n")
                .concat("Total pago: R$").concat(recibo.getValorTotalPago().setScale(2, RoundingMode.HALF_UP).toString());
    }

    private DateTimeFormatter getPatternFormat() {
        return DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
    }

}
