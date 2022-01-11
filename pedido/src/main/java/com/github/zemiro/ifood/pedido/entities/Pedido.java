package com.github.zemiro.ifood.pedido.entities;

import io.quarkus.mongodb.panache.PanacheMongoEntity;
import io.quarkus.mongodb.panache.common.MongoEntity;

import java.util.List;

@MongoEntity(collection = "pedidos", database = "pedido")
public class Pedido extends PanacheMongoEntity {

    public String client;

    public List<Prato> pratos;

    public Restaurante restaurante;

    public String entrgador;

    public Localizacao localizacaoEntregador;
}
