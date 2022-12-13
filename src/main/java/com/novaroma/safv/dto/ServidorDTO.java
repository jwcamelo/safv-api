package com.novaroma.safv.dto;

import com.novaroma.safv.model.Pessoa;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ServidorDTO extends Pessoa {
    private String matricula;
    private Integer idSetor;
    private Integer idFuncao;
    private String emailSes;
    private Boolean admin;
}
