package com.logistock.preco;

import java.time.LocalDate;

public record DetalharPrecoDTO(
        Double preco,
        LocalDate dataInicio,
        LocalDate dataFinal
) {
    public DetalharPrecoDTO(Preco preco) {
        this(preco.getPreco(), preco.getDataInicio(), preco.getDataFinal());
    }
}
