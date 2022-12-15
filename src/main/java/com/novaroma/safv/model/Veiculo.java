package com.novaroma.safv.model;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Veiculo {
    @Id
    private String placa;
    private String fabricante;
    private Double quilometragem;
    private String cor;
    private String tipo;
    private Integer ano;
    private String modelo;

    @JsonIgnore
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    @ManyToMany(mappedBy = "veiculos")
    private List<Viagem> viagens = new ArrayList<>();
}
