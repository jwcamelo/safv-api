package com.novaroma.safv.controller;

import com.novaroma.safv.dto.SetorDTO;
import com.novaroma.safv.model.Setor;
import com.novaroma.safv.service.SetorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/setor")
@CrossOrigin(origins="*")
public class SetorController {
    @Autowired
    private SetorService service;

    @GetMapping("/{id}")
    public ResponseEntity<Object> find(@PathVariable(value = "id") Integer id){
        Optional<Setor> setorOptional = service.findById(id);
        if(setorOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.OK).body(setorOptional.get());
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Setor não encontrado");
    }

    @GetMapping
    public ResponseEntity<List<Setor>> findAll(){
        return ResponseEntity.status(HttpStatus.OK).body(service.findAll());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable(value="id") Integer id){
        if(service.delete(id)){
            return ResponseEntity.status(204).body(true);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(false);
    }

    @PostMapping
    public ResponseEntity<Object> save(@RequestBody SetorDTO setorDto){
        Setor setor = new Setor();
        setor.setNome(setorDto.getNome());
        setor.setDescricao(setorDto.getDescricao());
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(setor));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> update(@RequestBody SetorDTO setorDto, @PathVariable Integer id){
        Optional<Setor> setorOptional = service.findById(id);
        if(setorOptional.isPresent()){
            setorOptional.get().setNome(setorDto.getNome());
            setorOptional.get().setDescricao(setorDto.getDescricao());
            return ResponseEntity.status(HttpStatus.OK).body(service.save(setorOptional.get()));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Setor não encontrado");
    }
}
