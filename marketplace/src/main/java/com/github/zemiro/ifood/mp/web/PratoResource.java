package com.github.zemiro.ifood.mp.web;

import com.github.zemiro.ifood.mp.dto.PratoDTO;
import com.github.zemiro.ifood.mp.referenceEntity.Prato;
import com.github.zemiro.ifood.mp.service.api.PratoService;
import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.Uni;
import io.vertx.mutiny.pgclient.PgPool;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;


@Path("/pratos")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PratoResource {

    @Inject
    PratoService pratoService;

    @Inject
    PgPool pgPool;

    @GET
    public Multi<PratoDTO> buscarPratos()
    {
        return pratoService.buscarPratos(pgPool);
    }

    @GET
    @Path("{id}")
    public Uni<PratoDTO> buscarPratoPorId(@PathParam("id") Long id){
        return pratoService.buscarPratoPorId(pgPool, id);
    }
}