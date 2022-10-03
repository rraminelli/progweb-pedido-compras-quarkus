package br.com.ada.bancobrasil.pedidocompras.init;

import io.quarkus.runtime.Quarkus;
import io.quarkus.runtime.QuarkusApplication;

public class PedidosComprasApplication implements QuarkusApplication {

    final ImportProdutosInit importProdutosInit;

    public PedidosComprasApplication(ImportProdutosInit importProdutosInit) {
        this.importProdutosInit = importProdutosInit;
    }

    @Override
    public int run(String... args) throws Exception {

        importProdutosInit.startup();

        Quarkus.waitForExit();

        return 0;
    }

}
