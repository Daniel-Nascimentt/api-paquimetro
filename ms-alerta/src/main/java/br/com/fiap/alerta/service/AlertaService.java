package br.com.fiap.alerta.service;

import br.com.fiap.alerta.client.EmailClient;
import br.com.fiap.alerta.client.request.EmailRequest;
import br.com.fiap.alerta.dominio.Alerta;
import br.com.fiap.alerta.dominio.StatusAlerta;
import br.com.fiap.alerta.dto.response.AlertaResponse;
import br.com.fiap.alerta.repository.AlertaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AlertaService {

    @Autowired
    private AlertaRepository alertaRepository;

    @Autowired
    private EmailClient emailClient;

    public void alertarCondutor(){
        List<Alerta> alertas = alertaRepository.findByStatusPaquimetroAndStatusAlertaAndProximaAlertaBetween();
        System.out.println(alertas.size() + " foram encontrados.");

        atualizarAlertas(alertas);

        notificarCondutores(alertas);

    }

    private void atualizarAlertas(List<Alerta> alertas) {
        alertas.forEach(alerta -> {
           alerta.notificarNovamente(alerta.getProximaAlerta());
        });

        alertaRepository.saveAll(alertas);
    }

    @Async
    private void notificarCondutores(List<Alerta> alertas) {
        alertas.forEach(alerta -> emailClient.enviarEmail(new EmailRequest(alerta.getNotificarEmail(), prepararEmail())));
    }

    private static String prepararEmail() {

        return "ATENÇÃO!! \n"
                .concat("\n")
                .concat("Seu prazo de estacionamento está prestes a virar a hora.").concat("\n")
                .concat("Se a modalidade de estacionamento escolhida for por horario fixo, evite dor de cabeça e possiveis encargos por ultrapassar o horario marcado.").concat("\n")
                .concat("\n")
                .concat("Atenciosamente: Equipe FIAP de paquimetro.");
    }

    public AlertaResponse obterContagemAlertas() {

       long alertasAtivos =  alertaRepository.countByStatusAlerta(StatusAlerta.PENDENTE);
       long alertasFinalizados = alertaRepository.countByStatusAlerta(StatusAlerta.PAQ_ENCERRADO);

       return new AlertaResponse(alertasAtivos, alertasFinalizados);

    }
}
