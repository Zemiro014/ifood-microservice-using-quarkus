package com.github.zemiro.ifood.mp.dao.impl;

import com.github.zemiro.ifood.mp.dao.api.PratoCarrinhoDao;
import com.github.zemiro.ifood.mp.referenceEntity.PratoCarrinho;
import io.smallrye.mutiny.Uni;
import io.vertx.mutiny.pgclient.PgPool;
import io.vertx.mutiny.sqlclient.Row;
import io.vertx.mutiny.sqlclient.RowSet;
import io.vertx.mutiny.sqlclient.Tuple;

import javax.enterprise.context.ApplicationScoped;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class PratoCarrinhoDaoImpl implements PratoCarrinhoDao {
    @Override
    public Uni<String> savePratoCarrinho(PgPool pgPool, String cliente, Long prato) {
        String query ="INSERT INTO prato_cliente (\"prato\", \"cliente\") VALUES ("+prato+", "+"'"+cliente+"'"+") RETURNING (cliente)";
        Uni<RowSet<Row>> preparedQuery = pgPool.query(query).execute();
        return preparedQuery.map(pgRowSet -> pgRowSet.iterator().next().getString("cliente"));
    }

    @Override
    public Uni<List<PratoCarrinho>> findPratoCarrinho(PgPool pgPool, String cliente) {
        String query = "select * from prato_cliente where cliente = '"+cliente+"'";
        Uni<RowSet<Row>> preparedQuery = pgPool.query(query).execute();
        return preparedQuery.map(pgRowSet -> {
            List<PratoCarrinho> list = new ArrayList<>(pgRowSet.size());
            for (Row row : pgRowSet) {
                list.add(toPratoCarrinho(row));
            }
            return list;
        });
    }
    private static PratoCarrinho toPratoCarrinho(Row row) {
        PratoCarrinho pc = new PratoCarrinho();
        pc.setCliente(row.getString("cliente"));
        pc.setPrato(row.getLong("prato"));
        return pc;
    }

    @Override
    public Uni<Boolean> deletePratoCarrinho(PgPool client, String cliente) {
        Uni<RowSet<Row>> preparedQuery = client.query("DELETE FROM prato_cliente WHERE cliente = '"+cliente+"'").execute();
        return  preparedQuery.map(pgRowSet -> pgRowSet.rowCount() == 1);
    }
}
