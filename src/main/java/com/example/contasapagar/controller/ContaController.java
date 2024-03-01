package com.example.contasapagar.controller;

import com.example.contasapagar.dtos.ContaRecordDto;
import com.example.contasapagar.dtos.UpdateStatusContaRecordDto;
import com.example.contasapagar.models.ContaModel;
import com.example.contasapagar.services.ContaService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
public class ContaController {

    @Autowired
    private ContaService contaService;

    @PostMapping("/contas")
    public ResponseEntity<ContaModel> saveConta(@RequestBody @Valid ContaRecordDto contaRecordDto){
        return ResponseEntity.status(HttpStatus.CREATED).body(contaService.createConta(contaRecordDto));
    }

    @GetMapping("/contas")
    public ResponseEntity<List<ContaModel>> getAllContas(){
        return ResponseEntity.status(HttpStatus.OK).body(contaService.getAllContas());
    }

    @GetMapping("/contas/{id}")
    public ResponseEntity<Object> getConta(@PathVariable(value = "id") UUID id){
        Optional<ContaModel> contaO = contaService.getConta(id);
        if(contaO.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Conta não encontrada.");
        }
        return ResponseEntity.status(HttpStatus.OK).body(contaO.get());
    }

    @PutMapping("/contas/{id}")
    public ResponseEntity<Object> updateConta(@PathVariable(value = "id") UUID id,
                                              @RequestBody @Valid ContaRecordDto contaRecordDto){
        Optional<ContaModel> contaO = contaService.updateConta(id, contaRecordDto);
        if(contaO.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Conta não encontrada.");
        }
        return ResponseEntity.status(HttpStatus.OK).body(contaO.get());
    }

    @PutMapping("/contas/updateStatus/{id}")
    public ResponseEntity<Object> updateStatusConta(@PathVariable(value = "id") UUID id,
                                              @RequestBody @Valid UpdateStatusContaRecordDto updateStatusContaRecordDto){
        Optional<ContaModel> contaO = contaService.updateStatusConta(id, updateStatusContaRecordDto);
        if(contaO.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Conta não encontrada.");
        }
        return ResponseEntity.status(HttpStatus.OK).body(contaO.get());
    }

    @DeleteMapping("/contas/{id}")
    public ResponseEntity<Object> deleteConta(@PathVariable(value = "id") UUID id) throws EntityNotFoundException {
        try {
            contaService.deleteConta(id);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Conta não Encontrada.");
        }
        return ResponseEntity.status(HttpStatus.OK).body("Conta deletada com sucesso.");
    }
}
