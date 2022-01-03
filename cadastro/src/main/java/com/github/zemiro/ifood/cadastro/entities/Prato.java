package com.github.zemiro.ifood.cadastro.entities;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "prato")
public class Prato extends PanacheEntityBase {
    @Id
    @GeneratedValue
    public Long id;

    public String nome;
    public String descricao;
    @ManyToOne
    @JoinColumn(name = "restaurante_id")
    public Restaurente restaurante;

    public BigDecimal preco;

}
