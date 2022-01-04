package com.github.zemiro.ifood.cadastro.service.api;

import com.github.zemiro.ifood.cadastro.dto.AdicionarRestauranteDTO;
import com.github.zemiro.ifood.cadastro.dto.AtualizarRestauranteDTO;
import com.github.zemiro.ifood.cadastro.entities.Restaurente;

import java.util.List;

public interface RestauranteService {
    public List<Restaurente> findAllRestaurant();
    public void deleteRestaurant(Long id);
    public void updateRestaurant(Long id, AtualizarRestauranteDTO dto);
    public void createRestaurant(AdicionarRestauranteDTO dto);
}
