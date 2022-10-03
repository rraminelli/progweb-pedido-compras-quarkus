package br.com.ada.bancobrasil.pedidocompras.init;

import br.com.ada.bancobrasil.pedidocompras.service.ProdutoService;
import lombok.extern.slf4j.Slf4j;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;

@Slf4j
@ApplicationScoped
public class ImportProdutosInit {

    final ProdutoService produtoService;
    final ImportaProdutosWS importaProdutosWS;

    public ImportProdutosInit(ProdutoService produtoService, ImportaProdutosWS importaProdutosWS) {
        this.produtoService = produtoService;
        this.importaProdutosWS = importaProdutosWS;
    }

    @Transactional
    public void startup() {
        log.info("Import produtos - INICIO");

        if (produtoService.exists()) {
            return;
        }

        importaProdutosWS.importar();

        log.info("Import produtos - FIM");
    }

}
