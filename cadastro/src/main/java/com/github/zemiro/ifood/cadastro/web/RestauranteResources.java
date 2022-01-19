package com.github.zemiro.ifood.cadastro.web;

import com.github.zemiro.ifood.cadastro.dto.AdicionarRestauranteDTO;
import com.github.zemiro.ifood.cadastro.dto.AtualizarRestauranteDTO;
import com.github.zemiro.ifood.cadastro.entities.Restaurente;
import com.github.zemiro.ifood.cadastro.service.api.RestauranteService;
import org.eclipse.microprofile.jwt.JsonWebToken;
import org.eclipse.microprofile.metrics.annotation.Counted;
import org.eclipse.microprofile.metrics.annotation.SimplyTimed;
import org.eclipse.microprofile.metrics.annotation.Timed;
import org.eclipse.microprofile.openapi.annotations.enums.SecuritySchemeType;
import org.eclipse.microprofile.openapi.annotations.security.OAuthFlow;
import org.eclipse.microprofile.openapi.annotations.security.OAuthFlows;
import org.eclipse.microprofile.openapi.annotations.security.SecurityScheme;

import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/restaurantes")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@RolesAllowed("proprietario")
@SecurityScheme(securitySchemeName = "ifood-oauth", type = SecuritySchemeType.OAUTH2, flows =
@OAuthFlows(password = @OAuthFlow(tokenUrl = "http://localhost:8180/auth/realms/ifood/protocol/openid-connect/token")))
//@SecurityRequirement(name = "ifood-oauth")
public class RestauranteResources {

    @Inject
    private RestauranteService restauranteService;

    @GET
    @Counted(name = "Quantidade de busca de restaurante")
    @SimplyTimed(name = "Tempo simples de busca")
    @Timed(name = "tempo completo de busca")
    public Response findAllRestaurants() {
        List<Restaurente> restaurentes = restauranteService.findAllRestaurant();
        return Response.ok().entity(restaurentes).build();
    }

    @POST
    @Transactional
    public Response newrestaurant(@Valid AdicionarRestauranteDTO dto){
        restauranteService.createRestaurant(dto);

        //URI uri = UriBuilder.fromPath("/{id}").build(dto.id);
        //return Response.created(uri).build();
        return Response.status(Response.Status.CREATED).build();
    }

    @PUT
    @Path("{id}")
    @Transactional
    public Response updateRestaurant(@PathParam("id") Long id, AtualizarRestauranteDTO dto){
        restauranteService.updateRestaurant(id, dto);
        return Response.noContent().build();
    }

    @DELETE
    @Path("{id}")
    @Transactional
    public Response deleteRestaurant(@PathParam("id") Long id){
        restauranteService.deleteRestaurant(id);
        return Response.noContent().build();
    }
}