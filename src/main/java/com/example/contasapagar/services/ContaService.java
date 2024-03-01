package com.example.contasapagar.services;

import com.example.contasapagar.dtos.ContaRecordDto;
import com.example.contasapagar.dtos.UpdateStatusContaRecordDto;
import com.example.contasapagar.models.ContaModel;
import com.example.contasapagar.repositories.ContaRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ContaService {

    @Autowired
    private ContaRepository contaRepository;

    public ContaModel createConta(ContaRecordDto contaRecordDto){
        ContaModel contaModel = new ContaModel();
        BeanUtils.copyProperties(contaRecordDto,contaModel);
        ContaModel save = contaRepository.save(contaModel);
        return save;
    }

    public List<ContaModel> getAllContas(){
        return contaRepository.findAll();
    }

    public Optional<ContaModel> getConta(UUID id){
        return contaRepository.findById(id);
    }

    public Optional<ContaModel> updateConta(UUID id, ContaRecordDto contaRecordDto){
        Optional<ContaModel> contaO = contaRepository.findById(id);
        if(contaO.isPresent()){
            BeanUtils.copyProperties(contaRecordDto, contaO.get());
            contaRepository.save(contaO.get());
        }
        return contaO;
    }

    public Optional<ContaModel> updateStatusConta(UUID id, UpdateStatusContaRecordDto updateStatusContaRecordDto){
        Optional<ContaModel> contaO = contaRepository.findById(id);
        if(contaO.isPresent()){
            BeanUtils.copyProperties(updateStatusContaRecordDto, contaO.get());
            contaRepository.save(contaO.get());
        }
        return contaO;
    }

    public void deleteConta(UUID id) throws EntityNotFoundException {
        Optional<ContaModel> contaO = contaRepository.findById(id);
        if(contaO.isEmpty()){
            throw new EntityNotFoundException();
        }
        contaRepository.delete(contaO.get());
    }

}
