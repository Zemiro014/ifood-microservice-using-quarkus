package com.github.zemiro.ifood.pedido.util;

import com.github.zemiro.ifood.pedido.dto.PedidorealizadoDTO;
import io.quarkus.kafka.client.serialization.ObjectMapperDeserializer;

public class PedidoDeserializer extends ObjectMapperDeserializer<PedidorealizadoDTO> {
    public PedidoDeserializer() {
        super(PedidorealizadoDTO.class);
    }
}
