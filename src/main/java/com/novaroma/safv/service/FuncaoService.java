package com.novaroma.safv.service;

import com.novaroma.safv.model.Funcao;
import com.novaroma.safv.repository.FuncaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FuncaoService {
    @Autowired
    private FuncaoRepository repo;

    public List<Funcao> findAll(){
        return repo.findAll();
    }
    public Optional<Funcao> findById(Integer id){
        return repo.findById(id);
    }

    public Funcao save(Funcao funcao){
        return repo.save(funcao);
    }

    public Boolean delete(Integer id){
        if(findById(id).isPresent()) {
            repo.deleteById(id);
            return true;
        }
        return false;
    }
}
