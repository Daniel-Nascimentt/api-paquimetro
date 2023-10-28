package br.com.fiap.alerta.job;

import br.com.fiap.alerta.service.AlertaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class AlertaScheduled {

    // em milesegundos
    private static final long MINUTOS = 2000;

    @Autowired
    private AlertaService alertaService;

    @Scheduled(fixedDelay = MINUTOS)
    public void enviarAlerta(){
        System.out.println("Verificando se há alertas a serem enviados: ");
        alertaService.alertarCondutor();
    }

}
