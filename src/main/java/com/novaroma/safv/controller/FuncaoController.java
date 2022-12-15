package com.novaroma.safv.controller;

import com.novaroma.safv.dto.FuncaoDTO;
import com.novaroma.safv.model.Funcao;
import com.novaroma.safv.service.FuncaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/funcao")
@CrossOrigin(origins="*")
public class FuncaoController {
    @Autowired
    private FuncaoService service;

    @GetMapping("/{id}")
    public ResponseEntity<Object> find(@PathVariable(value = "id") Integer id){
        Optional<Funcao> funcaoOptional = service.findById(id);
        if(funcaoOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.OK).body(funcaoOptional.get());
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Função não encontrada");
    }

    @GetMapping
    public ResponseEntity<List<Funcao>> findAll(){
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
    public ResponseEntity<Object> save(@RequestBody FuncaoDTO funcaoDto){
        Funcao funcao = new Funcao();
        funcao.setNome(funcaoDto.getNome());
        funcao.setDescricao(funcaoDto.getDescricao());
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(funcao));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> update(@RequestBody FuncaoDTO funcaoDto, @PathVariable Integer id){
        Optional<Funcao> funcaoOptional = service.findById(id);
        if(funcaoOptional.isPresent()){
            funcaoOptional.get().setNome(funcaoDto.getNome());
            funcaoOptional.get().setDescricao(funcaoDto.getDescricao());
            return ResponseEntity.status(HttpStatus.OK).body(service.save(funcaoOptional.get()));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Função não encontrada");
    }
}
