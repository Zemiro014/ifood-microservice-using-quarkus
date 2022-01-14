package com.github.zemiro.ifood.pedido.service.api;

import com.github.zemiro.ifood.pedido.dto.PedidorealizadoDTO;
import com.github.zemiro.ifood.pedido.entities.Pedido;

public interface PedidoService {
    public void savePedido(PedidorealizadoDTO entity);
}