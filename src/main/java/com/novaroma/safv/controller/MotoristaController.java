package com.novaroma.safv.controller;

import com.novaroma.safv.model.Motorista;
import com.novaroma.safv.model.Servidor;
import com.novaroma.safv.service.MotoristaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/motorista")
public class MotoristaController {
    @Autowired
    private MotoristaService service;

    @GetMapping("/{id}")
    public ResponseEntity<Object> find(@PathVariable(value = "id") String cnh){
        Optional<Motorista> motoristaOptional = service.findById(cnh);
        if(motoristaOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.OK).body(motoristaOptional.get());
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Motorista não encontrado");
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<Object> findByEmail(@PathVariable(value="email") String email){
        Optional<Motorista> motoristaOptional = service.findByEmail(email);
        if(motoristaOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.OK).body(motoristaOptional.get());
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Motorista não encontrado");
    }

    @GetMapping
    public ResponseEntity<List<Motorista>> findAll(){
        return ResponseEntity.status(HttpStatus.OK).body(service.findAll());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable(value="id") String id){
        if(service.delete(id)){
            return ResponseEntity.status(204).body(true);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(false);
    }

    @PostMapping
    public ResponseEntity<Object> save(@RequestBody Motorista motorista){
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(motorista));
    }

    @PutMapping("/{cnh}")
    public ResponseEntity<Object> update(@RequestBody Motorista motorista, @PathVariable String cnh){
        Optional<Motorista> motoristaOptional = service.findById(cnh);
        if(motoristaOptional.isPresent()){
            motoristaOptional.get().setCategoria(motorista.getCategoria());
            motoristaOptional.get().setNome(motorista.getNome());
            motoristaOptional.get().setSobrenome(motorista.getSobrenome());
            motoristaOptional.get().setDataDeNascimento(motorista.getDataDeNascimento());
            motoristaOptional.get().setCpf(motorista.getCpf());
            motoristaOptional.get().setLogradouro(motorista.getLogradouro());
            motoristaOptional.get().setNumero(motorista.getNumero());
            motoristaOptional.get().setComplemento(motorista.getComplemento());
            motoristaOptional.get().setCep(motorista.getCep());
            motoristaOptional.get().setSexo(motorista.getSexo());
            motoristaOptional.get().setPrimeiroAcesso(motorista.getPrimeiroAcesso());
            motoristaOptional.get().setSenha(motorista.getSenha());
            motoristaOptional.get().setEmail(motorista.getEmail());

            return ResponseEntity.status(HttpStatus.OK).body(service.save(motoristaOptional.get()));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Motorista não encontrado");
    }
}
