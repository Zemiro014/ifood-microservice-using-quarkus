package com.github.zemiro.ifood.cadastro.entities;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "restaurante")
public class Restaurente extends PanacheEntityBase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;
    public String cpnj;
    public String nome;

    @OneToOne(cascade = CascadeType.ALL)
    public Localizacao localizacao;
    public String proprietario;
    @CreationTimestamp
    public Date dataDeCriacao;

    @CreationTimestamp
    public Date dataDeAtualizacao;

    public Localizacao getLocalizacao() {
        return localizacao;
    }
}
