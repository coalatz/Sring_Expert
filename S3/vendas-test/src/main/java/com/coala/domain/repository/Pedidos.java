package com.coala.domain.repository;

import java.util.*;

import org.springframework.data.jpa.repository.JpaRepository;

import com.coala.domain.entity.Cliente;
import com.coala.domain.entity.Pedido;

public interface Pedidos extends JpaRepository<Pedido, Integer> {
    List<Pedido> findByCliente(Cliente cliente);
}
