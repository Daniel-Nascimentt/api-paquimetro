package br.com.fiap.paquimetro.repository;

import br.com.fiap.paquimetro.dominio.Condutor;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.Optional;

public interface CondutorRepository extends MongoRepository<Condutor, String> {

    Optional<Condutor> findByDocumentoIdentificacao(String identificacao);
}
