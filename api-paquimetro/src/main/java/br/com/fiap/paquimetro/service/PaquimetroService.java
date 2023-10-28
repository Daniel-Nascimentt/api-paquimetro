package br.com.fiap.paquimetro.service;

import br.com.fiap.paquimetro.dominio.*;
import br.com.fiap.paquimetro.dto.request.AlertaRequest;
import br.com.fiap.paquimetro.dto.request.PaquimetroPagamentoRequest;
import br.com.fiap.paquimetro.dto.request.PaquimetroRequest;
import br.com.fiap.paquimetro.dto.response.PaquimetroResponse;
import br.com.fiap.paquimetro.exception.DocNotFoundException;
import br.com.fiap.paquimetro.exception.PagamentoInvalidoException;
import br.com.fiap.paquimetro.queue.RabbitMqProducer;
import br.com.fiap.paquimetro.repository.PaquimetroRepository;
import br.com.fiap.paquimetro.repository.VeiculoRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PaquimetroService {

    @Autowired
    private PaquimetroRepository paquimetroRepository;

    @Autowired
    private VeiculoRepository veiculoRepository;

    @Autowired
    private RabbitMqProducer rabbitMq;

    @Transactional
    public String iniciarPaquimetro(PaquimetroRequest request) throws DocNotFoundException, JsonProcessingException {

        Veiculo veiculo = veiculoRepository.findByPlaca(request.getPlaca()).orElseThrow(() -> new DocNotFoundException("Veiculo não encontrado!"));

        if(OpcaoEstacionamento.FIXO.equals(request.getOpcaoEstacionamento())){
            return iniciarEstacionamentoFixo(veiculo, request);
        }
        return iniciarEstacionamentoHora(veiculo, request);

    }

    @Transactional
    private String iniciarEstacionamentoHora(Veiculo veiculo, PaquimetroRequest request) throws JsonProcessingException {
        Condutor condutor = veiculo.getCondutor();

        Paquimetro paquimetroSaved = paquimetroRepository.save(new Paquimetro(OpcaoEstacionamento.P_HORA, veiculo, condutor));

        rabbitMq.criarAlerta(new AlertaRequest(paquimetroSaved));

        return paquimetroSaved.getId();

    }

    @Transactional
    private String iniciarEstacionamentoFixo(Veiculo veiculo, PaquimetroRequest request) throws JsonProcessingException {
        Condutor condutor = veiculo.getCondutor();

        Paquimetro paquimetroSaved = paquimetroRepository.save(new Paquimetro(request.getPeriodoHoras(), OpcaoEstacionamento.FIXO, veiculo, condutor));

        rabbitMq.criarAlerta(new AlertaRequest(paquimetroSaved));

        return paquimetroSaved.getId();
    }

    @Transactional
    public PaquimetroResponse finalizarEstacionamento(String paquimetroId, PaquimetroRequest request) throws DocNotFoundException {

        Veiculo veiculo = veiculoRepository.findByPlaca(request.getPlaca()).orElseThrow(() -> new DocNotFoundException("Veiculo não encontrado!"));

        Paquimetro paquimetro = paquimetroRepository.findByIdAndVeiculoAndStatus(paquimetroId, veiculo, StatusEstacionado.ATIVO).orElseThrow(() -> new DocNotFoundException("PaquimetroId não encontrado!!"));

        paquimetro.finalizar();

        paquimetroRepository.save(paquimetro);

        return new PaquimetroResponse().toResponse(paquimetro);

    }

    @Async
    public void pagar(String paquimetroId, PaquimetroPagamentoRequest request) throws DocNotFoundException, JsonProcessingException, PagamentoInvalidoException {
        Paquimetro paquimetro = paquimetroRepository.findByIdAndPago(paquimetroId, false).orElseThrow(() -> new DocNotFoundException("PaquimetroId não encontrado!!"));
        paquimetro.pagar(request.getFormaPagamento());
        paquimetroRepository.save(paquimetro);

        rabbitMq.sendEmitirRecibo(paquimetro);
    }
}
