package com.coala.rest.contoller;

import java.util.*;

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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping("/api/clientes")
public class ClienteController {

    @Autowired
    private Clientes clientes;

    @GetMapping(value = "/{id}")
    public Cliente getClienteById(@PathVariable("id") Integer id) {
        return clientes
        .findById(id)
        .orElseThrow( () -> 
        new ResponseStatusException(HttpStatus.NOT_FOUND, 
        "Cliente nao encontrado")
        );
        
    }

    @PostMapping("/save")
    @ResponseStatus(HttpStatus.CREATED)
    public Cliente saveCliente(@RequestBody Cliente cliente) {
        return clientes.save(cliente);
        
        
    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCliente(@PathVariable Integer id) {
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
    public void updateCliente(@PathVariable Integer id, @RequestBody Cliente cliente) { 
        clientes.findById(id)
        .map( clienteAtualiza -> {
            cliente.setId(clienteAtualiza.getId());
            clientes.save(cliente);
            return clienteAtualiza;
        }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
        "Cliente nao encontrado"));
    }

    @GetMapping("/pesquisa")
    public List<Cliente> findNome(Cliente filtro) {
        ExampleMatcher matcher = ExampleMatcher.matching()
        .withIgnoreCase()
        .withStringMatcher( ExampleMatcher.StringMatcher.CONTAINING );

        Example  example = Example.of(filtro, matcher);

        return clientes.findAll(example);
        
    }
    
    
}
