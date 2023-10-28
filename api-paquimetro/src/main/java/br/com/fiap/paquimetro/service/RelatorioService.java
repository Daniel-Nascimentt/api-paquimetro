package br.com.fiap.paquimetro.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RelatorioService {

    @Autowired
    private HistoricoPaquimetrosRepository historicoPaquimetrosRepository;

}
