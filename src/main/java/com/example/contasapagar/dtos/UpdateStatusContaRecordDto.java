package com.example.contasapagar.dtos;

import com.example.contasapagar.models.enums.SituacaoConta;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record UpdateStatusContaRecordDto(LocalDate dataPagamento, @NotNull SituacaoConta situacao) {
}
