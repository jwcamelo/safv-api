package com.novaroma.safv.model;


import javax.persistence.Entity;

import javax.persistence.Id;
import javax.persistence.ManyToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Cascade;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Motorista extends Pessoa implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    private String cnh;
    private String categoria;

    @JsonIgnore
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    @ManyToMany(mappedBy = "motoristas")
    private List<Viagem> viagens = new ArrayList<>();
}
