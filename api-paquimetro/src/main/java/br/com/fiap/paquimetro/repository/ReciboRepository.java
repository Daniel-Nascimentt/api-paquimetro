package br.com.fiap.paquimetro.repository;

import br.com.fiap.paquimetro.dominio.Recibo;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ReciboRepository extends MongoRepository<Recibo, String> {
}
