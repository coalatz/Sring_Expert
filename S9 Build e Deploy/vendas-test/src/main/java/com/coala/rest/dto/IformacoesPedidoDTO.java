package com.coala.rest.dto;

import java.math.BigDecimal;
import java.util.List;

import lombok.Builder;

@Builder
public class IformacoesPedidoDTO {

    private Integer codigo;
    private String nomeCliente;
    private BigDecimal total;
    private String status;
    private List<IformacaoItemPedidoDTO> items;

    public IformacoesPedidoDTO() {
    }

    public IformacoesPedidoDTO(Integer codigo, String nomeCliente, BigDecimal total, String status,
            List<IformacaoItemPedidoDTO> items) {
        this.codigo = codigo;
        this.nomeCliente = nomeCliente;
        this.total = total;
        this.status = status;
        this.items = items;
    }

    

    public Integer getCodigo() {
        return codigo;
    }
    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }
    public String getNomeCliente() {
        return nomeCliente;
    }
    public void setNomeCliente(String nomeCliente) {
        this.nomeCliente = nomeCliente;
    }
    public BigDecimal getTotal() {
        return total;
    }
    public void setTotal(BigDecimal total) {
        this.total = total;
    }
    public List<IformacaoItemPedidoDTO> getItems() {
        return items;
    }
    public void setItems(List<IformacaoItemPedidoDTO> items) {
        this.items = items;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "IformacoesPedidoDTO [codigo=" + codigo + ", nomeCliente=" + nomeCliente + ", total=" + total
                + ", items=" + items + "]";
    }

    
}
