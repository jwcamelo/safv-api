package com.novaroma.safv.repository;

import com.novaroma.safv.model.Motorista;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MotoristaRepository extends JpaRepository<Motorista, String> {
    public Optional<Motorista> findByCnh(String cnh);
    public Optional<Motorista> findByCpf(String cpf);
}
