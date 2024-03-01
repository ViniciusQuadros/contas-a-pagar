package com.example.contasapagar.models.enums;

public enum SituacaoConta {
    PAGO("Pago"),
    A_PAGAR("A Pagar"),
    ATRASADO("Atrasado");

    private final String descricao;
    SituacaoConta(String descricao){
        this.descricao=descricao;
    }

    public String getDescricao(){
        return descricao;
    }
}
