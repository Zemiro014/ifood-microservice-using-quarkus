package com.github.zemiro.ifood.cadastro.service.api;

import com.github.zemiro.ifood.cadastro.entities.Restaurente;

import java.util.List;

public interface RestauranteService {
    public List<Restaurente> findAllRestaurant();
    public void deleteRestaurant(Long id);
    public void updateRestaurant(Long id, Restaurente dto);
    public void createRestaurant(Restaurente entity);
}
