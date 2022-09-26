package br.com.ada;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Path("/hello")
public class GreetingResource {

    @GET
    public String hello() {
        return "Hello from RESTEasy Reactive TESTE";
    }
}