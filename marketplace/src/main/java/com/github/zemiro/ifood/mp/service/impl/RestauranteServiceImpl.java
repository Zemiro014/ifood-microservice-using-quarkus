package com.github.zemiro.ifood.mp.service.impl;

import com.github.zemiro.ifood.mp.referenceEntity.Localizacao;
import com.github.zemiro.ifood.mp.referenceEntity.Restaurante;
import com.github.zemiro.ifood.mp.service.api.RestauranteService;
import io.vertx.mutiny.pgclient.PgPool;
import io.vertx.mutiny.sqlclient.Tuple;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class RestauranteServiceImpl implements RestauranteService {

    @Inject
   private PgPool pgPool;

    @Override
    public void salvarRestaurante(Restaurante entity) {
        Localizacao localizacao = entity.getLocalizacao();
        pgPool.preparedQuery("insert into localizacao (id, latitude, longitude) values($1, $2, $3)")
                .execute(Tuple.of(localizacao.getId(), localizacao.getLatitude(), localizacao.getLongitude())).await()
                .indefinitely();
        pgPool.preparedQuery("insert into restaurante (id, nome, localizacao_id) values ($1, $2, $3)")
                .execute(Tuple.of(entity.getId(), entity.getNome(), localizacao.getId())).await().indefinitely();
    }
}