package Utils;

import io.restassured.http.ContentType;

import static io.restassured.RestAssured.given;

public class LoginAdm {
    public String gerarTokenAdmin() {
        String tokenAdmin =
                given()
                        .body("{\n" +
                                " \"email\": \"admin@email.com\",\n" +
                                " \"senha\": \"654321\"\n" + "}")
                        .contentType(ContentType.JSON)
                        .when()
                        .post("v1/auth")
                        .then().extract().path("data.token");
        return tokenAdmin;
    }
}
