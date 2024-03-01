package com.example.contasapagar.mother;

import com.example.contasapagar.dtos.ContaRecordDto;
import com.example.contasapagar.dtos.UpdateStatusContaRecordDto;
import com.example.contasapagar.models.ContaModel;
import com.example.contasapagar.models.enums.SituacaoConta;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

public class ContaMother {

    public static ContaRecordDto getContaRecordDto(){
        return new ContaRecordDto(
                LocalDate.of(2024,3,31),
                null,
                BigDecimal.valueOf(100.5),
                "Pagamento da fatura de internet",
                SituacaoConta.A_PAGAR);
    }

    public static ContaModel getContaModel(){
        return new ContaModel(
                UUID.fromString("9c4ff3b6-4418-4231-8e6b-4fab0051ed06"),
                LocalDate.of(2024,3,31),
                null,
                new BigDecimal("100.50"),
                "Pagamento da fatura de internet",
                SituacaoConta.A_PAGAR);
    }

    public static UpdateStatusContaRecordDto getUpdateStatusContaRecordDto(){
        return new UpdateStatusContaRecordDto(
                LocalDate.of(2024,3,30),
                SituacaoConta.PAGO
        );
    }

}
