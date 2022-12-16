package com.novaroma.safv.repository;

import com.novaroma.safv.model.Motorista;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MotoristaRepository extends JpaRepository<Motorista, String> {
    public Optional<Motorista> findByEmail(String email);

    public List<Motorista> findByCategoria(String categoria);
}
