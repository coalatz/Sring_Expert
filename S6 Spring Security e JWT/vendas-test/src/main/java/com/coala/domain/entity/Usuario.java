package com.coala.domain.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import lombok.Builder;

@Entity
@Table(name = "usuario")
@Builder
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @NotEmpty(message = "{campo.login-usuario.obrigatorio}")
    @Column(name = "login")
    private String login;

    @NotEmpty(message = "{campo.senha-usuario.obrigatorio}")
    @Column(name = "senha")
    private String senha;

    @Column
    private boolean admin;


    public Usuario(Integer id, String login, String senha, boolean admin) {
        this.id = id;
        this.login = login;
        this.senha = senha;
        this.admin = admin;
    }

    public Usuario() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }
    

    


}
