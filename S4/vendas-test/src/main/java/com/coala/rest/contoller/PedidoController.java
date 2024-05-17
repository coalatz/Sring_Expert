package com.coala.rest.contoller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.coala.domain.entity.Pedido;
import com.coala.rest.dto.PedidoDTO;
import com.coala.service.PedidoService;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping("/api/pedido")
public class PedidoController {

    private PedidoService pedidoService;

    public PedidoController (PedidoService pedidoService) {
        this.pedidoService = pedidoService;
    }

    @PostMapping("/save")
    @ResponseStatus(CREATED)
    public Integer save(@RequestBody PedidoDTO dto) {

        Pedido pedido = pedidoService.salvar(dto);
        return pedido.getId();
    }


}
