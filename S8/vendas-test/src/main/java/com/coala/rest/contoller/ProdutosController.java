package com.coala.rest.contoller;

import java.util.*;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.coala.domain.entity.Produto;
import com.coala.domain.repository.Produtos;

import io.swagger.annotations.*;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@RestController
@RequestMapping("/api/produto")
@Api("Api de Produtos")
public class ProdutosController {

    @Autowired
    private Produtos produtos;

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation("Obter detalhes de um produto")
    @ApiResponses({
        @ApiResponse(code = 200, message = "Informacoes do produto"),
        @ApiResponse(code = 404, message = "Produto nao encontrado no banco de dados")
    })
    public Produto getProdutoById(@PathVariable @ApiParam("Id do produto") Integer id) {
        return produtos
               .findById(id)
               .orElseThrow( () ->
               new ResponseStatusException(HttpStatus.NOT_FOUND,
        "Produto nao Encontrado"));
    }

    @PostMapping("/save")
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation("Salvar um produto")
    @ApiResponse(code = 201, message = "Produto cadastrado no banco de dados")
    public Produto saveProduto(@RequestBody @Valid @ApiParam("Objeto do tipo Produto") Produto produto) {
        return produtos.save(produto);
    }

    @PutMapping("edit/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ApiOperation("Editar informacoes de um produto")
    @ApiResponses({
        @ApiResponse(code = 204, message = "Alteracoes feitas com sucesso"),
        @ApiResponse(code = 404, message = "Produto nao encontrado no banco de dados")
    })
    public Produto setById(@PathVariable @ApiParam("Id do produto") Integer id, 
    @RequestBody @Valid @ApiParam("Objeto de produto com novas informacoes") Produto produtoNovo) {
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
    @ApiOperation("Deletar um produto por id")
    @ApiResponses({
        @ApiResponse(code = 204, message = "Produto deletado com sucesso"),
        @ApiResponse(code = 404, message = "Produto nao encontrado no banco de dados")
    })
    public void deleteById(@PathVariable @ApiParam("Id do produto") Integer id) {
        produtos.findById(id)
                .map(produtoDel -> {
                    produtos.delete(produtoDel);
                    return produtoDel;
                }).orElseThrow( () -> 
                new ResponseStatusException(HttpStatus.NOT_FOUND,
                "Produto nao encontrado"));
    }
    
    @GetMapping("/pesquisa")
    @ApiOperation("Obter detalhes dos produtos por nome")
    @ApiResponse(code = 200, message = "Detalhes dos produtos")
    public List<Produto> find(Produto produto) {
        ExampleMatcher matcher =  ExampleMatcher.matching()
        .withIgnoreCase()
        .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);

        Example exemple = Example.of(produto, matcher);

        return produtos.findAll(exemple);
    }
    
}
