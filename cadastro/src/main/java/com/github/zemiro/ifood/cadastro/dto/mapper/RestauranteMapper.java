package com.github.zemiro.ifood.cadastro.dto.mapper;

import com.github.zemiro.ifood.cadastro.dto.AdicionarRestauranteDTO;
import com.github.zemiro.ifood.cadastro.dto.AtualizarRestauranteDTO;
import com.github.zemiro.ifood.cadastro.entities.Restaurente;
import org.mapstruct.Mapper;

@Mapper(componentModel = "cdi")
public interface RestauranteMapper {
    public Restaurente adicionarRestauranteDtoToRestaurante(AdicionarRestauranteDTO dto);

    public Restaurente atualizarRestauranteToRestaurante(AtualizarRestauranteDTO dto);
}