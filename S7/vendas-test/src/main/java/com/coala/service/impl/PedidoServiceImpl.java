package com.coala.service.impl;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.coala.domain.entity.*;
import com.coala.domain.enums.StatusPedido;
import com.coala.domain.repository.*;

import com.coala.exception.*;

import com.coala.rest.dto.ItemPedidoDTO;
import com.coala.rest.dto.PedidoDTO;
import com.coala.service.PedidoService;


@Service
public class PedidoServiceImpl implements PedidoService {

    private final Pedidos respository;
    private final Clientes clienteRepository;
    private final Produtos produtosRepository;
    private final ItensPedidos itemsPedidoRepository;

    


    public PedidoServiceImpl(Pedidos respository, Clientes clienteRepository, Produtos produtosRepository,
            ItensPedidos itemsPedidoRepository) {
        this.respository = respository;
        this.clienteRepository = clienteRepository;
        this.produtosRepository = produtosRepository;
        this.itemsPedidoRepository = itemsPedidoRepository;
    }


//estudar novamente para entendimento da logica 
@Override
@Transactional
public Pedido salvar(PedidoDTO dto) {

    //criando pedido cliente
    Integer clienteId = dto.getCliente();
    Cliente cliente =  clienteRepository.findById(clienteId)
    .orElseThrow(() -> 
        new RegraNegocioException("Codigo de cliente invalido")
    );

    //criando pedido 
    Pedido pedido = new Pedido();
    pedido.setTotal(dto.getTotal());
    pedido.setDataPedido(LocalDate.now());
    pedido.setCliente(cliente);

    //chamando a funcao para a conversao de itens
    List<ItemPedido> itemsPedido = converterItems(pedido, dto.getItems());
    //salvando o pedido 
    respository.save(pedido);
    //salvando itens pedido no repository
    itemsPedidoRepository.saveAll(itemsPedido);
    //passando os itens salvos pro pedido 
    pedido.setItens(itemsPedido);
    pedido.setStatus(StatusPedido.REALIZADO);

    return pedido;
}


    //funcao para a conversao de itens 
    private List<ItemPedido> converterItems(Pedido pedido, 
    List<ItemPedidoDTO> items) {
        //caso a lista de items esteja vazia ela entrar e des-
        //parar essas excepetion
        if(items.isEmpty()){
            throw new RegraNegocioException(
                "Nao e possivel realizar um pedidos sem itens");
        }

        return items.stream().map( dto -> {
            Integer idProduto = dto.getProduto();
            Produto produto = produtosRepository.findById(idProduto)
                .orElseThrow(() -> new RegraNegocioException(
                    "Codigo de produto invalido: " + idProduto));
    
            ItemPedido itemPedido = new ItemPedido();
            itemPedido.setQuantidade(dto.getQuantidade());
            itemPedido.setPedido(pedido);
            itemPedido.setProduto(produto);
            return itemPedido;
        }).collect(Collectors.toList());
    }    

    @Override
    public Optional<Pedido> obterPedidoCompleto(Integer id) {
        return respository.findByIdFetchItens(id);
    }

    @Override
    @Transactional
    public void ataulizaStatus(Integer id, StatusPedido statusPedido) {
        respository.findById(id)
        .map( p -> {
            p.setStatus(statusPedido);
            respository.save(p);
            return p;
        })
        .orElseThrow(() -> new PedidoNaoEncontradoExcepetion());
    }

}
