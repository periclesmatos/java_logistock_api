package com.logistock.produto;

import com.logistock.preco.DetalharPrecoDTO;

import java.util.List;

public record DetalharProdutoDTO(
        Long id,
        String sku,
        String nome,
        String descricao,
        String categoria,
        List<DetalharPrecoDTO> historicoDePreco
) {
    public DetalharProdutoDTO(Produto produto, List<DetalharPrecoDTO> historicoDePreco) {
        this(produto.getId(), produto.getSku(), produto.getNome(), produto.getDescricao(), produto.getCategoria(), historicoDePreco);
    }
}
