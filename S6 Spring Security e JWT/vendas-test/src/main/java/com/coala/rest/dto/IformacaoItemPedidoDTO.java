package com.coala.rest.dto;

import java.math.BigDecimal;

import lombok.Builder;

@Builder
public class IformacaoItemPedidoDTO {

    private String descricaoProduto;
    private BigDecimal preco;
    private Integer quantidade;

    public IformacaoItemPedidoDTO() {
    }

    public IformacaoItemPedidoDTO(String descricaoProduto, BigDecimal preco, Integer quantidade) {
        this.descricaoProduto = descricaoProduto;
        this.preco = preco;
        this.quantidade = quantidade;
    }
    
    public String getDescricaoProduto() {
        return descricaoProduto;
    }
    public void setDescricaoProduto(String descricaoProduto) {
        this.descricaoProduto = descricaoProduto;
    }
    public BigDecimal getPreco() {
        return preco;
    }
    public void setPreco(BigDecimal preco) {
        this.preco = preco;
    }
    public Integer getQuantidade() {
        return quantidade;
    }
    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }
    @Override
    public String toString() {
        return "IformacaoItemPedidoDTO [descricaoProduto=" + descricaoProduto + ", preco=" + preco + ", quantidade="
                + quantidade + "]";
    }

    

}
