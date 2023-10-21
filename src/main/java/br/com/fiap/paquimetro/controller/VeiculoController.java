package br.com.fiap.paquimetro.controller;

import br.com.fiap.paquimetro.service.VeiculoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/veiculo")
public class VeiculoController {

    @Autowired
    private VeiculoService veiculoService;

}
