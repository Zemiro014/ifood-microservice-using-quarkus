package com.github.zemiro.ifood.pedido.entities;

import io.quarkus.mongodb.panache.PanacheMongoEntity;
import io.quarkus.mongodb.panache.common.MongoEntity;

import java.util.List;

@MongoEntity(collection = "pedidos", database = "pedido")
public class Pedido extends PanacheMongoEntity {

    private String client;
    private List<Prato> pratos;
    private Restaurante restaurante;
    private String entrgador;
    private Localizacao localizacaoEntregador;

    public String getClient() {
        return client;
    }

    public void setClient(String client) {
        this.client = client;
    }

    public List<Prato> getPratos() {
        return pratos;
    }

    public void setPratos(List<Prato> pratos) {
        this.pratos = pratos;
    }

    public Restaurante getRestaurante() {
        return restaurante;
    }

    public void setRestaurante(Restaurante restaurante) {
        this.restaurante = restaurante;
    }

    public String getEntrgador() {
        return entrgador;
    }

    public void setEntrgador(String entrgador) {
        this.entrgador = entrgador;
    }

    public Localizacao getLocalizacaoEntregador() {
        return localizacaoEntregador;
    }

    public void setLocalizacaoEntregador(Localizacao localizacaoEntregador) {
        this.localizacaoEntregador = localizacaoEntregador;
    }
}
