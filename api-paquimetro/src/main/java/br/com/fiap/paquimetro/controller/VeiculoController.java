package br.com.fiap.paquimetro.controller;

import br.com.fiap.paquimetro.dto.request.VeiculoRequest;
import br.com.fiap.paquimetro.dto.request.VeiculoRequestUpdate;
import br.com.fiap.paquimetro.dto.response.VeiculoResponse;
import br.com.fiap.paquimetro.exception.DocNotFoundException;
import br.com.fiap.paquimetro.service.VeiculoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/veiculo")
public class VeiculoController {

    @Autowired
    private VeiculoService veiculoService;

    @GetMapping(value = "/placa/{placa}")
    public ResponseEntity<VeiculoResponse> buscarPorPlaca(@PathVariable String placa) throws DocNotFoundException {
        return ResponseEntity.ok(veiculoService.buscarPorPlaca(placa));
    }

    @GetMapping(value = "/{condutorId}")
    public Page<VeiculoResponse> buscarTodosPorCondutor(@PathVariable String condutorId, Pageable pageable) throws DocNotFoundException {
        return veiculoService.buscarTodosPorCondutor(condutorId, pageable);
    }

    @PostMapping
    public ResponseEntity<VeiculoResponse> cadastrarVeiculo(@RequestBody @Valid VeiculoRequest request) throws DocNotFoundException {
        return ResponseEntity.status(HttpStatus.CREATED).body(veiculoService.cadastrarVeiculo(request));
    }

    @DeleteMapping(value = "/{condutorId}/{placa}")
    public ResponseEntity<?> deletarPorCondutorEPlaca(@PathVariable("condutorId") String condutorId, @PathVariable("placa") String placa) throws DocNotFoundException {
        veiculoService.deletarPorCondutorEPlaca(condutorId, placa);
        return ResponseEntity.ok().build();
    }

    @PutMapping
    public ResponseEntity<VeiculoResponse> atualizarPorCondutorEPlaca(@RequestBody @Valid VeiculoRequestUpdate request) throws DocNotFoundException {
        return ResponseEntity.ok(veiculoService.atualizarPorCondutorEPlaca(request));
    }

}
