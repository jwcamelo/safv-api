package com.novaroma.safv.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Servidor extends Pessoa implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    private String matricula;
    private String emailSes;
    private Boolean admin;
    @ManyToOne
    private Setor setor;
    @ManyToOne
    private Funcao funcao;

    @JsonIgnore
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    @ManyToMany(mappedBy = "servidores")
    private List<Viagem> viagens = new ArrayList<>();

}
