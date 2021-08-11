package com.montanha.gerenciador;
import Utils.BaseApi;
import io.restassured.http.ContentType;
import org.apache.http.HttpStatus;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;
import static io.restassured.RestAssured.*;

public class ViagensRestAssured extends BaseApi {

    public String gerarTokenUsuario(){
        String tokenUsuario =
                given()
                        .body("{\n"+
                                " \"email\": \"usuario@email.com\",\n" +
                                " \"senha\": \"123456\"\n" + "}")
                        .contentType(ContentType.JSON)
                        .when()
                        .post("v1/auth")
                        .then().extract().path("data.token");
        return tokenUsuario;
    }

    public String gerarTokenAdmin(){
        String tokenAdmin =
                given()
                .body("{\n"+
                        " \"email\": \"admin@email.com\",\n" +
                        " \"senha\": \"654321\"\n" + "}")
                .contentType(ContentType.JSON)
                .when()
                .post("v1/auth")
                .then().extract().path("data.token");
        return tokenAdmin;
    }
    @Test
    public void cadastrarViagemUsandoTokenAdmin(){
        given()
                .header("Authorization", gerarTokenAdmin())
                .body("{\n"+
                        " \"acompanhante\": \"Roberto\",\n" +
                        " \"dataPartida\": \"2021-08-04\",\n" +
                        " \"dataRetorno\": \"2021-08-10\",\n" +
                        " \"localDeDestino\": \"Porto Alegre\",\n" +
                        " \"regiao\": \"Sul\"\n" + "}")
                .contentType(ContentType.JSON)
        .when()
                .post("v1/viagens")
        .then()
                .statusCode(HttpStatus.SC_CREATED)
                .log().all()
                .body(containsString("2021-08-04"))
                .body(is(notNullValue()));
                Assert.assertThat("Roberto",Matchers.is("Roberto"));
                Assert.assertThat("Sul",Matchers.is("Sul"));

    }
    @Test
    public void visualizarViagensCadastradas(){
        given()
                .header("Authorization",gerarTokenUsuario())
                .contentType(ContentType.JSON)
        .when()
                .get("v1/viagens")
        .then()
                .statusCode(HttpStatus.SC_OK)
                .body(is(notNullValue()))
                .log().all();
    }
    @Test
    public void atualizarDadosDeViagemCadastrada(){
        given()
                .header("Authorization",gerarTokenAdmin())
                .body("{\n"+
                        " \"acompanhante\": \"Pedro\",\n" +
                        " \"dataPartida\": \"2021-08-04\",\n" +
                        " \"dataRetorno\": \"2021-08-10\",\n" +
                        " \"localDeDestino\": \"Porto Alegre\",\n" +
                        " \"regiao\": \"Sul\"\n" + "}")
                .contentType(ContentType.JSON)
        .when()
                .put("v1/viagens/1")
        .then()
                .statusCode(HttpStatus.SC_NO_CONTENT)
                .body(is(notNullValue()))
                .log().all();
    }
    @Test
    public void deletarCadastroDeViagem(){
        given()
                .header("Authorization",gerarTokenAdmin())
                .contentType(ContentType.JSON)
        .when()
                .delete("v1/viagens/4")
        .then()
                .statusCode(HttpStatus.SC_NO_CONTENT)
                .body(is(notNullValue()))
                .log().all();

    }
}
