package com.github.zemiro.ifood.cadastro.dao.impl;

import com.github.zemiro.ifood.cadastro.dao.api.CRUDServiceGeneric;
import com.github.zemiro.ifood.cadastro.entities.Restaurente;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;
import javax.ws.rs.NotFoundException;
import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class RestauranteServiceDao extends CRUDServiceGeneric<Restaurente, Restaurente> {
    @Override
    public List<Restaurente> findAll() {
        return Restaurente.listAll();
    }

    @Override
    public void delete(Long id) {
        Optional<Restaurente> restOptional = Restaurente.findByIdOptional(id);
        restOptional.ifPresentOrElse(Restaurente::delete, () -> {
            throw new NotFoundException();
        });
    }

    @Override
    @Transactional
    public void update(Long id, Restaurente dto) {
        Optional<Restaurente> restOptional = Restaurente.findByIdOptional(id);
        if(restOptional.isEmpty()){
            throw new NotFoundException();
        }else{
            Restaurente restaurante = restOptional.get();
            restaurante.nome = dto.nome;
            restaurante.persist();
        }
    }

    @Override
    @Transactional
    public void create(Restaurente entity) {
        entity.persist();
    }
}
