package com.coala.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.coala.domain.entity.Produto;

public interface Produtos extends JpaRepository<Produto, Integer>{

}
