package br.com.ada.bancobrasil.pedidocompras.service;

import br.com.ada.bancobrasil.pedidocompras.dto.ProdutoListDto;
import br.com.ada.bancobrasil.pedidocompras.entity.Produto;

import java.util.List;

public interface ProdutoService {

    Produto save(Produto produto);

    void delete(Long idProduto);

    ProdutoListDto findAll(String filtro, int page, int size);

    Produto getById(Long id);

    void save(List<Produto> produtos);

    boolean exists();

    void updateEstoque(Long idProduto, Integer qtdeEstoque);

}
