package com.logistock.controller;

import com.logistock.preco.AtualizarPrecoDTO;
import com.logistock.preco.DetalharPrecoDTO;
import com.logistock.produto.*;
import com.logistock.service.ProdutoService;
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
import java.util.stream.Collector;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    @Autowired
    private ProdutoService produtoService;

    @PostMapping
    public ResponseEntity<ProdutoDTO> cadastrarProduto(@RequestBody @Valid CadastrarProdutoDTO dados, UriComponentsBuilder uriComponentsBuilder) {
        var produto = produtoService.cadastrarProduto(dados);
        var uri = uriComponentsBuilder.path("produtos/{id}").buildAndExpand(produto.getId()).toUri();
        return ResponseEntity.created(uri).body(new ProdutoDTO(produto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<DetalharProdutoDTO> detalharProduto(@PathVariable Long id) {
        var produto = produtoService.buscarProduto(id);
        List<DetalharPrecoDTO> historicoDePreco = produto.getHistoricoDePreco().stream()
                .map(DetalharPrecoDTO::new)
                .collect(Collectors.toList());

        DetalharProdutoDTO detalharProdutoDTO = new DetalharProdutoDTO(produto, historicoDePreco);
        return ResponseEntity.ok(detalharProdutoDTO);
    }

    @GetMapping
    public ResponseEntity<Page<ListarProdutoDTO>> listarProdutos(@PageableDefault(size = 10, sort = {"nome"}) Pageable paginacao) {
        var page = produtoService.buscarProdutos(paginacao);
        return  ResponseEntity.ok(page);
    }

    @PutMapping
    public ResponseEntity<ProdutoDTO> atualizarProduto(@RequestBody @Valid AtualizarProdutoDTO dados) {
        var produto = produtoService.atualizarProduto(dados);
        return ResponseEntity.ok(new ProdutoDTO(produto));
    }

    @PutMapping("/preco")
    public  ResponseEntity<ProdutoDTO> atualizarPreco(@RequestBody @Valid AtualizarPrecoDTO dados) {
        var produto = produtoService.atualizarPreco(dados);
        return ResponseEntity.ok(new ProdutoDTO(produto));
    }

}
