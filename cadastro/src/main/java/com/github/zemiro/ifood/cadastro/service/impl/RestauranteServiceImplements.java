package com.github.zemiro.ifood.cadastro.service.impl;

import com.github.zemiro.ifood.cadastro.dao.api.CRUDServiceGeneric;
import com.github.zemiro.ifood.cadastro.dao.impl.RestauranteServiceDao;
import com.github.zemiro.ifood.cadastro.entities.Restaurente;
import com.github.zemiro.ifood.cadastro.service.api.RestauranteService;

import javax.enterprise.context.ApplicationScoped;
import java.util.List;

@ApplicationScoped
public class RestauranteServiceImplements implements RestauranteService {
    private static CRUDServiceGeneric restauranteDao = new RestauranteServiceDao();
    @Override
    public List<Restaurente> findAllRestaurant() {
        return restauranteDao.findAll();
    }

    @Override
    public void deleteRestaurant(Long id) {
        restauranteDao.delete(id);
    }

    @Override
    public void updateRestaurant(Long id, Restaurente dto) {
        restauranteDao.update(id, dto);
    }

    @Override
    public void createRestaurant(Restaurente entity) {
        restauranteDao.create(entity);
    }
}
