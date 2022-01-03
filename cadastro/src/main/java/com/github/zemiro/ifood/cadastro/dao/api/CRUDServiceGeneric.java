package com.github.zemiro.ifood.cadastro.dao.api;

import com.github.zemiro.ifood.cadastro.entities.Restaurente;

import java.util.List;

public abstract class  CRUDServiceGeneric<T, D> {
    public abstract List<T> findAll();
    public abstract void delete(Long id);
    public abstract void update(Long id, D dto);
    public abstract void create(T entity);
}