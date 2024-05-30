package com.coala.exception;

public class PedidoNaoEncontradoExcepetion extends RuntimeException{

    public PedidoNaoEncontradoExcepetion() {
        super("Pedido nao encontrado.");
    }
}
