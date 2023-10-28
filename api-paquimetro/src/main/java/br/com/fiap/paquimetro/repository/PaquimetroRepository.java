package br.com.fiap.paquimetro.repository;

import br.com.fiap.paquimetro.dominio.Paquimetro;
import br.com.fiap.paquimetro.dominio.StatusEstacionado;
import br.com.fiap.paquimetro.dominio.Veiculo;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface PaquimetroRepository extends MongoRepository<Paquimetro, String> {
    Optional<Paquimetro> findByIdAndVeiculoAndStatus(String paquimetroId, Veiculo veiculo, StatusEstacionado ativo);

    Optional<Paquimetro> findByIdAndPago(String paquimetroId, boolean b);

    long countByStatus(StatusEstacionado statusEstacionado);
}
