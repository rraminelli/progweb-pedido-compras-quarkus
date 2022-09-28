package br.com.ada.bancobrasil.pedidocompras.repository;

import br.com.ada.bancobrasil.pedidocompras.entity.Produto;
import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import io.quarkus.panache.common.Page;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ProdutoRepository implements PanacheRepositoryBase<Produto, Long> {

    public PanacheQuery<Produto> findByNomeOrDescricao(String filtro, Page page) {
        return find("nome like ?1 or descricao like ?2", "%" + filtro + "%", "%" + filtro + "%").page(page);
    }


}
