package com.github.zemiro.ifood.cadastro.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

public class AdicionarPratoDTO {

    @NotNull
    @NotEmpty
    public String nome;
    public String descricao;

    @NotNull
    public BigDecimal preco;
}
