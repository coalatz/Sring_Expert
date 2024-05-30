package com.coala.domain.entity;

import java.util.*;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "pedido")
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    @Column(name = "total")
    private BigDecimal total;

    public List<ItemPedido> getItens() {
        return itens;
    }
    public void setItens(List<ItemPedido> itens) {
        this.itens = itens;
    }
    @Column(name = "data_pedido", length = 20, precision = 2)
    private LocalDate dataPedido;

    @OneToMany(mappedBy = "pedido")
    private List<ItemPedido> itens;
    
    public Integer getId() {
        return id;
    }
    public Cliente getCliente() {
        return cliente;
    }
    
    public BigDecimal getTotal() {
        return total;
    }
    public LocalDate getDataPedido() {
        return dataPedido;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
    public void setTotal(BigDecimal total) {
        this.total = total;
    }
    public void setDataPedido(LocalDate dataPedido) {
        this.dataPedido = dataPedido;
    }

    @Override
    public String toString() {
        return "Pedido [id=" + id + ", cliente=" + cliente + ", total=" + total + ", dataPedido=" + dataPedido
                + ", itens=" + itens + "]";
    }

    
}
