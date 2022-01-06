package com.github.zemiro.ifood.mp.dao.impl;

import com.github.zemiro.ifood.mp.dao.api.PratoDao;
import com.github.zemiro.ifood.mp.dto.PratoDTO;
import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.Uni;
import io.vertx.mutiny.pgclient.PgPool;
import io.vertx.mutiny.sqlclient.Row;
import io.vertx.mutiny.sqlclient.RowSet;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.stream.StreamSupport;

@ApplicationScoped
public class PratoDaoImpl implements PratoDao {

    @Override
    public Multi<PratoDTO> findAll(PgPool pgPool) {
        String query = "SELECT * FROM prato";
        return makingConsult(pgPool, query);
    }

    @Override
    public Multi<PratoDTO> findAll(PgPool pgPool, Long idRestaurante) {
        String query = "SELECT * FROM prato where prato.restaurante_id = "+idRestaurante+" ORDER BY nome ASC";
        return makingConsult(pgPool, query);
    }

    @Override
    public Multi<PratoDTO> findById(PgPool pgPool, Long id) {
        String query = "SELECT * FROM prato where id = "+id;
        return makingConsult(pgPool, query);
    }

    private static Multi<PratoDTO> makingConsult(PgPool pgPool, String query){
        Uni<RowSet<Row>> preparedQuery = pgPool.query(query).execute();
        return unitToMulti(preparedQuery);
    }

    private static Multi<PratoDTO> unitToMulti(Uni<RowSet<Row>> queryResult) {
        return queryResult.onItem().transformToMulti(set -> Multi.createFrom().items(() -> {
            return StreamSupport.stream(set.spliterator(), false);
        })).onItem().transform(PratoDTO::from);
    }
}
