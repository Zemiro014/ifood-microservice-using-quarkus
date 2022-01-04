package com.github.zemiro.ifood.cadastro;

import com.github.database.rider.cdi.api.DBRider;
import com.github.database.rider.core.api.configuration.DBUnit;
import com.github.database.rider.core.api.configuration.Orthography;
import com.github.database.rider.core.api.dataset.DataSet;
import com.github.zemiro.ifood.cadastro.utils.CadastroTestLifecycleManager;
import io.quarkus.test.common.QuarkusTestResource;
import io.quarkus.test.junit.QuarkusTest;
import org.approvaltests.Approvals;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@DBRider
@DBUnit(caseInsensitiveStrategy = Orthography.LOWERCASE)
@QuarkusTest
@QuarkusTestResource(CadastroTestLifecycleManager.class)
public class RestauranteResourceTeste {

    @Test
    @DataSet("restaurantes-ceneario-1.yml")
    public void testeBuscarrestaurante() {
        String resultado = given()
          .when().get("/restaurantes")
          .then()
             .statusCode(200)
                .extract().asString();
        Approvals.verifyJson(resultado);
    }

}