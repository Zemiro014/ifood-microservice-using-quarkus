package com.github.zemiro.ifood.cadastro.dto.mapper;

import com.github.zemiro.ifood.cadastro.dto.AdicionarPratoDTO;
import com.github.zemiro.ifood.cadastro.dto.AtualizarPratoDTO;
import com.github.zemiro.ifood.cadastro.entities.Prato;
import org.mapstruct.Mapper;

@Mapper(componentModel = "cdi")
public interface PratoMapperObject {
    public Prato convertAdicionarPratoDtoToPrato(AdicionarPratoDTO dto);

    public Prato convertAtualizarPratoDtoToPrato(AtualizarPratoDTO dto);
}