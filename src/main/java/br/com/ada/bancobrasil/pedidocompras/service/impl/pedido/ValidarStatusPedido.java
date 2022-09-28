package br.com.ada.bancobrasil.pedidocompras.service.impl.pedido;

import br.com.ada.bancobrasil.pedidocompras.entity.Pedido;
import br.com.ada.bancobrasil.pedidocompras.entity.enums.StatusPedidoEnum;
import br.com.ada.bancobrasil.pedidocompras.repository.PedidoRepository;

import javax.annotation.Priority;
import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
@Priority(10)
public class ValidarStatusPedido implements ValidarPedido {

    final PedidoRepository pedidoRepository;

    public ValidarStatusPedido(PedidoRepository pedidoRepository) {
        this.pedidoRepository = pedidoRepository;
    }

    @Override
    public void validar(Pedido pedido) {

        if (!StatusPedidoEnum.NOVO.equals(pedido.getStatus())) {
            //pedidoRepository.persist(pedido);
        }

    }


}
