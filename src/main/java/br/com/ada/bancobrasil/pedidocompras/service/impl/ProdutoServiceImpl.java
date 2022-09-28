package br.com.ada.bancobrasil.pedidocompras.service.impl;

import br.com.ada.bancobrasil.pedidocompras.dto.ProdutoListDto;
import br.com.ada.bancobrasil.pedidocompras.entity.Produto;
import br.com.ada.bancobrasil.pedidocompras.repository.ProdutoRepository;
import br.com.ada.bancobrasil.pedidocompras.service.ProdutoService;
import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.panache.common.Page;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;
import java.util.List;

@ApplicationScoped
public class ProdutoServiceImpl implements ProdutoService {

    final ProdutoRepository produtoRepository;

    public ProdutoServiceImpl(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    @Override
    @Transactional
    public Produto save(Produto produto) {
        produtoRepository.persist(produto);
        return produto;
    }



    @Override
    @Transactional
    public void save(List<Produto> produtos) {
        produtoRepository.persist(produtos);
    }

    @Override
    @Transactional
    public void delete(Long idProduto) {
        produtoRepository.deleteById(idProduto);
    }

    @Override
    @Transactional(value = Transactional.TxType.NOT_SUPPORTED)
    public Produto getById(Long id) {
        return produtoRepository.findByIdOptional(id).get();
    }

    @Override
    @Transactional(value = Transactional.TxType.NOT_SUPPORTED)
    public ProdutoListDto findAll(String filter, int page, int size) {

        PanacheQuery<Produto> produtosList = produtoRepository.findByNomeOrDescricao(filter, Page.of(page, size));

        return ProdutoListDto.builder()
                .list(produtosList.list())
                .total((int)produtosList.count())
                .size(produtosList.list().size())
                .page(page)
                .build();

    }

    @Override
    @Transactional(value = Transactional.TxType.NOT_SUPPORTED)
    public boolean exists() {
        return produtoRepository.count() > 0;
    }

    @Override
    @Transactional
    public void updateEstoque(Long idProduto, Integer qtdeEstoque) {
        Produto produto = produtoRepository.findById(idProduto);
        produto.setEstoque(qtdeEstoque);
        produtoRepository.persist(produto);
    }

}
