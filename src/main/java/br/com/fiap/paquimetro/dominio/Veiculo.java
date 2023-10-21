package br.com.fiap.paquimetro.dominio;

import br.com.fiap.paquimetro.dto.request.VeiculoRequestUpdate;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.TextIndexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document
public class Veiculo {

    @Id
    private String id;

    private String marca;

    private String modelo;

    @TextIndexed
    private String placa;

    @DBRef
    private Condutor condutor;

    public Veiculo(String marca, String modelo, String placa, Condutor condutor) {
        this.marca = marca;
        this.modelo = modelo;
        this.placa = placa;
        this.condutor = condutor;
    }

    public void update(VeiculoRequestUpdate request) {
        this.marca = (request.getMarca() != null && !request.getMarca().isEmpty()) ? request.getMarca() : this.marca;
        this.modelo = (request.getModelo() != null && !request.getModelo().isEmpty()) ? request.getModelo() : this.modelo;
        this.placa = (request.getPlaca() != null && !request.getPlaca().isEmpty()) ? request.getPlaca() : this.placa;
    }
}
