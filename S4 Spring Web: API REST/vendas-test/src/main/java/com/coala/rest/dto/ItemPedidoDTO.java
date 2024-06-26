package com.coala.rest.dto;

public class ItemPedidoDTO {

    private Integer produto;
    private Integer quantidade;

    public ItemPedidoDTO(Integer produto, Integer quantidade) {
        this.produto = produto;
        this.quantidade = quantidade;
    }

    public ItemPedidoDTO() {
    }

    public Integer getProduto() {
        return produto;
    }

    public void setProduto(Integer produto) {
        this.produto = produto;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    @Override
    public String toString() {
        return "ItemPedidoDTO [produto=" + produto + ", quantidade=" + quantidade + "]";
    }

    
    
}
