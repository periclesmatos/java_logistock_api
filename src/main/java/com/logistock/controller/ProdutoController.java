package com.logistock.controller;

import com.logistock.produto.*;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    @Autowired
    private ProdutoRepository repository;

    @PostMapping
    @Transactional
    public ResponseEntity<DetalharProdutoDTO> cadastrarProduto(@RequestBody @Valid CadastrarProdutoDTO dados, UriComponentsBuilder uriComponentsBuilder) {
        var produto = new Produto(dados);
        repository.save(produto);
        var uri = uriComponentsBuilder.path("produtos/{id}").buildAndExpand(produto.getId()).toUri();

        return ResponseEntity.created(uri).body(new DetalharProdutoDTO(produto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<DetalharProdutoDTO> detalharProduto(@PathVariable Long id) {
        var produto = repository.getReferenceById(id);

        return ResponseEntity.ok(new DetalharProdutoDTO(produto));
    }

    @GetMapping
    public ResponseEntity<Page<ListarProdutoDTO>> listarProdutos(@PageableDefault(size = 10, sort = {"nome"}) Pageable paginacao) {
        var page = repository.findAll(paginacao).map(ListarProdutoDTO::new);

        return  ResponseEntity.ok(page);
    }

    @PutMapping
    @Transactional
    public ResponseEntity<DetalharProdutoDTO> atualizarProduto(@RequestBody @Valid AtualizarProdutoDTO dados) {
        var produto = repository.getReferenceById(dados.id());
        produto.atualizarInformacoes(dados);

        return ResponseEntity.ok(new DetalharProdutoDTO(produto));
    }

}
