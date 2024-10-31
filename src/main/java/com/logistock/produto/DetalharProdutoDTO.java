package com.logistock.produto;

public record DetalharProdutoDTO(
        Long id,
        String sku,
        String nome,
        String descricao,
        String categoria,
        double preco,
        int quantidade
) {
    public DetalharProdutoDTO(Produto produto) {
        this(produto.getId(),
                produto.getSku(),
                produto.getNome(),
                produto.getDescricao(),
                produto.getCategoria(),
                produto.getPreco(),
                produto.getQuantidade());
    }
}
