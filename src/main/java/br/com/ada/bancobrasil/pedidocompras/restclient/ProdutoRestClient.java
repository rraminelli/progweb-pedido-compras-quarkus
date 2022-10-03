package br.com.ada.bancobrasil.pedidocompras.restclient;

import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

@RegisterRestClient(baseUri = "https://dummyjson.com")
public interface ProdutoRestClient {

    @GET
    @Path(value = "/products/search")
    Response find(@QueryParam("q") String filter);

}
