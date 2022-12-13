package com.novaroma.safv.model;


import javax.persistence.Entity;

import javax.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Motorista extends Pessoa implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    private String cnh;
    private String categoria;
}
