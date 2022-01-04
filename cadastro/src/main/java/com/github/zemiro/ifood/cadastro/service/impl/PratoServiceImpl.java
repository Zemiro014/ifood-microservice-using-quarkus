package com.github.zemiro.ifood.cadastro.service.impl;

import com.github.zemiro.ifood.cadastro.dao.api.PratoCRUDESerrvice;
import com.github.zemiro.ifood.cadastro.dao.impl.PratoServiceDao;
import com.github.zemiro.ifood.cadastro.dto.AdicionarPratoDTO;
import com.github.zemiro.ifood.cadastro.dto.AtualizarPratoDTO;
import com.github.zemiro.ifood.cadastro.dto.mapper.PratoMapperObject;
import com.github.zemiro.ifood.cadastro.entities.Prato;
import com.github.zemiro.ifood.cadastro.entities.Restaurente;
import com.github.zemiro.ifood.cadastro.service.api.PratoService;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.List;

@ApplicationScoped
public class PratoServiceImpl implements PratoService {
    @Inject
    private PratoMapperObject pratoMapperObject;

    private static PratoCRUDESerrvice pratoServiceDao = new PratoServiceDao();
    @Override
    public List<Restaurente> findDishByRestaurant(Long restauranteId) {
        return pratoServiceDao.findDishByRestaurant(restauranteId);
    }

    @Override
    public void createNewDishToRestaurante(Long restauranteId, AdicionarPratoDTO dto) {
        Prato entity = pratoMapperObject.convertAdicionarPratoDtoToPrato(dto);
        pratoServiceDao.createNewDishToRestaurante(restauranteId, entity);
    }

    @Override
    public void updateDishOfRestaurante(Long restauranteId, Long pratoId, AtualizarPratoDTO dto) {
        Prato entity = pratoMapperObject.convertAtualizarPratoDtoToPrato(dto);
        pratoServiceDao.updateDishOfRestaurante(restauranteId, pratoId, entity);
    }

    @Override
    public void deleteDishFromRestaurant(Long restauranteId, Long pratoId) {
        pratoServiceDao.deleteDishFromRestaurant(restauranteId, pratoId);
    }
}