package com.github.zemiro.ifood.cadastro.dto;

import org.hibernate.validator.constraints.br.CNPJ;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class AdicionarRestauranteDTO {

    @NotEmpty
    @NotNull
    @CNPJ
    public String cpnj;

    @Size(min = 3, max = 30)
    public String nome;

    public LocalizacaoDTO localizacao;
}