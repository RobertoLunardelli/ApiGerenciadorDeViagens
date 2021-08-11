package Utils;

import io.restassured.http.ContentType;

import static io.restassured.RestAssured.given;

public class LoginUsuario {
    public String gerarTokenUsuario() {
        String tokenUsuario =
                given()
                        .body("{\n" +
                                " \"email\": \"usuario@email.com\",\n" +
                                " \"senha\": \"123456\"\n" + "}")
                        .contentType(ContentType.JSON)
                        .when()
                        .post("v1/auth")
                        .then().extract().path("data.token");
        return tokenUsuario;
    }
}
