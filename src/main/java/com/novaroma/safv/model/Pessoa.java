package com.novaroma.safv.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@MappedSuperclass
public class Pessoa {
    
    private String cpf;
    private String nome;
    private String sobrenome;
    private String dataDeNascimento;
    private String logradouro;
    private Integer numero;
    private Integer cep;
    private String complemento;
    private String email;
    private Character sexo;
    private Boolean primeiroAcesso = true;
    private String senha;
}
