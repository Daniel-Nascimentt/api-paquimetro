package br.com.fiap.paquimetro.service;

import br.com.fiap.paquimetro.repository.HistoricoEstacionadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HistoricoEstacionadoService {

    @Autowired
    private HistoricoEstacionadoRepository historicoEstacionadoRepository;

}
