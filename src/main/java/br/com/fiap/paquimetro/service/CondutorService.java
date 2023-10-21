package br.com.fiap.paquimetro.service;

import br.com.fiap.paquimetro.repository.CondutorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CondutorService {

    @Autowired
    private CondutorRepository condutorRepository;

}
