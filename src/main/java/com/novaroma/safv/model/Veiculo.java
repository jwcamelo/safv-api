package com.novaroma.safv.model;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

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
}
