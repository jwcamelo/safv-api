package com.novaroma.safv.controller;

import com.novaroma.safv.dto.ViagemDto;
import com.novaroma.safv.model.Motorista;
import com.novaroma.safv.model.Servidor;
import com.novaroma.safv.model.Veiculo;
import com.novaroma.safv.model.Viagem;
import com.novaroma.safv.service.MotoristaService;
import com.novaroma.safv.service.ServidorService;
import com.novaroma.safv.service.VeiculoService;
import com.novaroma.safv.service.ViagemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/viagem")
public class ViagemController {
    @Autowired
    private ViagemService service;
    @Autowired
    private VeiculoService veiculoService;
    @Autowired
    private ServidorService servidorService;
    @Autowired
    private MotoristaService motoristaService;

    @GetMapping("/{id}")
    public ResponseEntity<Object> find(@PathVariable(value = "id") Long id){
        Optional<Viagem> viagemOptional = service.findById(id);
        if(viagemOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.OK).body(viagemOptional.get());
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Veículo não encontrado");
    }

    @GetMapping
    public ResponseEntity<List<Viagem>> findAll(){
        return ResponseEntity.status(HttpStatus.OK).body(service.findAll());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable(value="id") Long id){
        if(service.delete(id)){
            return ResponseEntity.status(204).body(true);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(false);
    }

    @PostMapping
    public ResponseEntity<Object> save(@RequestBody ViagemDto viagemDto){
        Viagem viagem = new Viagem();

        viagem.setData(viagemDto.getData());
        viagem.setHora(viagemDto.getHora());
        viagem.setLocalDestino(viagemDto.getLocalDestino());
        viagem.setLocalPartida(viagemDto.getLocalPartida());
        if(!viagemDto.getCnhMotoristas().isEmpty()){
            for(String placa : viagemDto.getPlacaVeiculos()){
                if(veiculoService.findById(placa).isPresent()){
                    viagem.getVeiculos().add(veiculoService.findById(placa).get());
                }
                else{
                    return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Veículo não encontrado");
                }
            }
        }
        if(!viagemDto.getCnhMotoristas().isEmpty()) {
            for (String cnh : viagemDto.getCnhMotoristas()) {
                if (motoristaService.findById(cnh).isPresent()) {
                    viagem.getMotoristas().add(motoristaService.findById(cnh).get());
                } else {
                    return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Motorista não encontrado");
                }
            }
        }
        if(!viagemDto.getMatriculaServidores().isEmpty()){
            for(String matricula : viagemDto.getMatriculaServidores()){
                if(servidorService.findById(matricula).isPresent()){
                    viagem.getServidores().add(servidorService.findById(matricula).get());
                }
                else{
                    return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Servidor não encontrado");
                }
            }
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(viagem));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> update(@RequestBody ViagemDto viagemDto, @PathVariable Long id){
        Optional<Viagem> viagemOptional = service.findById(id);
        if(viagemOptional.isPresent()){
            viagemOptional.get().setData(viagemDto.getData());
            viagemOptional.get().setHora(viagemDto.getHora());
            viagemOptional.get().setLocalDestino(viagemDto.getLocalDestino());
            viagemOptional.get().setLocalPartida(viagemDto.getLocalPartida());

            if(!viagemDto.getCnhMotoristas().isEmpty()){
                for(String placa : viagemDto.getPlacaVeiculos()){
                    if(veiculoService.findById(placa).isPresent()){
                        viagemOptional.get().getVeiculos().add(veiculoService.findById(placa).get());
                    }
                    else{
                        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Veículo não encontrado");
                    }
                }
            }
            if(!viagemDto.getCnhMotoristas().isEmpty()) {
                for (String cnh : viagemDto.getCnhMotoristas()) {
                    if (motoristaService.findById(cnh).isPresent()) {
                        viagemOptional.get().getMotoristas().add(motoristaService.findById(cnh).get());
                    } else {
                        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Motorista não encontrado");
                    }
                }
            }
            if(!viagemDto.getMatriculaServidores().isEmpty()){
                for(String matricula : viagemDto.getMatriculaServidores()){
                    if(servidorService.findById(matricula).isPresent()){
                        viagemOptional.get().getServidores().add(servidorService.findById(matricula).get());
                    }
                    else{
                        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Servidor não encontrado");
                    }
                }
            }

            return ResponseEntity.status(HttpStatus.OK).body(service.save(viagemOptional.get()));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Viagem não encontrada");
    }

    @PutMapping("/{id}/servidor/{matriculaServidor}")
    public ResponseEntity<Object> addServidor(@PathVariable("id") Long id, @PathVariable("matriculaServidor") String matriculaServidor){
        Optional<Viagem> viagemOptional = service.findById(id);
        if(viagemOptional.isPresent()){
            Optional<Servidor> servidorOptional = servidorService.findById(matriculaServidor);
            if(servidorOptional.isPresent()){
                viagemOptional.get().getServidores().add(servidorOptional.get());
                return ResponseEntity.status(HttpStatus.OK).body(service.save(viagemOptional.get()));
            }
            else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Servidor não encontrado");
            }
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Viagem não encontrada");
        }
    }

    @PutMapping("/{id}/motorista/{cnhMotorista}")
    public ResponseEntity<Object> addMotorista(@PathVariable("id") Long id, @PathVariable("cnhMotorista") String cnhMotorista){
        Optional<Viagem> viagemOptional = service.findById(id);
        if(viagemOptional.isPresent()){
            Optional<Motorista> motoristaOptional = motoristaService.findById(cnhMotorista);
            if(motoristaOptional.isPresent()){
                viagemOptional.get().getMotoristas().add(motoristaOptional.get());
                return ResponseEntity.status(HttpStatus.OK).body(service.save(viagemOptional.get()));
            }
            else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Motorista não encontrado");
            }
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Viagem não encontrada");
        }
    }

    @PutMapping("/{id}/veiculo/{placaVeiculo}")
    public ResponseEntity<Object> addVeiculo(@PathVariable("id") Long id, @PathVariable("placaVeiculo") String placaVeiculo){
        Optional<Viagem> viagemOptional = service.findById(id);
        if(viagemOptional.isPresent()){
            Optional<Veiculo> veiculoOptional = veiculoService.findById(placaVeiculo);
            if(veiculoOptional.isPresent()){
                viagemOptional.get().getVeiculos().add(veiculoOptional.get());
                return ResponseEntity.status(HttpStatus.OK).body(service.save(viagemOptional.get()));
            }
            else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Veículo não encontrado");
            }
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Viagem não encontrada");
        }
    }

    /*@GetMapping
    public ResponseEntity<Page<Viagem>> findAllPage(@PageableDefault(page = 0, size = 10, sort = "id", direction = Sort.Direction.ASC) Pageable pageable){
        return ResponseEntity.status(HttpStatus.OK).body(service.findAll(pageable));
    }*/
}
