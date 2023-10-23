package br.com.fiap.paquimetro.service;

import br.com.fiap.paquimetro.repository.ReciboRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReciboService {

    @Autowired
    private ReciboRepository reciboRepository;

}
