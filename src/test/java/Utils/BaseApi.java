package Utils;

import io.restassured.RestAssured;
import org.junit.BeforeClass;

public class BaseApi {
    @BeforeClass
    public static void baseApiUrl(){
        RestAssured.baseURI = "http://localhost";
        RestAssured.basePath = "/api";
        RestAssured.port = 8089;

    }
}
