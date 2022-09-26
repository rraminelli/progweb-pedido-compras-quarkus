package br.com.ada.bancobrasil.pedidocompras.controller;

import br.com.ada.bancobrasil.pedidocompras.entity.Produto;
import br.com.ada.bancobrasil.pedidocompras.service.ProdutoService;

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

    //@GET
    //@ApiResponse(description = "Lista de produtos, filtro por nome e descricao")
    /*public Response findAll(@RequestParam(value = "filter", required = false) String filter,
                                                 @ParameterObject @PageableDefault(size = 20, page = 0) Pageable pageable) {
        return ResponseEntity.ok(produtoService.findAll(filter, pageable));
    }*/

    //@PreAuthorize("hasAnyRole('ADMIN')")
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
    //@PreAuthorize("hasAnyRole('ADMIN')")
    @DELETE
    @Path("/{id}")
    public void delete(@PathParam("id") Long id) {
        produtoService.delete(id);
    }

}
