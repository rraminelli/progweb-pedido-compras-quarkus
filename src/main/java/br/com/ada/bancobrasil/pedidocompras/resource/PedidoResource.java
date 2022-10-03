package br.com.ada.bancobrasil.pedidocompras.resource;

import br.com.ada.bancobrasil.pedidocompras.dto.RealizarPedidoDto;
import br.com.ada.bancobrasil.pedidocompras.service.PedidoService;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Path("/pedido")
public class PedidoResource {

    final PedidoService pedidoService;

    public PedidoResource(PedidoService pedidoService) {
        this.pedidoService = pedidoService;
    }

    @RolesAllowed("CLIENTE")
    @POST
    public Response realizarPedido(RealizarPedidoDto realizarPedidoDto) {
        return Response.ok(pedidoService.realizarPedido(realizarPedidoDto)).build();
    }

}
