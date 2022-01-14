package com.github.zemiro.ifood.mp.service.api;

import com.github.zemiro.ifood.mp.dto.PratoDTO;
import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.Uni;
import io.vertx.mutiny.pgclient.PgPool;

public interface PratoService {
    public Multi<PratoDTO> buscarPratos(PgPool pgPool);
    public Uni<PratoDTO> buscarPratoPorId(PgPool pgPool, Long id);
    public Multi<PratoDTO> buscarPratosPorIdRestaurante(PgPool pgPool, Long restauranteId);
}