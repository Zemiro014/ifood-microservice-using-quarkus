package com.github.zemiro.ifood.mp.message.consume;

import com.github.zemiro.ifood.mp.referenceEntity.Restaurante;
import org.eclipse.microprofile.reactive.messaging.Incoming;

import javax.enterprise.context.ApplicationScoped;
import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;

@ApplicationScoped
public class ConsumeActiveMQRestauranteCadastrado {

    @Incoming("restaurantes")
    public void receberRestaurante(String json){
        Jsonb create = JsonbBuilder.create();
        Restaurante restaurante = create.fromJson(json, Restaurante.class);

        System.out.println("-------------------------------------------");
        System.out.println(json);
        System.out.println("-------------------------------------------");
        System.out.println(restaurante);
    }

}
