package com.coala.service;

import java.util.Optional;

import com.coala.domain.entity.Pedido;
import com.coala.rest.dto.PedidoDTO;
import com.coala.domain.enums.StatusPedido;

public interface PedidoService  {

    Pedido salvar(PedidoDTO dto);

    Optional<Pedido> obterPedidoCompleto(Integer id);

    void ataulizaStatus(Integer id, StatusPedido statusPedido);
}
