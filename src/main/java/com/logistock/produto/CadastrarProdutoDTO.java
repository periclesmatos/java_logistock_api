package com.logistock.produto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record CadastrarProdutoDTO(
        @NotBlank
        @Pattern(regexp = "^[A-Z0-9]{3}-[A-Z0-9]{3}-[A-Z0-9]{3}$", message = "SKU deve estar no formato XXX-XXX-XXX")
        String sku,
        @NotBlank
        String nome,
        String descricao,
        @NotBlank
        String categoria,
        @NotNull
        double preco,
        @NotNull
        int quantidade
) {
}
