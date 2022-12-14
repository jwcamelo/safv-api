package com.novaroma.safv.controller;

import com.novaroma.safv.dto.ServidorDTO;
import com.novaroma.safv.model.Funcao;
import com.novaroma.safv.model.Servidor;
import com.novaroma.safv.model.Setor;
import com.novaroma.safv.service.FuncaoService;
import com.novaroma.safv.service.ServidorService;
import com.novaroma.safv.service.SetorService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/servidor")
public class ServidorController {
    @Autowired
    private ServidorService service;
    @Autowired
    private SetorService setorService;
    @Autowired
    private FuncaoService funcaoService;

    @GetMapping("/{id}")
    public ResponseEntity<Object> find(@PathVariable(value = "id") String matricula){
        Optional<Servidor> servidorOptional = service.findById(matricula);
        if(servidorOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.OK).body(servidorOptional.get());
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Servidor não encontrado");
    }

    @GetMapping
    public ResponseEntity<List<Servidor>> findAll(){
        return ResponseEntity.status(HttpStatus.OK).body(service.findAll());
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<Object> findByEmail(@PathVariable(value="email") String email){
        Optional<Servidor> servidorOptional = service.findByEmail(email);
        if(servidorOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.OK).body(servidorOptional.get());
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Servidor não encontrado");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable(value="id") String matricula){
        if(service.delete(matricula)){
            return ResponseEntity.status(204).body(true);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(false);
    }

    @PostMapping
    public ResponseEntity<Object> save(@RequestBody ServidorDTO servidorDto){
        Servidor servidor = new Servidor();
        Optional<Setor> setorOptional = setorService.findById(servidorDto.getIdSetor());
        Optional<Funcao> funcaoOptional = funcaoService.findById(servidorDto.getIdFuncao());
        if(setorOptional.isPresent() && funcaoOptional.isPresent()) {
            servidor.setSetor(setorOptional.get());
            servidor.setFuncao(funcaoOptional.get());
            servidor.setMatricula(servidorDto.getMatricula());
            servidor.setAdmin(servidorDto.getAdmin());
            servidor.setNome(servidorDto.getNome());
            servidor.setSobrenome(servidorDto.getSobrenome());
            servidor.setDataDeNascimento(servidorDto.getDataDeNascimento());
            servidor.setCpf(servidorDto.getCpf());
            servidor.setLogradouro(servidorDto.getLogradouro());
            servidor.setNumero(servidorDto.getNumero());
            servidor.setComplemento(servidorDto.getComplemento());
            servidor.setCep(servidorDto.getCep());
            servidor.setEmail(servidorDto.getEmail());
            servidor.setEmailSes(servidorDto.getEmailSes());
            servidor.setPrimeiroAcesso(servidorDto.getPrimeiroAcesso());
            servidor.setSenha(servidorDto.getSenha());
            servidor.setSexo(servidorDto.getSexo());
            return ResponseEntity.status(HttpStatus.CREATED).body(service.save(servidor));
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("setor e/ou função não encontrado(s)");
        }
    }

    @PutMapping("/{matricula}")
    public ResponseEntity<Object> update(@RequestBody ServidorDTO servidorDto, @PathVariable String matricula){
        Optional<Servidor> servidorOptional = service.findById(matricula);
        Optional<Setor> setorOptional = setorService.findById(servidorDto.getIdSetor());
        Optional<Funcao> funcaoOptional = funcaoService.findById(servidorDto.getIdFuncao());
        if(servidorOptional.isPresent() && setorOptional.isPresent() && funcaoOptional.isPresent()){
            servidorOptional.get().setAdmin(servidorDto.getAdmin());
            servidorOptional.get().setSetor(setorOptional.get());
            servidorOptional.get().setFuncao(funcaoOptional.get());
            servidorOptional.get().setNome(servidorDto.getNome());
            servidorOptional.get().setSobrenome(servidorDto.getSobrenome());
            servidorOptional.get().setDataDeNascimento(servidorDto.getDataDeNascimento());
            servidorOptional.get().setCpf(servidorDto.getCpf());
            servidorOptional.get().setLogradouro(servidorDto.getLogradouro());
            servidorOptional.get().setNumero(servidorDto.getNumero());
            servidorOptional.get().setComplemento(servidorDto.getComplemento());
            servidorOptional.get().setCep(servidorDto.getCep());
            servidorOptional.get().setEmail(servidorDto.getEmail());
            servidorOptional.get().setEmailSes(servidorDto.getEmailSes());
            servidorOptional.get().setPrimeiroAcesso(servidorDto.getPrimeiroAcesso());
            servidorOptional.get().setSenha(servidorDto.getSenha());
            servidorOptional.get().setSexo(servidorDto.getSexo());


            return ResponseEntity.status(HttpStatus.OK).body(service.save(servidorOptional.get()));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Servidor, setor e/ou função não encontrado(s)");
    }
}
