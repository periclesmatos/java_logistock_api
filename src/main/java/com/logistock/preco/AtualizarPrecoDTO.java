package com.logistock.preco;


import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record AtualizarPrecoDTO(
        @NotNull
        Long produtoId,
        @NotNull
        Double preco,
        LocalDate dataInicial,
        LocalDate dataFinal
) {
}
