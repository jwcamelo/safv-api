package com.novaroma.safv.service;

import com.novaroma.safv.model.Servidor;
import com.novaroma.safv.model.Viagem;
import com.novaroma.safv.repository.ServidorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ServidorService {
    @Autowired
    private ServidorRepository repo;

    public List<Servidor> findAll(){
        return repo.findAll();
    }
    public Optional<Servidor> findById(String id){
        return repo.findById(id);
    }
    public Optional<Servidor> findByEmail(String email){
        return repo.findByEmailSes(email);
    }

    public Servidor save(Servidor servidor){
        return repo.save(servidor);
    }

    public Boolean delete(String id){
        if(findById(id).isPresent()) {
            repo.deleteById(id);
            return true;
        }
        return false;
    }
}
