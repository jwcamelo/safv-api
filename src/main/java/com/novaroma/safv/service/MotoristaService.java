package com.novaroma.safv.service;

import com.novaroma.safv.model.Motorista;
import com.novaroma.safv.model.Servidor;
import com.novaroma.safv.repository.MotoristaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MotoristaService {
    @Autowired
    private MotoristaRepository repo;

    public List<Motorista> findAll(){
        return repo.findAll();
    }
    public Optional<Motorista> findById(String id){
        return repo.findById(id);
    }
    public Optional<Motorista> findByEmail(String email){
        return repo.findByEmail(email);
    }

    public List<Motorista> findByCategoria(String categoria){
        return repo.findByCategoria(categoria);
    }

    public Motorista save(Motorista motorista){
        return repo.save(motorista);
    }

    public Boolean delete(String id){
        if(findById(id).isPresent()) {
            repo.deleteById(id);
            return true;
        }
        return false;
    }
}
