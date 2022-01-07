package com.github.zemiro.ifood.mp.message.consume;

import com.github.zemiro.ifood.mp.referenceEntity.Restaurante;
import com.github.zemiro.ifood.mp.service.api.RestauranteService;
import org.eclipse.microprofile.reactive.messaging.Incoming;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;
import org.jetbrains.annotations.NotNull;

@ApplicationScoped
public class ConsumeActiveMQRestauranteCadastrado {

    @Inject
    RestauranteService restauranteService;

    @Incoming("restaurantes")
    public void receberRestaurante(@NotNull String json){
        if(!json.isEmpty()){
            Jsonb create = JsonbBuilder.create();
            Restaurante restaurante = create.fromJson(json, Restaurante.class);
            restauranteService.salvarRestaurante(restaurante);
        }
    }
}
