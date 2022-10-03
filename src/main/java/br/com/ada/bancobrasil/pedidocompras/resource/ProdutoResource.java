package br.com.ada.bancobrasil.pedidocompras.resource;

import br.com.ada.bancobrasil.pedidocompras.entity.Produto;
import br.com.ada.bancobrasil.pedidocompras.service.ProdutoService;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;

import javax.annotation.security.RolesAllowed;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Path("/produtos")
public class ProdutoResource {

    final ProdutoService produtoService;

    public ProdutoResource(ProdutoService produtoService) {
        this.produtoService = produtoService;
    }

    @GET
    @APIResponse(description = "Lista de produtos, filtro por nome e descricao")
    public Response findAll(@QueryParam(value = "filter") String filter,
                            @QueryParam(value = "page") int page,
                            @QueryParam(value = "size") int size) {
        return Response.ok(produtoService.findAll(filter, page, size)).build();
    }

    @RolesAllowed("ADMIN")
    @POST
    public Response save(@Valid Produto produto) {
        return Response.ok(produtoService.save(produto)).build();
    }

    //http://server:8080/produtos/1
    @GET
    @Path("/{id}")
    public Response getById(@PathParam("id") Long id) {
        return Response.ok(produtoService.getById(id)).build();
    }

    //http://server:8080/produtos/1
    @RolesAllowed("ADMIN")
    @DELETE
    @Path("/{id}")
    public void delete(@PathParam("id") Long id) {
        produtoService.delete(id);
    }

}
