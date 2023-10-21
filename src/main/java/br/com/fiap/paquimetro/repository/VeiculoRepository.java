package br.com.fiap.paquimetro.repository;

import br.com.fiap.paquimetro.dominio.Condutor;
import br.com.fiap.paquimetro.dominio.Veiculo;
import br.com.fiap.paquimetro.dto.response.VeiculoResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface VeiculoRepository extends MongoRepository<Veiculo, String> {
    Optional<Veiculo> findByPlaca(String placa);

    Page<Veiculo> findAllByCondutor(Condutor condutor, Pageable pageable);

    Optional<Veiculo> findByPlacaAndCondutor(String placa, Condutor condutor);
}
