package com.github.zemiro.ifood.cadastro.dao.impl;

import com.github.zemiro.ifood.cadastro.dao.api.PratoCRUDESerrvice;
import com.github.zemiro.ifood.cadastro.entities.Prato;
import com.github.zemiro.ifood.cadastro.entities.Restaurente;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.NotFoundException;
import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class PratoServiceDao implements PratoCRUDESerrvice {

    @Override
    public List<Restaurente> findDishByRestaurant(Long restauranteId) {
        Optional<Restaurente> restaurenteOpt = Restaurente.findByIdOptional(restauranteId);
        if(restaurenteOpt.isEmpty())
            throw new NotFoundException("Restaurante não existe");
        else
            return Prato.list("restaurante", restaurenteOpt.get());
    }

    @Override
    public void createNewDishToRestaurante(Long restauranteId, Prato dto) {
        Optional<Restaurente> restaurenteOpt = Restaurente.findByIdOptional(restauranteId);
        if (restaurenteOpt.isEmpty()){
            throw new NotFoundException("Restaurante não existe");
        }
        Prato prato = new Prato();
        prato.nome = dto.nome;
        prato.descricao = dto.descricao;
        prato.preco = dto.preco;
        prato.restaurante = restaurenteOpt.get();
        prato.persist();
    }

    @Override
    public void updateDishOfRestaurante(Long restauranteId, Long pratoId, Prato dto) {
        Optional<Restaurente> restaurenteOpt = Restaurente.findByIdOptional(restauranteId);
        if (restaurenteOpt.isEmpty()){
            throw new NotFoundException("Restaurante não existe");
        }
        Optional<Prato> pratoOptional = Prato.findByIdOptional(pratoId);
        if(pratoOptional.isEmpty()){
            throw new NotFoundException("Prato não existe");
        }
        Prato prato = pratoOptional.get();
        prato.preco = dto.preco;
        prato.persist();
    }

    @Override
    public void deleteDishFromRestaurant(Long restauranteId, Long pratoId) {
        Optional<Restaurente> restaurenteOpt = Restaurente.findByIdOptional(restauranteId);
        if (restaurenteOpt.isEmpty()){
            throw new NotFoundException("Restaurante não existe");
        }
        Optional<Prato> pratoOptional = Prato.findByIdOptional(pratoId);
        pratoOptional.ifPresentOrElse(Prato::delete, () -> {throw new NotFoundException("Prato não existe");});
    }
}
