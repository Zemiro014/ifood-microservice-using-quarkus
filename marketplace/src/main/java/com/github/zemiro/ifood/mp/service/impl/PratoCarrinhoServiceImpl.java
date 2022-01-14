package com.github.zemiro.ifood.mp.service.impl;

import com.github.zemiro.ifood.mp.dao.api.PratoCarrinhoDao;
import com.github.zemiro.ifood.mp.dto.PedidoRealizadoDTO;
import com.github.zemiro.ifood.mp.referenceEntity.PratoCarrinho;
import com.github.zemiro.ifood.mp.service.api.PratoCarrinhoService;
import io.smallrye.mutiny.Uni;
import io.vertx.mutiny.pgclient.PgPool;
import org.eclipse.microprofile.reactive.messaging.Incoming;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.List;

@ApplicationScoped
public class PratoCarrinhoServiceImpl implements PratoCarrinhoService {

    @Inject
    private PratoCarrinhoDao pratoCarrinhoDao;

    @Override
    public Uni<String> savePratoCarrinho(PgPool pgPool, String cliente, Long prato) {
        return pratoCarrinhoDao.savePratoCarrinho(pgPool, cliente, prato);
    }

    @Override
    public Uni<List<PratoCarrinho>> findPratoCarrinho(PgPool pgPool, String cliente) {
        return pratoCarrinhoDao.findPratoCarrinho(pgPool, cliente);
    }

    @Override
    public Uni<Boolean> deletePratoCarrinho(PgPool client, String cliente) {
        return pratoCarrinhoDao.deletePratoCarrinho(client, cliente);
    }
}
