package br.com.fiap.paquimetro.repository;

import br.com.fiap.paquimetro.dominio.Condutor;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CondutorRepository extends MongoRepository<Condutor, String> {
}
