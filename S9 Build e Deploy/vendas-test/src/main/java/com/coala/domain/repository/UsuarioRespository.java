package com.coala.domain.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.coala.domain.entity.Usuario;

@Repository
public interface UsuarioRespository extends JpaRepository<Usuario, Integer>{

    Optional<Usuario> findByLogin(String login);
}
