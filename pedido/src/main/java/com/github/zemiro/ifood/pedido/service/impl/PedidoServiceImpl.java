package com.github.zemiro.ifood.pedido.service.impl;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.github.zemiro.ifood.pedido.dto.PedidorealizadoDTO;
import com.github.zemiro.ifood.pedido.dto.PratoPedidoDTO;
import com.github.zemiro.ifood.pedido.elasticsearch.PedidoESService;
import com.github.zemiro.ifood.pedido.entities.Pedido;
import com.github.zemiro.ifood.pedido.entities.Prato;
import com.github.zemiro.ifood.pedido.entities.Restaurante;
import com.github.zemiro.ifood.pedido.service.api.PedidoService;
import org.bson.types.Decimal128;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.json.bind.JsonbBuilder;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class PedidoServiceImpl implements PedidoService {
    @Inject
    private PedidoESService elasticsearch;

    @Override
    public void savePedido(PedidorealizadoDTO dto) {
        Pedido pedido = new Pedido();
        pedido.setClient(dto.getCliente());
        List<Prato> pratos = new ArrayList<>();
        for (PratoPedidoDTO pratoPedido : dto.getPratos()) {
            pratos.add(from(pratoPedido));
        }
        pedido.setPratos(pratos);
        //dto.getPratos().forEach(prato -> pedido.getPratos().add(from(prato)));
        Restaurante restaurante = new Restaurante();
        restaurante.setNome(dto.getRestaurante().getNome());
        pedido.setRestaurante(restaurante);

        String json = JsonbBuilder.create().toJson(dto);
        elasticsearch.index("pedidos", json);

        pedido.persist();
    }

    private Prato from(PratoPedidoDTO dto) {
        Prato p = new Prato();
        p.setNome(dto.getNome());
        p.setDescricao(dto.getDescricao());
        p.setPreco(new Decimal128(dto.getPreco()));
        return  p;
    }
}