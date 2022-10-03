package br.com.ada.bancobrasil.pedidocompras.resource;

import br.com.ada.bancobrasil.pedidocompras.dto.LoginDto;
import br.com.ada.bancobrasil.pedidocompras.dto.TokenDto;
import br.com.ada.bancobrasil.pedidocompras.entity.Usuario;
import br.com.ada.bancobrasil.pedidocompras.security.JwtUtils;
import br.com.ada.bancobrasil.pedidocompras.security.PasswordUtils;
import br.com.ada.bancobrasil.pedidocompras.service.UsuarioService;
import org.apache.http.HttpStatus;
import org.eclipse.microprofile.openapi.annotations.Operation;

import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Objects;
import java.util.Set;

@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Path("/login")
public class LoginResource {

    final UsuarioService usuarioService;

    public LoginResource(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }


    @Operation(description = "Realizar login")
    @POST
    public Response salvar(@Valid LoginDto loginDto) {

        Usuario usuario = usuarioService.findByEmail(loginDto.getEmail());

        if (Objects.isNull(usuario) || !usuario.getSenha().equals(PasswordUtils.encode(loginDto.getSenha()))) {
            return Response.status(HttpStatus.SC_FORBIDDEN).build();
        }

        String token = JwtUtils.generateToken(usuario.getEmail(), Set.of(usuario.getPerfil().name()));

        return Response.ok(new TokenDto(usuario.getEmail(), token)).build();

    }

}
