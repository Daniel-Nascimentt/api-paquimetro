package br.com.fiap.alerta.repository;

import br.com.fiap.alerta.dominio.Alerta;
import br.com.fiap.alerta.dominio.StatusAlerta;
import br.com.fiap.alerta.dominio.StatusEstacionado;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface AlertaRepository extends MongoRepository<Alerta, String> {
    Optional<Alerta> findByIdPaquimetroAndStatusPaquimetroAndStatusAlerta(String idPaquimetro, StatusEstacionado statusPaquimetro, StatusAlerta statusAlerta);

    default Optional<Alerta> findByIdPaquimetroAndStatusPaquimetroAndStatusAlerta(String idPaquimetro) {
        return findByIdPaquimetroAndStatusPaquimetroAndStatusAlerta(idPaquimetro, StatusEstacionado.ATIVO, StatusAlerta.PENDENTE);
    }

    List<Alerta> findByStatusPaquimetroAndStatusAlertaAndProximaAlertaBetween(StatusEstacionado statusPaquimetro, StatusAlerta statusAlerta, LocalDateTime agora, LocalDateTime cincoMinDepois);

    default List<Alerta> findByStatusPaquimetroAndStatusAlertaAndProximaAlertaBetween(){
        return findByStatusPaquimetroAndStatusAlertaAndProximaAlertaBetween(StatusEstacionado.ATIVO, StatusAlerta.PENDENTE, LocalDateTime.now(), LocalDateTime.now().plusMinutes(5));
    }

}


