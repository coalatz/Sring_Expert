package com.coala.domain.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "item_pedido")
public class ItemPedido{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "id", insertable = false, updatable = false)
    private Pedido pedido;

    @ManyToOne
    @JoinColumn(name = "id", insertable = false, updatable = false)
    private Produto produto;
    
    @Column
    private Integer quantidade;


    public Integer getId() {
        return id;
    }
    public Pedido getPedido() {
        return pedido;
    }
    public Produto getProduto() {
        return produto;
    }
    public Integer getQuantidade() {
        return quantidade;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }
    public void setProduto(Produto produto) {
        this.produto = produto;
    }
    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }
    
    
}
