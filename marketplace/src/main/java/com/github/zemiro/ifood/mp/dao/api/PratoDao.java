package com.github.zemiro.ifood.mp.dao.api;

import com.github.zemiro.ifood.mp.dto.PratoDTO;
import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.Uni;
import io.vertx.mutiny.pgclient.PgPool;

public interface PratoDao {
    public Multi<PratoDTO> findAll(PgPool pgPool);
    public Multi<PratoDTO> findAll(PgPool pgPool, Long idRestaurante);
    public Uni<PratoDTO> findById(PgPool pgPool, Long id);
}