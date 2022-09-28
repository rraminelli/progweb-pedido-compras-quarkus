package br.com.ada.bancobrasil.pedidocompras.repository;

import br.com.ada.bancobrasil.pedidocompras.entity.Pedido;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class PedidoRepository implements PanacheRepositoryBase<Pedido, Long> {

}
