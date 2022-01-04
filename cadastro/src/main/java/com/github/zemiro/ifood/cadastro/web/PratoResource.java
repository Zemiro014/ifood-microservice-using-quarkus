package com.github.zemiro.ifood.cadastro.web;

import com.github.zemiro.ifood.cadastro.dto.AdicionarPratoDTO;
import com.github.zemiro.ifood.cadastro.dto.AtualizarPratoDTO;
import com.github.zemiro.ifood.cadastro.entities.Prato;
import com.github.zemiro.ifood.cadastro.entities.Restaurente;
import com.github.zemiro.ifood.cadastro.service.api.PratoService;
import org.eclipse.microprofile.openapi.annotations.enums.SecuritySchemeType;
import org.eclipse.microprofile.openapi.annotations.security.OAuthFlow;
import org.eclipse.microprofile.openapi.annotations.security.OAuthFlows;
import org.eclipse.microprofile.openapi.annotations.security.SecurityRequirement;
import org.eclipse.microprofile.openapi.annotations.security.SecurityScheme;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;
import org.jboss.resteasy.annotations.jaxrs.PathParam;

import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.transaction.Transactional;
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
public class PratoResource {

    @Inject
    private PratoService pratoService;

    @GET
    @Path("{restauranteId}/pratos")
    @Tag(name = "pratos dos restaurantes")
    public Response findAllDish(@PathParam("restauranteId") Long restauranteId){
        List<Restaurente> restaurenteList = pratoService.findDishByRestaurant(restauranteId);
            return Response.ok().entity(restaurenteList).build();
    }

    @POST
    @Path("{restauranteId}/pratos")
    @Tag(name = "pratos dos restaurantes")
    @Transactional
    public Response createDish(@PathParam("restauranteId") Long restauranteId, AdicionarPratoDTO dto) {
        pratoService.createNewDishToRestaurante(restauranteId, dto);
        return Response.status(Response.Status.CREATED).build();
    }

    @PUT
    @Path("{restauranteId}/pratos/{pratoId}")
    @Tag(name = "pratos dos restaurantes")
    @Transactional
    public Response updateDish(@PathParam("restauranteId") Long restauranteId, @PathParam("pratoId") Long pratoId, AtualizarPratoDTO dto){
        System.out.println("preco: "+dto.preco);
        pratoService.updateDishOfRestaurante(restauranteId, pratoId, dto);
        return Response.noContent().build();
    }

    @DELETE
    @Path("{restauranteId}/pratos/{pratoId}")
    @Tag(name = "pratos dos restaurantes")
    @Transactional
    public Response deleteDish(@PathParam("restauranteId") Long restauranteId, @PathParam("pratoId") Long pratoId){
        pratoService.deleteDishFromRestaurant(restauranteId, pratoId);
        return Response.noContent().build();
    }
}