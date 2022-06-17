import io.restassured.authentication.PreemptiveBasicAuthScheme;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class ApiCountryTests {

    @BeforeAll
    public static void setUpAuth() {
        PreemptiveBasicAuthScheme authScheme = new PreemptiveBasicAuthScheme();
        authScheme.setUserName("admin");
        authScheme.setPassword("admin");
        authentication = authScheme;
    }

    @BeforeAll
    public static void setUpErrorLogging() {
        enableLoggingOfRequestAndResponseIfValidationFails();
    }

    @Test
    public void shouldGetAllCountries() {
        when()
                .get("/api/countries")
                .then()
                .statusCode(200)
                .body(
                        "size()", is(10)
                );
    }

    @Test
    public void shouldCreateNewCountryWhenItUnique() {
        given()
                .contentType("application/json")
                .body(
                        "{\"countryName\": \"ru\"}"
                )
                .when()
                .post("/api/countries")
                .then()
                .statusCode(201)
                .body("id", not(empty()));
    }

    @Test
    public void shouldRenameCountryWhenItExist() {
        given()
                .contentType("application/json")
                .body("{\n" +
                        " \"id\": 1, \n" +
                        " \"countryName\": \"xx\" \n" +
                        "}")
                .when()
                .put("/api/countries/1")
                .then()
                .statusCode(200)
                .body("countryName", is("xx"));
    }

    @Test
    public void shouldDeleteCountryWhenItExist() {
        given()
                .contentType("application/json")
                .body("{\n" +
                        " \"id\": 5, \n" +
                        "}")
        .when()
                .delete("/api/countries/5")
        .then()
                .statusCode(204);
    }
}