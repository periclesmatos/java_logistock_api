package com.logistock.produto;

public record ListarProdutoDTO(
        Long id,
        String sku,
        String nome,
        String descricao,
        String categoria,
        double preco,
        int quantidade
) {
    public ListarProdutoDTO(Produto produto) {
        this(produto.getId(),
                produto.getSku(),
                produto.getNome(),
                produto.getDescricao(),
                produto.getCategoria(),
                produto.getPreco(),
                produto.getQuantidade());
    }
}
