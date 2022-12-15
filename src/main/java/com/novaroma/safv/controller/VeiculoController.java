package com.novaroma.safv.controller;

import com.novaroma.safv.dto.FuncaoDTO;

import com.novaroma.safv.model.Veiculo;
import com.novaroma.safv.service.VeiculoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/veiculo")
@CrossOrigin(origins="*")
public class VeiculoController {
    @Autowired
    private VeiculoService service;

    @GetMapping("/{id}")
    public ResponseEntity<Object> find(@PathVariable(value = "id") String placa){
        Optional<Veiculo> veiculoOptional = service.findById(placa);
        if(veiculoOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.OK).body(veiculoOptional.get());
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Função não encontrada");
    }

    @GetMapping
    public ResponseEntity<List<Veiculo>> findAll(){
        return ResponseEntity.status(HttpStatus.OK).body(service.findAll());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable(value="id") String placa){
        if(service.delete(placa)){
            return ResponseEntity.status(204).body(true);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(false);
    }

    @PostMapping
    public ResponseEntity<Object> save(@RequestBody Veiculo veiculo){
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(veiculo));
    }

    @PutMapping("/{placa}")
    public ResponseEntity<Object> update(@RequestBody Veiculo veiculo, @PathVariable String placa){
        Optional<Veiculo> veiculoOptional = service.findById(placa);
        if(veiculoOptional.isPresent()){
            veiculoOptional.get().setAno(veiculo.getAno());
            veiculoOptional.get().setCor(veiculo.getCor());
            veiculoOptional.get().setFabricante(veiculo.getFabricante());
            veiculoOptional.get().setTipo(veiculo.getTipo());
            veiculoOptional.get().setModelo(veiculo.getModelo());
            veiculoOptional.get().setQuilometragem(veiculo.getQuilometragem());

            return ResponseEntity.status(HttpStatus.OK).body(service.save(veiculoOptional.get()));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Veiculo não encontrado");
    }
}
