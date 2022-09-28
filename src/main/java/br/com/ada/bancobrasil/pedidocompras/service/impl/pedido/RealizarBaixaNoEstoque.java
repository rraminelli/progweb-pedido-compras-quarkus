package br.com.ada.bancobrasil.pedidocompras.service.impl.pedido;

import br.com.ada.bancobrasil.pedidocompras.entity.Pedido;
import br.com.ada.bancobrasil.pedidocompras.entity.Produto;
import br.com.ada.bancobrasil.pedidocompras.entity.enums.StatusPedidoEnum;
import br.com.ada.bancobrasil.pedidocompras.service.ProdutoService;
import lombok.extern.slf4j.Slf4j;

import javax.annotation.Priority;
import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;

@Slf4j
@ApplicationScoped
@Priority(40)
public class RealizarBaixaNoEstoque implements ValidarPedido {

    final ProdutoService produtoService;

    public RealizarBaixaNoEstoque(ProdutoService produtoService) {
        this.produtoService = produtoService;
    }


    @Transactional
    public void validar(Pedido pedido) {
        log.info("RealizarBaixaNoEstoque");

        if (!StatusPedidoEnum.NOVO.equals(pedido.getStatus())) {
            return;
        }

        pedido.getItens().forEach(item -> {
            log.info("Produto: {}", item.getProduto().getId());
            item.getProduto().setEstoque(item.getProduto().getEstoque() - item.getQuantidade());
            produtoService.updateEstoque(item.getProduto().getId(), item.getProduto().getEstoque());
        });

    }

}
