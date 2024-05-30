package com.coala.localizacao.domain.repository.specs;

import org.springframework.data.jpa.domain.Specification;

import com.coala.localizacao.domain.entity.Cidade;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;

public abstract class CidadeSpecs {

    public static Specification<Cidade> nomeEqual(String nome) {
        return (Root<Cidade> root,
                CriteriaQuery<?> query,
                CriteriaBuilder cb) -> cb.equal(root.get("nome"), nome);
    }

    public static Specification<Cidade> habitantesGreaterThan(Long habitantes) {
        return (Root<Cidade> root,
                CriteriaQuery<?> query,
                CriteriaBuilder cb) -> cb.greaterThan(root.get("habitantes"), habitantes);
    }

    public static Specification<Cidade> habitantesBetwenn(Long min, Long max) {
        return (Root<Cidade> root,
                CriteriaQuery<?> query,
                CriteriaBuilder cb) -> cb.between(root.get("habitantes"), min, max);
    }

    public static Specification<Cidade> nomeLike(String nome) {
        return (Root<Cidade> root,
                CriteriaQuery<?> query,
                CriteriaBuilder cb) -> cb.like(cb.upper(root.get("nome")),
                        "%" + nome + "%");
    }

    public static Specification<Cidade> idEqual(Long id) {
        return (Root<Cidade> root, CriteriaQuery<?> query, CriteriaBuilder cb) -> cb.equal(root.get("id"), id);
    }

}
