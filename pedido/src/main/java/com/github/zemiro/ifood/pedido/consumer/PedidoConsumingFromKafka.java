package com.github.zemiro.ifood.pedido.consumer;

import com.github.zemiro.ifood.pedido.dto.PedidorealizadoDTO;
import org.eclipse.microprofile.reactive.messaging.Incoming;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class PedidoConsumingFromKafka {
    @Incoming("pedidos")
    public void consumingPedidos(PedidorealizadoDTO dto){
        System.out.println(dto);
    }
}
