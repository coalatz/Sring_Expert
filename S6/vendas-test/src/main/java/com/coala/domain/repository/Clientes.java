package com.coala.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.coala.domain.entity.*;

public interface Clientes extends JpaRepository<Cliente, Integer>{
    
    @Query(value = "select * from cliente c where c.nome like '%:nome%'", nativeQuery=true)
    //@Query(value = "select c from Cliente c where c.nome like :nome")
    List<Cliente> encontrarPorNome( @Param("nome") String nome);

    List<Cliente> findAllByOrderByNome();

    //@Query( value = "select cliente, pedido from Cliente cliente, Pedido pedido where cliente.id = pedido.cliente_id", nativeQuery = true )
    @Query(value = "select c from Cliente c left join fetch c.pedidos where c.id = :id ")
    Cliente findClienteFetchPedidos(@Param("id") Integer id);

}