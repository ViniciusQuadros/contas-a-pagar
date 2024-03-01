package com.example.contasapagar.services;

import com.example.contasapagar.dtos.ContaRecordDto;
import com.example.contasapagar.dtos.UpdateStatusContaRecordDto;
import com.example.contasapagar.models.ContaModel;
import com.example.contasapagar.models.enums.SituacaoConta;
import com.example.contasapagar.mother.ContaMother;
import com.example.contasapagar.repositories.ContaRepository;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;

@ExtendWith({MockitoExtension.class})
class ContaServiceTest {

    @Mock
    private ContaRepository contaRepository;

    @InjectMocks
    private ContaService contaService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void createConta() {

        ContaRecordDto contaRecordDto = ContaMother.getContaRecordDto();
        ContaModel contaModel = ContaMother.getContaModel();
        contaModel.setIdConta(null);
        ContaModel contaModel2 = ContaMother.getContaModel();

        Mockito.when(contaRepository.save(contaModel)).thenReturn(contaModel2);

        ContaModel result = contaService.createConta(contaRecordDto);

        Assertions.assertEquals(contaModel2, result);

    }

    @Test
    void getAllContas() {

        ContaModel contaModel1 = ContaMother.getContaModel();
        ContaModel contaModel2 = ContaMother.getContaModel();
        contaModel2.setValor(new BigDecimal("101.50"));
        ContaModel contaModel3 = ContaMother.getContaModel();
        contaModel3.setValor(new BigDecimal("102.50"));
        List<ContaModel> contas = new ArrayList<>(Arrays.asList(contaModel1, contaModel2, contaModel3));

        Mockito.when(contaRepository.findAll()).thenReturn(contas);

        List<ContaModel> result = contaService.getAllContas();

        Assertions.assertEquals(contas, result);

    }

    @Test
    void getConta() {

        UUID idConta = UUID.fromString("9c4ff3b6-4418-4231-8e6b-4fab0051ed06");
        ContaModel contaModel = ContaMother.getContaModel();

        Mockito.when(contaRepository.findById(idConta)).thenReturn(Optional.of(contaModel));

        Optional<ContaModel> result = contaService.getConta(idConta);

        Assertions.assertEquals(Optional.of(contaModel), result);

    }

    @Test
    void updateConta() {

        UUID idConta = UUID.fromString("9c4ff3b6-4418-4231-8e6b-4fab0051ed06");
        ContaModel contaModel = ContaMother.getContaModel();
        ContaRecordDto contaRecordDto = ContaMother.getContaRecordDto();

        Mockito.when(contaRepository.findById(idConta)).thenReturn(Optional.of(contaModel));
        Mockito.when(contaRepository.save(contaModel)).thenReturn(contaModel);

        Optional<ContaModel> result = contaService.updateConta(idConta, contaRecordDto);

        Assertions.assertEquals(Optional.of(contaModel), result);

    }

    @Test
    void updateStatusConta() {

        UUID idConta = UUID.fromString("9c4ff3b6-4418-4231-8e6b-4fab0051ed06");
        ContaModel contaModel = ContaMother.getContaModel();
        ContaModel contaModel2 = ContaMother.getContaModel();
        contaModel2.setDataPagamento(LocalDate.of(2024,3,30));
        contaModel2.setSituacao(SituacaoConta.PAGO);
        UpdateStatusContaRecordDto updateStatusContaRecordDto = ContaMother.getUpdateStatusContaRecordDto();

        Mockito.when(contaRepository.findById(idConta)).thenReturn(Optional.of(contaModel));
        Mockito.when(contaRepository.save(contaModel)).thenReturn(contaModel);

        Optional<ContaModel> result = contaService.updateStatusConta(idConta, updateStatusContaRecordDto);

        Assertions.assertEquals(Optional.of(contaModel2), result);

    }

    @Test
    void deleteConta() {

        UUID idConta = UUID.fromString("9c4ff3b6-4418-4231-8e6b-4fab0051ed06");
        ContaModel contaModel = ContaMother.getContaModel();

        Mockito.when(contaRepository.findById(idConta)).thenReturn(Optional.of(contaModel));
        Mockito.doNothing().when(contaRepository).delete(contaModel);

        contaService.deleteConta(idConta);

        Mockito.verify(contaRepository).delete(contaModel);
    }

    @Test
    void deleteContaEntityNotFoundException() {

        UUID idConta = UUID.fromString("9c4ff3b6-4418-4231-8e6b-4fab0051ed06");

        Mockito.when(contaRepository.findById(idConta)).thenReturn(Optional.empty());

        EntityNotFoundException entityNotFoundException = Assertions.assertThrows(EntityNotFoundException.class,
                () -> contaService.deleteConta(idConta));

    }
}