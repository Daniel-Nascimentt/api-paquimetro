package br.com.fiap.recibo.repository;

import br.com.fiap.recibo.dominio.OpcaoEstacionamento;
import br.com.fiap.recibo.dominio.Recibo;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ReciboRepository extends MongoRepository<Recibo, String> {


    long countByOpcaoEstacionamento(OpcaoEstacionamento opcaoEstacionamento);

    long countByEnviadoPorEmail(boolean enviadoPorEmail);
}
