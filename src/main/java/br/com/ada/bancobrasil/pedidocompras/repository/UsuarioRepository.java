package br.com.ada.bancobrasil.pedidocompras.repository;

import br.com.ada.bancobrasil.pedidocompras.entity.Usuario;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class UsuarioRepository implements PanacheRepositoryBase<Usuario, Long> {

    public Usuario findByEmail(String email) {
        return find("email", email).firstResult();
    }

}
