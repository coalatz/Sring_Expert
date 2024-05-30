package com.coala;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RestController;

import com.coala.domain.entity.*;
import com.coala.domain.repository.Clientes;
import com.coala.domain.repository.Pedidos;



@SpringBootApplication
@RestController
public class VendasApplication {


    @Bean
    public CommandLineRunner init(@Autowired Clientes clientes,
    @Autowired Pedidos pedidos) {
        return args -> {
            Cliente cliente = new Cliente();
            cliente.setNome("Coala Eduardo");
            clientes.save(cliente);

            Cliente cliente2 = new Cliente();
            cliente2.setNome("Clara Ivya");
            clientes.save(cliente2);

            Cliente cliente3 = new Cliente();
            cliente3.setNome("Yara Maria");
            clientes.save(cliente3);

            Pedido pedido = new Pedido();
            pedido.setCliente(cliente3);
            pedido.setDataPedido( LocalDate.now() );
            pedido.setTotal(BigDecimal.valueOf(100));
            pedidos.save(pedido);
            
            pedidos.findByCliente(cliente3).forEach(System.out::println);
            

        };
    }

            

    public static void main(String[] args) {
        SpringApplication.run(VendasApplication.class, args);
    }

}
