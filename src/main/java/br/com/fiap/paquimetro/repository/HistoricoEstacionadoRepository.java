package br.com.fiap.paquimetro.repository;

import br.com.fiap.paquimetro.dominio.HistoricoEstacionados;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface HistoricoEstacionadoRepository extends MongoRepository<HistoricoEstacionados, String> {
}
