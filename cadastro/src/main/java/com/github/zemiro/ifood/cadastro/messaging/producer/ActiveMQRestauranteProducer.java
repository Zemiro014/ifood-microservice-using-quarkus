package com.github.zemiro.ifood.cadastro.messaging.producer;

import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class ActiveMQRestauranteProducer {
    @Inject
    @Channel("restaurantes")
    Emitter<String> emitter;

    public void emittingMessageToActiveMQ(String json){
        emitter.send(json);
    }
}