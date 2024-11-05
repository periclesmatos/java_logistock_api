package com.logistock.produto;

import com.logistock.preco.DetalharPrecoDTO;

import java.util.List;

public record ProdutoDTO(
        Long id,
        String sku,
        String nome,
        String descricao,
        String categoria,
        Double preco,
        int quantidade
) {

    public ProdutoDTO(Produto produto) {
        this(produto.getId(), produto.getSku(), produto.getNome(), produto.getDescricao(), produto.getCategoria(), produto.getPreco(), produto.getQuantidade());
    }
}
