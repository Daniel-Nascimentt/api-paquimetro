package br.com.fiap.paquimetro.service;

import br.com.fiap.paquimetro.dominio.*;
import br.com.fiap.paquimetro.dto.request.PaquimetroRequest;
import br.com.fiap.paquimetro.dto.response.PaquimetroResponse;
import br.com.fiap.paquimetro.exception.DocNotFoundException;
import br.com.fiap.paquimetro.repository.PaquimetroRepository;
import br.com.fiap.paquimetro.repository.VeiculoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
public class PaquimetroService {

    @Autowired
    private PaquimetroRepository paquimetroRepository;

    @Autowired
    private VeiculoRepository veiculoRepository;

    @Transactional
    public String iniciarPaquimetro(PaquimetroRequest request) throws DocNotFoundException {

        Veiculo veiculo = veiculoRepository.findByPlaca(request.getPlaca()).orElseThrow(() -> new DocNotFoundException("Veiculo não encontrado!"));

        if(OpcaoEstacionamento.FIXO.equals(request.getOpcaoEstacionamento())){
            return iniciarEstacionamentoFixo(veiculo, request);
        }
        return iniciarEstacionamentoHora(veiculo, request);

    }

    @Transactional
    private String iniciarEstacionamentoHora(Veiculo veiculo, PaquimetroRequest request) {
        Condutor condutor = veiculo.getCondutor();

        Paquimetro paquimetroSaved = paquimetroRepository.save(new Paquimetro(LocalDateTime.now(), OpcaoEstacionamento.P_HORA, veiculo, condutor));

        // alguma coisa vai processar isso aqui
        new AlertaEstacionamento(LocalDateTime.now(), condutor, paquimetroSaved.getStatus());

        return paquimetroSaved.getId();

    }

    @Transactional
    private String iniciarEstacionamentoFixo(Veiculo veiculo, PaquimetroRequest request) {
        Condutor condutor = veiculo.getCondutor();

        Paquimetro paquimetroSaved = paquimetroRepository.save(new Paquimetro(request.getInicio(), request.getFim(), OpcaoEstacionamento.FIXO, veiculo, condutor));

        // alguma coisa vai processar isso aqui
        new AlertaEstacionamento(request.getInicio(), condutor, paquimetroSaved.getStatus());

        return paquimetroSaved.getId();
    }

    @Transactional
    public PaquimetroResponse finalizarEstacionamento(String paquimetroId, PaquimetroRequest request) throws DocNotFoundException {

        Veiculo veiculo = veiculoRepository.findByPlaca(request.getPlaca()).orElseThrow(() -> new DocNotFoundException("Veiculo não encontrado!"));

        Paquimetro paquimetro = paquimetroRepository.findByIdAndVeiculoAndStatus(paquimetroId, veiculo, StatusEstacionado.ATIVO).orElseThrow(() -> new DocNotFoundException("PaquimetroId não encontrado!!"));

        paquimetro.finalizar();

        paquimetro.calcularTempoEValor();

        paquimetroRepository.save(paquimetro);

        return new PaquimetroResponse().toResponse(paquimetro);

    }

}
