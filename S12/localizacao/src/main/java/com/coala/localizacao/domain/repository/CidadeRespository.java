package com.coala.localizacao.domain.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.coala.localizacao.domain.entity.Cidade;

public interface CidadeRespository extends JpaRepository<Cidade, Long>, JpaSpecificationExecutor<Cidade> {

        // Query Nativas
        @Query(nativeQuery = true, value = "select * from tb_cidade where as c where c.nome_cidade =:nome")
        List<Cidade> findByNomeSqlNat(@Param("nome") String nome);

        // Query Methods
        @Query("select c from Cidade c where upper(c.nome) like upper(?1)")
        List<Cidade> findByNome(String nome_cidade);

        // comecando com
        List<Cidade> findByNomeStartingWith(String nome_cidade);

        // terminando com
        List<Cidade> findByNomeEndingWith(String nome_cidade);

        // nome que contem uma parte do string
        List<Cidade> findByNomeContaining(String nome_cidade);

        List<Cidade> findByHabitantes(Long habitantes);

        // cidades com habitantes menor que
        List<Cidade> findByHabitantesLessThan(long habitantes);

        // cidades com habitantes maior que ordernado
        List<Cidade> findByHabitantesGreaterThan(
                        long habitantes, Sort sort);

        // cidades com habitantes maior que paginado
        Page<Cidade> findByHabitantesGreaterThan(
                        long habitantes, Pageable pageable);

        // cidades por habitante maior que e com nome asssim
        List<Cidade> findByHabitantesGreaterThanAndNomeLike(
                        long habitantes, String nome);

}
