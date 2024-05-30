package com.coala.rest.contoller;

import java.util.*;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.coala.domain.entity.Cliente;
import com.coala.domain.repository.Clientes;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping("/api/clientes")
@Api("Api Clientes")
public class ClienteController {

    @Autowired
    private Clientes clientes;

    @GetMapping(value = "/{id}")
    @ApiOperation("Obter detalhes de um cliente")
    @ApiResponses({
        @ApiResponse(code = 200, message = "Cliente encontrado"),
        @ApiResponse(code = 404, message = "Cliente nao existe no banco de dados")
    })
    public Cliente getClienteById(@PathVariable("id") @ApiParam("Id do cliente") Integer id) {
        return clientes
        .findById(id)
        .orElseThrow( () -> 
        new ResponseStatusException(HttpStatus.NOT_FOUND, 
        "Cliente nao encontrado")
        );
        
    }

    @PostMapping("/save")
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation("Salva cliente no banco de dados")
    @ApiResponses({
        @ApiResponse(code = 201, message = "Cliente salvo"),
        @ApiResponse(code = 400, message = "Erro de validacao")
    })
    public Cliente saveCliente(@RequestBody @Valid @ApiParam("Objeto do tipo cliente") Cliente cliente) {
        return clientes.save(cliente);
        
        
    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ApiOperation("Deleta cliente do banco de dados")
    @ApiResponses({
        @ApiResponse(code = 204, message = "Cliente deletado com sucesso"),
        @ApiResponse(code = 404, message = "Cliente nao encontrado no banco de dados")
    })
    public void deleteCliente(@PathVariable @ApiParam("Id do cliente") Integer  id) {
        clientes.findById(id)
            .map( cliente -> {
                clientes.delete(cliente);
                return cliente; 
            }).orElseThrow( ()-> 
            new ResponseStatusException(HttpStatus.NOT_FOUND, 
            "Erro ao deletar cliente"));

    }


    @PutMapping("/update/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ApiOperation("Atauliza os dados de um cliente")
    @ApiResponses({
        @ApiResponse(code = 204, message = "Cliente foi atualizado"),
        @ApiResponse(code = 404, message = "Cliente nao encontrado no banco de dados")
    })
    public void updateCliente(
        @PathVariable @ApiParam("Id do cliente") Integer id,
        @RequestBody @Valid @ApiParam("Objeto do tipo cliente") Cliente cliente) { 
        clientes.findById(id)
        .map( clienteAtualiza -> {
            cliente.setId(clienteAtualiza.getId());
            clientes.save(cliente);
            return clienteAtualiza;
        }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
        "Cliente nao encontrado"));
    }

    @GetMapping("/pesquisa")
    @ApiOperation("Obter detalhes de todos os clientes")
    @ApiResponse(code = 200, message = "Lista de clientes")
    public List<Cliente> findNome(@ApiParam("Objeto do tipo cliente")Cliente filtro) {
        ExampleMatcher matcher = ExampleMatcher.matching()
        .withIgnoreCase()
        .withStringMatcher( ExampleMatcher.StringMatcher.CONTAINING );

        Example  example = Example.of(filtro, matcher);

        return clientes.findAll(example);
        
    }
    
    
}
