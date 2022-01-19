package com.github.zemiro.ifood.cadastro.dao.impl;

import com.github.zemiro.ifood.cadastro.dao.api.CRUDServiceGeneric;
import com.github.zemiro.ifood.cadastro.entities.Restaurente;
import io.quarkus.security.ForbiddenException;
import org.eclipse.microprofile.jwt.Claim;
import org.eclipse.microprofile.jwt.Claims;
import org.eclipse.microprofile.jwt.JsonWebToken;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.NotFoundException;
import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class RestauranteServiceDao extends CRUDServiceGeneric<Restaurente, Restaurente> {

    @Inject
    JsonWebToken jwt;

    @Inject
    @Claim(standard = Claims.sub)
    String sub;

    @Override
    public List<Restaurente> findAll() {
        return Restaurente.listAll();
    }

    @Override
    public void delete(Long id) {
        Optional<Restaurente> restOptional = Restaurente.findByIdOptional(id);
        if(!restOptional.get().proprietario.equalsIgnoreCase(sub)){
            throw  new ForbiddenException();
        }
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
            System.out.println("proprietario => " +sub);
            if(!restaurante.proprietario.equalsIgnoreCase(jwt.claim("sub").toString())){
                throw  new ForbiddenException();
            }
            restaurante.nome = dto.nome;
            restaurante.persist();
        }
    }

    @Override
    @Transactional
    public void create(Restaurente entity) {
        System.out.println("proprietario => " +sub);
        entity.proprietario = sub;
        entity.persist();
    }
}
