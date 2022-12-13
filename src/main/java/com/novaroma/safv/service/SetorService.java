package com.novaroma.safv.service;

import com.novaroma.safv.model.Setor;
import com.novaroma.safv.repository.SetorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SetorService {
    @Autowired
    private SetorRepository repo;

    public List<Setor> findAll(){
        return repo.findAll();
    }
    public Optional<Setor> findById(Integer id){
        return repo.findById(id);
    }

    public Setor save(Setor setor){
        return repo.save(setor);
    }
    public Boolean delete(Integer id){
        if(findById(id).isPresent()) {
            repo.deleteById(id);
            return true;
        }
        return false;
    }
}
