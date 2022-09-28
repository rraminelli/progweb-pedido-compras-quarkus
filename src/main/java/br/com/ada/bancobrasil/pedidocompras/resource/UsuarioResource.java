package br.com.ada.bancobrasil.pedidocompras.resource;

import br.com.ada.bancobrasil.pedidocompras.entity.Usuario;
import br.com.ada.bancobrasil.pedidocompras.service.UsuarioService;
import lombok.extern.slf4j.Slf4j;

import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Slf4j
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Path("/usuarios")
public class UsuarioResource {

    final UsuarioService usuarioService;

    public UsuarioResource(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }


    @POST
    public Response insert(@Valid Usuario usuario) {
        log.info(usuario.toString());
        return Response.ok(usuarioService.save(usuario)).build();
    }

    ////http://localhost:8080/usuarios
    //@PreAuthorize("hasAnyRole('ADMIN')")
    @GET
    public Response getAll() {
        return Response.ok(usuarioService.findAll()).build();
    }

    //http://localhost:8080/usuarios/123
    //@PreAuthorize("hasAnyRole('ADMIN')")
    @GET
    @Path("/{id}")
    public Response getById(@PathParam("id") Long userId) {
        return Response.ok(usuarioService.getById(userId)).build();
    }


}
