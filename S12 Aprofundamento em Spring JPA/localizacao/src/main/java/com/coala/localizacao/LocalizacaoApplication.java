package com.coala.localizacao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.coala.localizacao.domain.entity.Cidade;
import com.coala.localizacao.service.CidadeService;

@SpringBootApplication
public class LocalizacaoApplication implements CommandLineRunner {

	@Autowired
	private CidadeService cidadeService;

	@Override
	public void run(String... args) throws Exception {
		Cidade cidade = new Cidade(3L, null, null);
		cidadeService.listarSpecsFiltroDinamico(cidade);
	}

	public static void main(String[] args) {
		SpringApplication.run(LocalizacaoApplication.class, args);
	}

}
