package br.com.fiap.paquimetro.service;

import br.com.fiap.paquimetro.dominio.Condutor;
import br.com.fiap.paquimetro.dto.request.CondutorRequest;
import br.com.fiap.paquimetro.dto.response.CondutorResponse;
import br.com.fiap.paquimetro.exception.DocNotFoundException;
import br.com.fiap.paquimetro.repository.CondutorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CondutorService {

    @Autowired
    private CondutorRepository condutorRepository;

    @Transactional(readOnly = true)
    public CondutorResponse buscarCondutorPorIdentificacao(String identificacao) throws DocNotFoundException {
        Condutor condutorSaved = condutorRepository.findByDocumentoIdentificacao(identificacao).orElseThrow(() -> new DocNotFoundException("Condutor não encontrado!"));
        return new CondutorResponse().toResponseAll(condutorSaved);
    }

    @Transactional
    public void deletarCondutorPorIdentificacao(String identificacao) throws DocNotFoundException {
        Condutor condutor = condutorRepository.findByDocumentoIdentificacao(identificacao).orElseThrow(() -> new DocNotFoundException("Condutor não encontrado!"));
        condutorRepository.delete(condutor);
    }

    @Transactional
    public CondutorResponse autualizarCondutor(CondutorRequest request) throws DocNotFoundException {
        Condutor condutor = condutorRepository.findByDocumentoIdentificacao(request.getDocumentoIdentificacao()).orElseThrow(() -> new DocNotFoundException("Condutor não encontrado!"));
        condutor.update(request);
        condutorRepository.save(condutor);
        return new CondutorResponse().toResponse(condutor);
    }

    @Transactional
    public CondutorResponse criarCondutor(CondutorRequest request) {
        Condutor condutor = condutorRepository.save(request.toDocument());
        return new CondutorResponse().toResponse(condutor);
    }
}
