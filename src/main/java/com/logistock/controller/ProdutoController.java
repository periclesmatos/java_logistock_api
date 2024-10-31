package com.logistock.controller;

import com.logistock.produto.CadastrarProdutoDTO;
import com.logistock.produto.DetalharProdutoDTO;
import com.logistock.produto.Produto;
import com.logistock.produto.ProdutoRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    @Autowired
    private ProdutoRepository repository;

    @PostMapping
    @Transactional
    public ResponseEntity<DetalharProdutoDTO> cadastrar(@RequestBody @Valid CadastrarProdutoDTO dados, UriComponentsBuilder uriComponentsBuilder) {
        var produto = new Produto(dados);
        repository.save(produto);
        var uri = uriComponentsBuilder.path("produtos/{id}").buildAndExpand(produto.getId()).toUri();

        return ResponseEntity.created(uri).body(new DetalharProdutoDTO(produto));
    }

}
