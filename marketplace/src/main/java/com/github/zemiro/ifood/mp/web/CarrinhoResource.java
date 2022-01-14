package com.github.zemiro.ifood.mp.web;

import com.github.zemiro.ifood.mp.dto.PedidoRealizadoDTO;
import com.github.zemiro.ifood.mp.dto.PratoDTO;
import com.github.zemiro.ifood.mp.dto.PratoPedidoDTO;
import com.github.zemiro.ifood.mp.dto.RestauranteDTO;
import com.github.zemiro.ifood.mp.referenceEntity.Prato;
import com.github.zemiro.ifood.mp.referenceEntity.PratoCarrinho;
import com.github.zemiro.ifood.mp.service.api.PratoCarrinhoService;
import com.github.zemiro.ifood.mp.service.api.PratoService;
import io.smallrye.mutiny.Uni;
import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;
import java.util.stream.Collectors;

@Path("carrinho")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CarrinhoResource {

    private static final String CLIENTE = "a";

    @Inject
    io.vertx.mutiny.pgclient.PgPool client;

    @Inject
    PratoCarrinhoService pratoCarrinhoService;

    @Inject
    PratoService pratoService;

    @Inject
    @Channel("pedidos")
    Emitter<PedidoRealizadoDTO> emitterPedido;

    @GET
    public Uni<List<PratoCarrinho>> buscarcarrinho() {
        return pratoCarrinhoService.findPratoCarrinho(client, CLIENTE);
    }

    @POST
    @Path("/prato/{idPrato}")
    public Uni<String> adicionarPrato(@PathParam("idPrato") Long idPrato) {
        //poderia retornar o pedido atual
        PratoCarrinho pc = new PratoCarrinho();
        pc.setCliente(CLIENTE);
        pc.setPrato(idPrato);
        return pratoCarrinhoService.savePratoCarrinho(client, CLIENTE, idPrato);
    }

    @POST
    @Path("/realizar-pedido")
    public Uni<Boolean> finalizar() {
        PedidoRealizadoDTO pedido = new PedidoRealizadoDTO();
        String cliente = CLIENTE;
        pedido.setCliente(cliente);
        List<PratoCarrinho> pratoCarrinho = pratoCarrinhoService.findPratoCarrinho(client, cliente).await().indefinitely();
        //Utilizar mapstruts
        List<PratoPedidoDTO> pratos = pratoCarrinho.stream().map(pc -> from(pc)).collect(Collectors.toList());
        pedido.setPratos(pratos);

        RestauranteDTO restaurante = new RestauranteDTO();
        restaurante.setNome("nome restaurante");
        pedido.setRestaurante(restaurante);

        //Enviando pedido no kafka
        emitterPedido.send(pedido);
        return pratoCarrinhoService.deletePratoCarrinho(client, cliente);
    }

    private PratoPedidoDTO from(PratoCarrinho pratoCarrinho) {
        PratoDTO dto = pratoService.buscarPratoPorId(client, pratoCarrinho.getPrato()).await().indefinitely();
        return new PratoPedidoDTO(dto.getNome(), dto.getDescricao(), dto.getPreco());
    }
}