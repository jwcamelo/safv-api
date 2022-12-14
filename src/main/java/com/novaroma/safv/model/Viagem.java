package com.novaroma.safv.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Viagem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String data;
    private String hora;
    private String localPartida;
    private String localDestino;

    @OneToMany
    List<Veiculo> veiculos = new ArrayList<>();
    @ManyToMany
    List<Servidor> servidores = new ArrayList<>();
    @OneToMany
    List<Motorista> motoristas = new ArrayList<>();

}
