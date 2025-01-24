package com.example.utils;


import io.restassured.response.Response;
import static io.restassured.RestAssured.*;


public class HttpRequestHelper {

    // Base URL con el prefijo /api/v1
    private static final String BASE_URL = "https://677ff1970476123f76a8d7e6.mockapi.io/api/";

    // Método GET con Rest Assured
    public static String sendGet(String endpoint) {
        Response response = given()
                .baseUri(BASE_URL)
                .when()
                .get(endpoint)
                .then()
                .statusCode(200) // Verifica que la respuesta sea OK
                .extract().response();
        return response.asString(); // Retorna la respuesta como String
    }

    // Método POST con Rest Assured
    public static String sendPost(String endpoint, String json) {
        Response response = given()
                .baseUri(BASE_URL)
                .contentType("application/json")
                .body(json)
                .when()
                .post(endpoint)
                .then()
                .statusCode(201) // Verifica que el código de estado sea 201 para éxito en la creación
                .extract().response();
        return response.asString(); // Retorna la respuesta como String
    }

    // Método DELETE con Rest Assured
    public static String sendDelete(String endpoint) {
        Response response = given()
                .baseUri(BASE_URL)
                .when()
                .delete(endpoint)
                .then()
                .statusCode(200) // Verifica que la respuesta sea OK
                .extract().response();
        return response.asString(); // Retorna la respuesta como String
    }

    // Método PUT con Rest Assured
    public static String sendPut(String endpoint, String json) {
        Response response = given()
                .baseUri(BASE_URL)
                .contentType("application/json")
                .body(json)
                .when()
                .put(endpoint)
                .then()
                .statusCode(200) // Verifica que la respuesta sea OK
                .extract().response();
        return response.asString(); // Retorna la respuesta como String
    }
}
