package com.coala.rest.contoller;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.coala.domain.entity.Produto;
import com.coala.domain.repository.Produtos;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@RestController
@RequestMapping("/api/produto")
public class ProdutosController {

    @Autowired
    private Produtos produtos;

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Produto getProdutoById(@PathVariable Integer id) {
        return produtos
               .findById(id)
               .orElseThrow( () ->
               new ResponseStatusException(HttpStatus.NOT_FOUND,
        "Produto nao Encontrado"));
    }

    @PostMapping("/save")
    @ResponseStatus(HttpStatus.CREATED)
    public Produto saveProduto(@RequestBody Produto produto) {
        return produtos.save(produto);
    }

    @PutMapping("edit/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Produto setById(@PathVariable Integer id, 
    @RequestBody Produto produtoNovo) {
        return produtos.findById(id)
        .map(produto -> {
            produtoNovo.setId(produto.getId());
            produtos.save(produtoNovo);
            return produtoNovo;
        }).orElseThrow( () -> 
        new ResponseStatusException(HttpStatus.NOT_FOUND,
        "Cliente nao encontrado")); 
    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable Integer id) {
        produtos.findById(id)
                .map(produtoDel -> {
                    produtos.delete(produtoDel);
                    return produtoDel;
                }).orElseThrow( () -> 
                new ResponseStatusException(HttpStatus.NOT_FOUND,
                "Produto nao encontrado"));
    }
    
    @GetMapping("/pesquisa")
    public List<Produto> find(Produto produto) {
        ExampleMatcher matcher =  ExampleMatcher.matching()
        .withIgnoreCase()
        .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);

        Example exemple = Example.of(produto, matcher);

        return produtos.findAll(exemple);
    }
    
}
