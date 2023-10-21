package br.com.fiap.paquimetro.service;

import br.com.fiap.paquimetro.repository.PaquimetroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaquimetroService {

    @Autowired
    private PaquimetroRepository paquimetroRepository;

}
