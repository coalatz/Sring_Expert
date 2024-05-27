package com.coala.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.coala.domain.entity.ItemPedido;

public interface ItensPedidos extends JpaRepository<ItemPedido, Integer> {

}
