package br.com.fiap.paquimetro.repository;

import br.com.fiap.paquimetro.dominio.Veiculo;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface VeiculoRepository extends MongoRepository<Veiculo, String> {
}
