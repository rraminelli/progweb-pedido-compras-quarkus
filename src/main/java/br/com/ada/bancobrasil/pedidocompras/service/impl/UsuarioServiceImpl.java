package br.com.ada.bancobrasil.pedidocompras.service.impl;

import br.com.ada.bancobrasil.pedidocompras.entity.Usuario;
import br.com.ada.bancobrasil.pedidocompras.entity.enums.PerfilEnum;
import br.com.ada.bancobrasil.pedidocompras.repository.UsuarioRepository;
import br.com.ada.bancobrasil.pedidocompras.security.PasswordUtils;
import br.com.ada.bancobrasil.pedidocompras.service.UsuarioService;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;

@ApplicationScoped
public class UsuarioServiceImpl implements UsuarioService {

    final UsuarioRepository usuarioRepository;

    public UsuarioServiceImpl(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    @Transactional
    public Usuario save(Usuario usuario) {
        usuario.setPerfil(PerfilEnum.CLIENTE);

        if (Objects.isNull(usuario.getId())) {
            usuario.setSenha(PasswordUtils.encode(usuario.getSenha()));
        }

        usuarioRepository.persist(usuario);

        return usuario;
    }

    @Override
    @Transactional(value = Transactional.TxType.NOT_SUPPORTED)
    public List<Usuario> findAll() {
        return usuarioRepository.findAll().list();
    }

    @Override
    @Transactional(value = Transactional.TxType.NOT_SUPPORTED)
    public Usuario getById(Long userId) {
        return usuarioRepository.findById(userId);
    }

    @Override
    @Transactional(value = Transactional.TxType.NOT_SUPPORTED)
    public Usuario findByEmail(String email) {
        return usuarioRepository.findByEmail(email);
    }

}
