package com.coala.domain.repository;

import java.util.*;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.coala.domain.entity.Cliente;
import com.coala.domain.entity.Pedido;


public interface Pedidos extends JpaRepository<Pedido, Integer> {
    List<Pedido> findByCliente(Cliente cliente);

    @Query(" select p from Pedido p left join fetch p.itens where p.id = :id ") 
    Optional<Pedido> findByIdFetchItens(@Param("id") Integer id);
}
