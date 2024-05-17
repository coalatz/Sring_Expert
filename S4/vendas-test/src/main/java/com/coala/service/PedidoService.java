package com.coala.service;

import com.coala.domain.entity.Pedido;
import com.coala.rest.dto.PedidoDTO;

public interface PedidoService  {

    Pedido salvar(PedidoDTO dto);
}
