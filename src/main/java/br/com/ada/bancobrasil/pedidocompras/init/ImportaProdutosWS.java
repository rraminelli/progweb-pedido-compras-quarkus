package br.com.ada.bancobrasil.pedidocompras.init;

import br.com.ada.bancobrasil.pedidocompras.dto.ProdutoImportListDto;
import br.com.ada.bancobrasil.pedidocompras.entity.Produto;
import br.com.ada.bancobrasil.pedidocompras.restclient.ProdutoRestClient;
import br.com.ada.bancobrasil.pedidocompras.service.ProdutoService;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
public class ImportaProdutosWS {

    final ProdutoRestClient produtoRestClient;
    final ProdutoService produtoService;

    public ImportaProdutosWS(@RestClient ProdutoRestClient produtoRestClient,
                             ProdutoService produtoService) {
        this.produtoRestClient = produtoRestClient;
        this.produtoService = produtoService;
    }

    public void importar() {

        Response response = produtoRestClient.find("");
        ProdutoImportListDto produtoImportListDto = response.readEntity(ProdutoImportListDto.class);

        List<Produto> produtoList =
                produtoImportListDto
                        .getProducts()
                        .stream().map(prod -> {
                                    return new Produto(
                                            null,
                                            prod.getTitle(),
                                            prod.getDescription(),
                                            prod.getPrice(),
                                            prod.getDiscountPercentage(),
                                            prod.getStock(),
                                            prod.getThumbnail()
                                    );
                                }
                        )
                        .collect(Collectors.toList());

        produtoService.save(produtoList);

    }

}
