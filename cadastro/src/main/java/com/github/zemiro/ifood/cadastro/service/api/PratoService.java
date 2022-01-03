package com.github.zemiro.ifood.cadastro.service.api;

import com.github.zemiro.ifood.cadastro.entities.Prato;
import com.github.zemiro.ifood.cadastro.entities.Restaurente;

import java.util.List;

public interface PratoService {
    public List<Restaurente> findDishByRestaurant(Long restauranteId);
    public void createNewDishToRestaurante(Long restauranteId, Prato dto);
    public void updateDishOfRestaurante(Long restauranteId, Long pratoId, Prato dto);
    public void deleteDishFromRestaurant(Long restauranteId, Long pratoId);
}