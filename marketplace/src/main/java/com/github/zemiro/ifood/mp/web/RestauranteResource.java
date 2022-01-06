package com.github.zemiro.ifood.mp.web;

import com.github.zemiro.ifood.mp.dto.PratoDTO;
import com.github.zemiro.ifood.mp.service.api.PratoService;
import io.smallrye.mutiny.Multi;
import io.vertx.mutiny.pgclient.PgPool;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/restaurantes")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class RestauranteResource {

    @Inject
    PgPool pgPool;

    @Inject
    PratoService pratoService;

    @GET
    @Path("{idRestaurante}/pratos")
    public Multi<PratoDTO> buscarPratos(@PathParam("idRestaurante") Long idRestaurante){
        return pratoService.buscarPratosPorIdRestaurante(pgPool, idRestaurante);
    }
}
