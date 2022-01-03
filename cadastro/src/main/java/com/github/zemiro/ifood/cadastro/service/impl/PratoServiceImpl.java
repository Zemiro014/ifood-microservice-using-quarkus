package com.github.zemiro.ifood.cadastro.service.impl;

import com.github.zemiro.ifood.cadastro.dao.api.PratoCRUDESerrvice;
import com.github.zemiro.ifood.cadastro.dao.impl.PratoServiceDao;
import com.github.zemiro.ifood.cadastro.entities.Prato;
import com.github.zemiro.ifood.cadastro.entities.Restaurente;
import com.github.zemiro.ifood.cadastro.service.api.PratoService;

import javax.enterprise.context.ApplicationScoped;
import java.util.List;

@ApplicationScoped
public class PratoServiceImpl implements PratoService {
    private static PratoCRUDESerrvice pratoServiceDao = new PratoServiceDao();
    @Override
    public List<Restaurente> findDishByRestaurant(Long restauranteId) {
        return pratoServiceDao.findDishByRestaurant(restauranteId);
    }

    @Override
    public void createNewDishToRestaurante(Long restauranteId, Prato dto) {
        pratoServiceDao.createNewDishToRestaurante(restauranteId, dto);
    }

    @Override
    public void updateDishOfRestaurante(Long restauranteId, Long pratoId, Prato dto) {
        pratoServiceDao.updateDishOfRestaurante(restauranteId, pratoId, dto);
    }

    @Override
    public void deleteDishFromRestaurant(Long restauranteId, Long pratoId) {
        pratoServiceDao.deleteDishFromRestaurant(restauranteId, pratoId);
    }
}