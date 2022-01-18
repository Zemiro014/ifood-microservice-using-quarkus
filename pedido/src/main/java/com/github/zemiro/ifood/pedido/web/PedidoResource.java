package com.github.zemiro.ifood.pedido.web;

import java.time.LocalDateTime;
import java.util.List;

import javax.enterprise.event.Observes;
import javax.inject.Inject;
import javax.json.bind.JsonbBuilder;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.github.zemiro.ifood.pedido.entities.Localizacao;
import com.github.zemiro.ifood.pedido.entities.Pedido;
import io.vertx.ext.web.handler.sockjs.SockJSBridgeOptions;
import org.bson.types.ObjectId;

import io.quarkus.mongodb.panache.PanacheMongoEntityBase;
import io.vertx.core.Vertx;
import io.vertx.ext.bridge.PermittedOptions;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.handler.sockjs.SockJSHandler;
import io.vertx.mutiny.core.eventbus.EventBus;
import org.jboss.logging.Logger;

@Path("/pedidos")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class PedidoResource {

    private static final Logger LOG = Logger.getLogger(PedidoResource.class);

    @Inject
    Vertx vertx;

    @Inject
    EventBus eventBus;

    void startup(@Observes Router router) {
        router.route("/localizacoes*").handler(localizacaoHandler());
    }

    private SockJSHandler localizacaoHandler() {
        SockJSHandler handler = SockJSHandler.create(vertx);
        PermittedOptions permitted = new PermittedOptions();
        permitted.setAddress("novaLocalizacao");
        SockJSBridgeOptions bridgeOptions= new SockJSBridgeOptions().addOutboundPermitted(permitted);
        handler.bridge(bridgeOptions);
        return handler;
    }

    @GET
    public List<PanacheMongoEntityBase> listarOsPedidos(){
        LOG.info("Olá Quarkus");
        LOG.infov("Olá Quarkus {0}", LocalDateTime.now());
        return Pedido.listAll();
    }

    @POST
    @Path("{idPedido}/localizacao")
    public Pedido novaLocalizacao(@PathParam("idPedido") String idPedido, Localizacao localizacao){
        Pedido pedido = Pedido.findById(new ObjectId(idPedido));
        pedido.setLocalizacaoEntregador(localizacao);
        String json = JsonbBuilder.create().toJson(localizacao);
        eventBus.send("novaLocalizacao", json);
        pedido.persistOrUpdate();
        return pedido;
    }
}
