package com.logistock.produto;

import com.logistock.preco.Preco;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

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
    @Setter
    private double preco;
    private int quantidade;

    @OneToMany(mappedBy = "produto", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Preco> historicoDePreco = new ArrayList<>();

    public Produto(CadastrarProdutoDTO dados) {
        this.sku = dados.sku();
        this.nome = dados.nome();
        this.descricao = dados.descricao();
        this.categoria = dados.categoria();
        this.preco = dados.preco();
        this.quantidade = dados.quantidade();

        Preco precoInicial = new Preco(dados.preco(), this);
        this.historicoDePreco.add(precoInicial);
    }

    public void atualizarInformacoes(AtualizarProdutoDTO dados) {
        if (dados.sku() != null) this.sku = dados.sku();
        if (dados.nome() != null) this.nome = dados.nome();
        if (dados.descricao() != null) this.descricao = dados.descricao();
        if (dados.categoria() != null) this.categoria = dados.categoria();
    }
}
