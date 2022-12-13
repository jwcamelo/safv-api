package com.novaroma.safv.service;

import com.novaroma.safv.model.Veiculo;
import com.novaroma.safv.repository.VeiculoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VeiculoService {
    @Autowired
    private VeiculoRepository repo;

    public List<Veiculo> findAll(){
        return repo.findAll();
    }
    public Optional<Veiculo> findById(String id){
        return repo.findById(id);
    }
    public List<Veiculo> findByTipo(String tipo){
        return repo.findByTipo(tipo);
    }

    public Veiculo save(Veiculo veiculo){
        return repo.save(veiculo);
    }

    public Boolean delete(String id){
        if(findById(id).isPresent()) {
            repo.deleteById(id);
            return true;
        }
        return false;
    }
}
