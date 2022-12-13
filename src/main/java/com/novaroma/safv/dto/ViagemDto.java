package com.novaroma.safv.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ViagemDto {
    private Long id;
    private String data;
    private String hora;
    private String localPartida;
    private String localDestino;
    private List<String> placaVeiculos = new ArrayList<>();
    private List<String> cnhMotoristas = new ArrayList<>();
    private List<String> matriculaServidores = new ArrayList<>();
}
