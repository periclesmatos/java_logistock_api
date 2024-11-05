package com.logistock.service;

import com.logistock.preco.AtualizarPrecoDTO;
import com.logistock.preco.Preco;
import com.logistock.preco.PrecoRepository;
import com.logistock.produto.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Objects;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private PrecoRepository precoRepository;

    @Transactional
    public Produto cadastrarProduto(CadastrarProdutoDTO dados) {
        var produto = new Produto(dados);
        produtoRepository.save(produto);
        return produto;
    }

    @Transactional
    public Produto buscarProduto(Long id) {
        return produtoRepository.getReferenceById(id);
    }

    @Transactional
    public Page<ListarProdutoDTO> buscarProdutos(Pageable paginacao) {
        return produtoRepository.findAll(paginacao).map(ListarProdutoDTO::new);
    }

    @Transactional
    public Produto atualizarProduto(AtualizarProdutoDTO dados) {
        var produto = produtoRepository.getReferenceById(dados.id());
        produto.atualizarInformacoes(dados);
        return produto;
    }

    @Transactional
    public Produto atualizarPreco(AtualizarPrecoDTO dados) {
        var produto = produtoRepository.getReferenceById(dados.produtoId());
        var ultimoPreco = produto.getHistoricoDePreco().stream()
                .filter(preco -> preco.getDataFinal() == null)
                .findFirst();

        ultimoPreco.ifPresent(preco -> preco.setDataFinal(LocalDate.now()));
        produto.setPreco(dados.preco());

        var novoregistroDePreco = new Preco(dados.preco(), produto);
        produto.getHistoricoDePreco().add(novoregistroDePreco);

        produtoRepository.save(produto);
        return produto;
    }
}
