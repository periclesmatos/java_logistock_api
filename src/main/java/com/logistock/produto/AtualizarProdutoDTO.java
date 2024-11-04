package com.logistock.produto;

import jakarta.validation.constraints.Pattern;

public record AtualizarProdutoDTO(
        Long id,
        String nome,
        @Pattern(regexp = "^[A-Z0-9]{3}-[A-Z0-9]{3}-[A-Z0-9]{3}$", message = "SKU deve estar no formato XXX-XXX-XXX")
        String sku,
        String descricao,
        String categoria
) {
}
