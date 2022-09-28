package br.com.ada.bancobrasil.pedidocompras.service.impl;

import br.com.ada.bancobrasil.pedidocompras.dto.RealizarPedidoDto;
import br.com.ada.bancobrasil.pedidocompras.dto.RealizarPedidoResponseDto;
import br.com.ada.bancobrasil.pedidocompras.entity.ItemPedido;
import br.com.ada.bancobrasil.pedidocompras.entity.Pedido;
import br.com.ada.bancobrasil.pedidocompras.entity.Produto;
import br.com.ada.bancobrasil.pedidocompras.entity.Usuario;
import br.com.ada.bancobrasil.pedidocompras.entity.enums.StatusPedidoEnum;
import br.com.ada.bancobrasil.pedidocompras.repository.PedidoRepository;
import br.com.ada.bancobrasil.pedidocompras.service.PedidoService;
import br.com.ada.bancobrasil.pedidocompras.service.ProdutoService;
import br.com.ada.bancobrasil.pedidocompras.service.UsuarioService;
import br.com.ada.bancobrasil.pedidocompras.service.impl.pedido.ValidarPedido;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Instance;
import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.stream.Collectors;

@ApplicationScoped
public class PedidoServiceImpl implements PedidoService {

    final PedidoRepository pedidoRepository;
    final ProdutoService produtoService;
    final Instance<ValidarPedido> validacoes;
    final UsuarioService usuarioService;

    public PedidoServiceImpl(PedidoRepository pedidoRepository,
                             ProdutoService produtoService,
                             Instance<ValidarPedido> validacoes,
                             UsuarioService usuarioService) {
        this.pedidoRepository = pedidoRepository;
        this.produtoService = produtoService;
        this.validacoes = validacoes;
        this.usuarioService = usuarioService;
    }

    @Override
    @Transactional
    public RealizarPedidoResponseDto realizarPedido(final RealizarPedidoDto realizarPedidoDto) {

        final Pedido pedido = this.criarPedido(realizarPedidoDto);

        validacoes.forEach(validarPedido -> validarPedido.validar(pedido));

        return new RealizarPedidoResponseDto(pedido.getId());
    }

    private Pedido criarPedido(RealizarPedidoDto realizarPedidoDto) {

        final Usuario usuario = usuarioService.getById(1L);

        final Pedido pedido = Pedido.builder()
                .dataPedido(LocalDateTime.now())
                .status(StatusPedidoEnum.NOVO)
                .usuario(usuario) //TODO: SpringSecurity
                .build();

        final Set<ItemPedido> itemPedidos = realizarPedidoDto.getItens().stream()
                .map(pedidoItemDto -> {

                    final Produto produto = produtoService.getById(pedidoItemDto.getProdutoId());

                    return ItemPedido.builder()
                            .quantidade(pedidoItemDto.getQuantidade())
                            .produto(produto)
                            .preco(produto.getPreco())
                            .desconto(produto.getDesconto())
                            .pedido(pedido)
                            .build();
                })
                .collect(Collectors.toSet());

        pedido.setItens(itemPedidos);

        pedidoRepository.persist(pedido);

        return pedido;

    }

}
