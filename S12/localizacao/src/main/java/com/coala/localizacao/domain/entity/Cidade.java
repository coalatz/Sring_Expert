package com.coala.localizacao.domain.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "tb_cidade")
@Data
public class Cidade {

    public Cidade() {
    }

    public Cidade(Long id, String nome, Long habitantes) {
        this.id = id;
        this.nome = nome;
        this.habitantes = habitantes;
    }

    @Id
    @Column(name = "id_cidade")
    private Long id;

    @Column(name = "nome_cidade", length = 50)
    private String nome;

    @Column(name = "qtd_habitantes")
    private Long habitantes;

}
