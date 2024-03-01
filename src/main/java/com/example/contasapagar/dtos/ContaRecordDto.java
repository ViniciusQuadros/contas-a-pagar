package com.example.contasapagar.dtos;

import com.example.contasapagar.models.enums.SituacaoConta;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDate;

public record ContaRecordDto(@NotNull LocalDate dataVencimento, LocalDate dataPagamento, @NotNull BigDecimal valor, @NotBlank String descricao, @NotNull SituacaoConta situacao) {
}