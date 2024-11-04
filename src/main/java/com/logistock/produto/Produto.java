package com.logistock.produto;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity(name = "Produto")
@Table(name = "produtos")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Produto {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String sku;
    private String nome;
    private String descricao;
    private String categoria;
    private double preco;
    private int quantidade;

    public Produto(CadastrarProdutoDTO dados) {
        this.sku = dados.sku();
        this.nome = dados.nome();
        this.descricao = dados.descricao();
        this.categoria = dados.categoria();
        this.preco = dados.preco();
        this.quantidade = dados.quantidade();
    }

    public void atualizarInformacoes(AtualizarProdutoDTO dados) {
        if (dados.sku() != null) this.sku = dados.sku();
        if (dados.nome() != null) this.nome = dados.nome();
        if (dados.descricao() != null) this.descricao = dados.descricao();
        if (dados.categoria() != null) this.categoria = dados.categoria();
    }
}
