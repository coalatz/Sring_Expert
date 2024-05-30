package com.coala.rest.contoller;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import org.springframework.util.CollectionUtils;

import com.coala.domain.entity.ItemPedido;
import com.coala.domain.entity.Pedido;
import com.coala.domain.enums.StatusPedido;
import com.coala.rest.dto.AtualizacaoStatusPedidoDTO;
import com.coala.rest.dto.IformacaoItemPedidoDTO;
import com.coala.rest.dto.IformacoesPedidoDTO;
import com.coala.rest.dto.PedidoDTO;
import com.coala.service.PedidoService;

import io.swagger.annotations.*;


import static org.springframework.http.HttpStatus.*;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/pedido")
@Api("Api de Pedido")
public class PedidoController {

    private PedidoService pedidoService;

    public PedidoController (PedidoService pedidoService) {
        this.pedidoService = pedidoService;
    }

    @PostMapping("/save")
    @ResponseStatus(CREATED)
    @ApiOperation("Salva um pedido")
    @ApiResponse(code = 201, message = "Retorna pedido salvo")
    public Integer save(@RequestBody @Valid @ApiParam("Objeto PedidoDTO") PedidoDTO dto) {

        Pedido pedido = pedidoService.salvar(dto);
        return pedido.getId();
    } 

    @GetMapping("{id}")
    @ApiOperation("Obter informacoes de um pedido")
    @ApiResponses({
        @ApiResponse(code = 200, message = "Informacoes do pedido"),
        @ApiResponse(code = 404, message = "Pedido nao encontrado")
    })
    public IformacoesPedidoDTO getById(@PathVariable @ApiParam("Id do pedido") Integer id) {

        return pedidoService.obterPedidoCompleto(id)
        .map(p ->  converter(p))
        .orElseThrow( () -> new ResponseStatusException(NOT_FOUND, "Pedido nao encontrado")); 
    }

    @PatchMapping("{id}")
    @ResponseStatus(NO_CONTENT)
    @ApiOperation("Atauliza o status de um pedido")
    @ApiResponse(code = 204, message = "codigo do pedido atualizado")
    public void updateStatus( @PathVariable Integer id, 
    @RequestBody AtualizacaoStatusPedidoDTO dto) {

        String novoStatus = dto.getNovoStatus();
        pedidoService.ataulizaStatus(id, StatusPedido.valueOf(novoStatus));
    }

    private IformacoesPedidoDTO converter(Pedido pedido) {
        return IformacoesPedidoDTO
        .builder()
        .codigo(pedido.getId())
        .nomeCliente(pedido.getCliente().getNome())
        .total(pedido.getTotal())
        .status(pedido.getStatus().name())
        .items(converterItens(pedido.getItens()))
        .build();
    }

    private List<IformacaoItemPedidoDTO> converterItens(List<ItemPedido> itens) {
        if (CollectionUtils.isEmpty(itens)) {
            return Collections.emptyList();
        }
        return itens.stream().map(item -> IformacaoItemPedidoDTO.builder()
            .descricaoProduto(item.getProduto().getDescricao())
            .preco(item.getProduto().getPreco())
            .quantidade(item.getQuantidade())
            .build())
        .collect(Collectors.toList());
    }
}
