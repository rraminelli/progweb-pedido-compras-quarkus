package br.com.ada.bancobrasil.pedidocompras.service;

import br.com.ada.bancobrasil.pedidocompras.dto.RealizarPedidoDto;
import br.com.ada.bancobrasil.pedidocompras.dto.RealizarPedidoResponseDto;

public interface PedidoService {

    RealizarPedidoResponseDto realizarPedido(RealizarPedidoDto realizarPedidoDto);

}
