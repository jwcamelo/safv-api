package com.novaroma.safv.repository;

import com.novaroma.safv.model.Servidor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ServidorRepository extends JpaRepository<Servidor, String> {
    public Optional<Servidor> findByCpf(String cpf);
}
