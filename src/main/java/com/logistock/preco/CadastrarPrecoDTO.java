package com.logistock.preco;

import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;

public record CadastrarPrecoDTO(
        @NotNull
        int produtoId,
        @NotNull
        Double preco,
        @NotNull
        LocalDate dataInicio,
        LocalDate dataFinal
) {
}
