package com.github.zemiro.ifood.mp.dto;

import com.github.zemiro.ifood.mp.referenceEntity.PratoNoCarrinho;

import java.util.List;

public class CarrinhoDTO {
    private List<PratoNoCarrinho> pratos;

    public List<PratoNoCarrinho> getPratos() {
        return pratos;
    }

    public void setPratos(List<PratoNoCarrinho> pratos) {
        this.pratos = pratos;
    }
}
