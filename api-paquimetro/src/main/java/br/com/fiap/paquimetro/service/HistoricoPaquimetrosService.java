package br.com.fiap.paquimetro.service;

import br.com.fiap.paquimetro.repository.HistoricoPaquimetrosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HistoricoPaquimetrosService {

    @Autowired
    private HistoricoPaquimetrosRepository historicoPaquimetrosRepository;

}
