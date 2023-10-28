package br.com.fiap.paquimetro.service;

import br.com.fiap.paquimetro.client.AlertaClient;
import br.com.fiap.paquimetro.client.ReciboClient;
import br.com.fiap.paquimetro.client.response.AlertaResponse;
import br.com.fiap.paquimetro.client.response.ReciboResponse;
import br.com.fiap.paquimetro.dominio.StatusEstacionado;
import br.com.fiap.paquimetro.dto.response.EstacionamentoResponse;
import br.com.fiap.paquimetro.repository.PaquimetroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RelatorioService {
    @Autowired
    private ReciboClient reciboClient;

    @Autowired
    private AlertaClient alertaClient;

    @Autowired
    private PaquimetroRepository paquimetroRepository;

   public ReciboResponse obterRelatorioRecibos(){
       return reciboClient.obterRelatorio();
   }

    public AlertaResponse obterRelatorioAlertas() {
       return alertaClient.obterRelatorio();
    }

    public EstacionamentoResponse obterRelatorioEstacionamento() {

       long estacionados = paquimetroRepository.countByStatus(StatusEstacionado.ATIVO);
       long finalizados = paquimetroRepository.countByStatus(StatusEstacionado.FINALIZADO);

        return new EstacionamentoResponse(estacionados, finalizados);
    }
}
