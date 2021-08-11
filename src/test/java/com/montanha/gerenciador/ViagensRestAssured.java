package com.montanha.gerenciador;

import Utils.BaseApi;
import Utils.Dados;
import Utils.LoginAdm;
import Utils.LoginUsuario;
import io.restassured.http.ContentType;
import org.apache.http.HttpStatus;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;
import static io.restassured.RestAssured.*;

public class ViagensRestAssured extends BaseApi {

    String gerarTokenAdmin = new LoginAdm().gerarTokenAdmin();
    String gerarTokenUsuario = new LoginUsuario().gerarTokenUsuario();
    Dados dados = new Dados();
    @Test
    public void cadastrarViagemUsandoTokenAdmin() {
        given()
                .header("Authorization", gerarTokenAdmin)
                .body(dados.cadastrarViagem())
                .contentType(ContentType.JSON)
                .when()
                .post("v1/viagens")
                .then()
                .statusCode(HttpStatus.SC_CREATED)
                .body(is(notNullValue()))
                .body(containsString(dados.getNome()))
                .log().all();

    }

    @Test
    public void visualizarViagensCadastradas() {
        given()
                .header("Authorization", gerarTokenUsuario)
                .contentType(ContentType.JSON)
                .when()
                .get("v1/viagens")
                .then()
                .statusCode(HttpStatus.SC_OK)
                .body(is(notNullValue()))
                .log().all();
    }

    @Test
    public void atualizarDadosDeViagemCadastrada() {
        given()
                .header("Authorization", gerarTokenAdmin)
                .body(dados.cadastrarViagem())
                .contentType(ContentType.JSON)
                .when()
                .put("v1/viagens/1")
                .then()
                .statusCode(HttpStatus.SC_NO_CONTENT)
                .body(is(notNullValue()))
                .log().all();
    }

    @Test
    public void deletarCadastroDeViagem() {
        given()
                .header("Authorization", gerarTokenAdmin)
                .contentType(ContentType.JSON)
                .when()
                .delete("v1/viagens/4")
                .then()
                .statusCode(HttpStatus.SC_NO_CONTENT)
                .body(is(notNullValue()))
                .log().all();

    }
}
