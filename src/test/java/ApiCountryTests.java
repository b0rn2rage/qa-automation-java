import io.restassured.authentication.PreemptiveBasicAuthScheme;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.sql.*;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class ApiCountryTests {
    private static Connection connection;

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

    @BeforeAll
    public static void connect() throws SQLException {
        connection = DriverManager.getConnection(
                "jdbc:postgresql://localhost/app-db",
                "app-db-admin",
                "P@ssw0rd"
        );
    }

    @AfterAll
    public static void disconnect() throws SQLException {
        connection.close();
    }

    /**
     * Дропаем все данные в таблице
     * Добавляем две записи
     * Геттером считываем все записи из таблицы
     * Проверяем, что количество записей = 2
     * Удаляем все после себя
     */
    @Test
    public void shouldGetAllCountries() throws SQLException {
        Statement sql = connection.createStatement();
        sql.executeUpdate("DELETE FROM country");
        PreparedStatement preparedSql = connection.prepareStatement(
                "INSERT INTO country (id, country_name) VALUES (?, ?), (?, ?)"
        );
        preparedSql.setInt(1, 1);
        preparedSql.setString(2, "ru");
        preparedSql.setInt(3, 2);
        preparedSql.setString(4, "en");
        preparedSql.executeUpdate();

        try {
            when()
                    .get("/api/countries")
                    .then()
                    .statusCode(200)
                    .body(
                            "size()", equalTo(2)
                    );
        } finally {
            preparedSql = connection.prepareStatement(
                    "DELETE FROM country WHERE country_name in(?, ?)"
            );
            preparedSql.setString(1, "ru");
            preparedSql.setString(2, "en");
            preparedSql.executeUpdate();
        }
    }

    /**
     * На всякий случай вначале тесте удаляем country_name = 'ru' из теста (мало-ли кто-то добавил)
     * Создаем страну через АПИ
     * Проверяем, что страна создалась (айди не пустой, имя страны = ru)
     * Удаляем после себя созданную страну
     */
    @Test
    public void shouldCreateNewCountryWhenItUnique() throws SQLException {
        PreparedStatement preparedSql = connection.prepareStatement(
                "DELETE FROM country WHERE country_name = ?"
        );
        preparedSql.setString(1, "ru");
        preparedSql.executeUpdate();

        try {
            given()
                    .contentType("application/json")
                    .body(
                            "{\"countryName\": \"ru\"}"
                    )
                    .when()
                    .post("/api/countries")
                    .then()
                    .statusCode(201)
                    .body(
                            "id", not(empty()),
                            "countryName", equalTo("ru")
                    );
        }
        finally {
            preparedSql = connection.prepareStatement(
                    "DELETE FROM country WHERE country_name = ?"
            );
            preparedSql.setString(1, "ru");
            preparedSql.executeUpdate();
        }
    }

    /**
     * Дропаем на всякий случай все данные в таблице
     * Инсертим запись с country_name = ru
     * В тесте при помощи АПИ делаем ренейм страны на xx
     * Проверяем, что ренейм действительно прошел и после этого удаляем измененную запись
     */
    @Test
    public void shouldRenameCountryWhenItExist() throws SQLException {
        Statement sql = connection.createStatement();
        sql.executeUpdate("DELETE FROM country");
        PreparedStatement preparedSql = connection.prepareStatement(
                "INSERT INTO country (id, country_name) VALUES (?, ?)"
        );
        preparedSql.setInt(1, 1);
        preparedSql.setString(2, "ru");
        preparedSql.executeUpdate();

        try {
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
        } finally {
            preparedSql = connection.prepareStatement(
                    "DELETE FROM country WHERE country_name = ?"
            );
            preparedSql.setString(1, "ru");
            preparedSql.executeUpdate();
        }
    }

    /**
     * Дропаем на всякий случай данные в таблице
     * Добавляем 1 запись
     * Удаляем ее с помощью АПИ
     * Если были ошибки в АПИ то перестраховываемся и удаляем сами запись из таблицы
     */
    @Test
    public void shouldDeleteCountryWhenItExist() throws SQLException {
        Statement sql = connection.createStatement();
        sql.executeUpdate("DELETE FROM country");
        PreparedStatement preparedSql = connection.prepareStatement(
                "INSERT INTO country (id, country_name) VALUES (?, ?)"
        );
        preparedSql.setInt(1, 1);
        preparedSql.setString(2, "ru");
        preparedSql.executeUpdate();

        try {
            given()
                    .contentType("application/json")
                    .body("{\n" +
                            " \"id\": 1, \n" +
                            "}")
                    .when()
                    .delete("/api/countries/1")
                    .then()
                    .statusCode(204);
        } finally {
            preparedSql = connection.prepareStatement(
                    "DELETE FROM country WHERE country_name = ?"
            );
            preparedSql.setString(1, "ru");
            preparedSql.executeUpdate();
        }
    }
}