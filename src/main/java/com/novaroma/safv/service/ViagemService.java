package com.novaroma.safv.service;

import com.novaroma.safv.model.Funcao;
import com.novaroma.safv.model.Viagem;
import com.novaroma.safv.repository.FuncaoRepository;
import com.novaroma.safv.repository.ViagemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ViagemService {
    @Autowired
    private ViagemRepository repo;

    public List<Viagem> findAll(){
        return repo.findAll();
    }
    public Optional<Viagem> findById(Long id){
        return repo.findById(id);
    }

    public Viagem save(Viagem viagem){
        return repo.save(viagem);
    }

    public Boolean delete(Long id){
        if(findById(id).isPresent()) {
            repo.deleteById(id);
            return true;
        }
        return false;
    }

    public List<Viagem> findByData(String data){
        return repo.findByData(data);
    }

    public Page<Viagem> findAll(Pageable pageable) {
        return repo.findAll(pageable);
    }
}
