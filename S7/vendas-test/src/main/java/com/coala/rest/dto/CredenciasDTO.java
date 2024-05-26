package com.coala.rest.dto;

public class CredenciasDTO {

    private String login;
    private String senha;


    public CredenciasDTO() {
    }

    public CredenciasDTO(String login, String senha) {
        this.login = login;
        this.senha = senha;
    }

    public String getLogin() {
        return this.login;
    }

    public String getSenha() {
        return this.senha;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    @Override
    public String toString() {
        return "CredenciasDTO [login=" + login + ", senha=" + senha + "]";
    }
    
}
