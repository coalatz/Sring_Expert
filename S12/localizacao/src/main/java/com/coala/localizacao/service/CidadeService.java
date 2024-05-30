package com.coala.localizacao.service;

import java.util.List;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.coala.localizacao.domain.entity.Cidade;
import com.coala.localizacao.domain.repository.CidadeRespository;
import com.coala.localizacao.domain.repository.specs.CidadeSpecs;

@Service
public class CidadeService {

    private CidadeRespository cidadeRespository;

    public CidadeService(CidadeRespository cidadeRespository) {
        this.cidadeRespository = cidadeRespository;
    }

    public void listarCidadeNome() {
        cidadeRespository.findByNome(
                "Rio de Janeiro").forEach(System.out::println);
        System.out.println("XXX1");
        cidadeRespository.findByNomeStartingWith(
                "Rio").forEach(System.out::println);
        System.out.println("XXX2");
        cidadeRespository.findByNomeEndingWith(
                "Janeiro").forEach(System.out::println);
        System.out.println("XXX3");
    }

    public void listarCidadeNomeSqlNat() {
        cidadeRespository
                .findByNomeSqlNat("Rio de Janeiro")
                .forEach(System.out::println);
    }

    public void listarCidadesPorQuantidade() {
        Pageable pageable = PageRequest.of(
                0, 2);

        cidadeRespository.findByHabitantesGreaterThan(
                50000L, pageable).forEach(System.out::println);
    }

    public void listarCidadeHabitante() {
        cidadeRespository.findByHabitantes(
                2513456L).forEach(System.out::println);
    }

    public void listar() {
        cidadeRespository.findAll().forEach(System.out::println);
    }

    public List<Cidade> filtroDinamico(Cidade cidade) {
        Example example = Example.of(cidade);
        return cidadeRespository.findAll(example);
    }

    public void listarCidadesSByNomeSpec() {
        Specification<Cidade> spec = CidadeSpecs.nomeEqual("Banabuiu")
                .and(CidadeSpecs.habitantesGreaterThan(5));
        cidadeRespository.findAll(spec).forEach(System.out::println);
    }

    public void listarSpecsFiltroDinamico(Cidade filtro) {
        Specification<Cidade> specs = Specification.where((root, query, cb) -> cb.conjunction());

        if (filtro.getId() != null) {
            specs = specs.and(CidadeSpecs.idEqual(filtro.getId()));
        }

        if (StringUtils.hasText(filtro.getNome())) {
            specs = specs.and(CidadeSpecs.nomeLike(filtro.getNome()));
        }

        if (filtro.getHabitantes() != null) {
            specs = specs.and(CidadeSpecs.habitantesGreaterThan(filtro.getHabitantes()));
        }

        List<Cidade> resultados = cidadeRespository.findAll(specs);
        resultados.forEach(System.out::println);
    }

}
