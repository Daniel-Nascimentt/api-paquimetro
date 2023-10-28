package br.com.fiap.alerta.repository;

import br.com.fiap.alerta.dominio.Alerta;
import br.com.fiap.alerta.dominio.StatusAlerta;
import br.com.fiap.alerta.dominio.StatusEstacionado;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface AlertaRepository extends MongoRepository<Alerta, String> {
    Optional<Alerta> findByIdPaquimetroAndStatusPaquimetroAndStatusAlerta(String idPaquimetro, StatusEstacionado statusPaquimetro, StatusAlerta statusAlerta);

    default Optional<Alerta> findByIdPaquimetroAndStatusPaquimetroAndStatusAlerta(String idPaquimetro) {
        return findByIdPaquimetroAndStatusPaquimetroAndStatusAlerta(idPaquimetro, StatusEstacionado.ATIVO, StatusAlerta.PENDENTE);
    }
}


