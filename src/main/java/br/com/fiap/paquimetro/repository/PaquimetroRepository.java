package br.com.fiap.paquimetro.repository;

import br.com.fiap.paquimetro.dominio.Paquimetro;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PaquimetroRepository extends MongoRepository<Paquimetro, String> {
}
