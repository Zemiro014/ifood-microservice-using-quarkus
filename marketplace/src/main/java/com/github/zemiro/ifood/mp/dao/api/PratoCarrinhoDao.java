package com.github.zemiro.ifood.mp.dao.api;

import com.github.zemiro.ifood.mp.referenceEntity.PratoCarrinho;
import io.smallrye.mutiny.Uni;
import io.vertx.mutiny.pgclient.PgPool;

import java.util.List;

public interface PratoCarrinhoDao {
    public Uni<String> savePratoCarrinho(PgPool pgPool, String cliente, Long prato);

    public Uni<List<PratoCarrinho>> findPratoCarrinho(PgPool pgPool, String cliente);

    public Uni<Boolean> deletePratoCarrinho(PgPool client, String cliente);
}
