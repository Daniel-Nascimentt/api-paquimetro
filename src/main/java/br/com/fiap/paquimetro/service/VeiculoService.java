package br.com.fiap.paquimetro.service;

import br.com.fiap.paquimetro.dominio.Condutor;
import br.com.fiap.paquimetro.dominio.Veiculo;
import br.com.fiap.paquimetro.dto.request.VeiculoRequest;
import br.com.fiap.paquimetro.dto.request.VeiculoRequestUpdate;
import br.com.fiap.paquimetro.dto.response.VeiculoResponse;
import br.com.fiap.paquimetro.exception.DocNotFoundException;
import br.com.fiap.paquimetro.repository.CondutorRepository;
import br.com.fiap.paquimetro.repository.VeiculoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class VeiculoService {

    @Autowired
    private VeiculoRepository veiculoRepository;

    @Autowired
    private CondutorRepository condutorRepository;

    @Transactional(readOnly = true)
    public VeiculoResponse buscarPorPlaca(String placa) throws DocNotFoundException {

        Veiculo veiculo = veiculoRepository.findByPlaca(placa).orElseThrow(() -> new DocNotFoundException("Veiculo não encontrado!"));

        return new VeiculoResponse().toResponse(veiculo);
    }

    @Transactional(readOnly = true)
    public Page<VeiculoResponse> buscarTodosPorCondutor(String condutorId, Pageable pageable) throws DocNotFoundException {

        Condutor condutor = condutorRepository.findById(condutorId).orElseThrow(() -> new DocNotFoundException("Condutor não encontrado!"));

        Page<Veiculo> pageVeiculo = veiculoRepository.findAllByCondutor(condutor, pageable);

        return pageVeiculo.map(veiculo -> new VeiculoResponse().toResponse(veiculo));
    }

    @Transactional
    public VeiculoResponse cadastrarVeiculo(VeiculoRequest request) throws DocNotFoundException {

        Condutor condutor = condutorRepository.findById(request.getCondutorId()).orElseThrow(() -> new DocNotFoundException("Condutor não encontrado!"));

        Veiculo veiculoSaved = veiculoRepository.save(request.toDoc(condutor));

        return new VeiculoResponse().toResponse(veiculoSaved);
    }

    @Transactional
    public void deletarPorCondutorEPlaca(String condutorId, String placa) throws DocNotFoundException {

        Condutor condutor = condutorRepository.findById(condutorId).orElseThrow(() -> new DocNotFoundException("Condutor não encontrado!"));

        Veiculo veiculo = veiculoRepository.findByPlacaAndCondutor(placa, condutor).orElseThrow(() -> new DocNotFoundException("Veiculo não encontrado!"));

        veiculoRepository.delete(veiculo);
    }

    @Transactional
    public VeiculoResponse atualizarPorCondutorEPlaca(VeiculoRequestUpdate request) throws DocNotFoundException {

        Condutor condutor = condutorRepository.findById(request.getCondutorId()).orElseThrow(() -> new DocNotFoundException("Condutor não encontrado!"));

        Veiculo veiculo = veiculoRepository.findByPlacaAndCondutor(request.getPlaca(), condutor).orElseThrow(() -> new DocNotFoundException("Veiculo não encontrado!"));

        veiculo.update(request);

        veiculoRepository.save(veiculo);

        return new VeiculoResponse().toResponse(veiculo);
    }
}
