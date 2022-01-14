package com.github.zemiro.ifood.pedido.consumer;

import com.github.zemiro.ifood.pedido.dto.PedidorealizadoDTO;
import com.github.zemiro.ifood.pedido.elasticsearch.PedidoESService;
import com.github.zemiro.ifood.pedido.service.api.PedidoService;
import org.eclipse.microprofile.reactive.messaging.Incoming;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class PedidoConsumingFromKafka {
    @Inject
    private PedidoService pedidoService;


    @Incoming("pedidos")
    public void consumingPedidos(PedidorealizadoDTO dto){
        System.out.println(dto);
        pedidoService.savePedido(dto);
    }
}
