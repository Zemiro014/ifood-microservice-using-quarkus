package com.github.zemiro.ifood.mp.service.impl;

import com.github.zemiro.ifood.mp.dao.api.PratoDao;
import com.github.zemiro.ifood.mp.dao.impl.PratoDaoImpl;
import com.github.zemiro.ifood.mp.dto.PratoDTO;
import com.github.zemiro.ifood.mp.service.api.PratoService;
import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.Uni;
import io.vertx.mutiny.pgclient.PgPool;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class PratoServiceImpl implements PratoService {

    @Inject
    PratoDao prato;

    @Override
    public Multi<PratoDTO> buscarPratos(PgPool pgPool) {
        return prato.findAll(pgPool);
    }

    @Override
    public Uni<PratoDTO> buscarPratoPorId(PgPool pgPool, Long id) {
        return prato.findById(pgPool, id);
    }

    @Override
    public Multi<PratoDTO> buscarPratosPorIdRestaurante(PgPool pgPool, Long restauranteId) {
        return prato.findAll(pgPool, restauranteId);
    }
}
