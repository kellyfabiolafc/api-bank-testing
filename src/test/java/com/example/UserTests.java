package com.example;

import org.junit.jupiter.api.Test;
import com.google.gson.Gson;
import org.junit.jupiter.api.Assertions;
import com.example.models.User;
import com.example.utils.HttpRequestHelper;

public class UserTests {
    private static final String USERS_ENDPOINT = "users";

    @Test
    public void createRandomUsers() throws Exception {
        for (int i = 0; i < 10; i++) {
            User user = User.generateRandomUser();
            String json = new Gson().toJson(user);
            String response = HttpRequestHelper.sendPost(USERS_ENDPOINT, json);

            Assertions.assertTrue(response.contains(user.getEmail()), 
                "No se creó el usuario con el email: " + user.getEmail());
        }
    }

    @Test
    public void verifyNoDuplicateEmails() throws Exception {
        // Obtener todos los usuarios
        String response = HttpRequestHelper.sendGet(USERS_ENDPOINT);

        // Verificar que no haya correos electrónicos duplicados
        String[] users = response.substring(1, response.length() - 1).split("\\},\\{");
        for (int i = 0; i < users.length; i++) {
            String email = users[i].split("\"email\":\"")[1].split("\"")[0];
            for (int j = i + 1; j < users.length; j++) {
                String emailToCompare = users[j].split("\"email\":\"")[1].split("\"")[0];
                Assertions.assertNotEquals(email, emailToCompare, "Se detectó un correo electrónico duplicado: " + email);
            }
        }
    }

    @Test
    public void updateUserAccountNumber() throws Exception {
        // Crear un usuario aleatorio
        User user = User.generateRandomUser();
        String json = new Gson().toJson(user);

        // Crear el usuario
        String response = HttpRequestHelper.sendPost(USERS_ENDPOINT, json);
        String userId = response.split("\"id\":\"")[1].split("\"")[0]; // Obtener el ID del usuario recién creado

        // Actualizar el número de cuenta
        String newAccountNumber = "1000" + (int) (Math.random() * 10000);
        user.setNumeroCuenta(newAccountNumber);
        String updatedJson = new Gson().toJson(user);

        // Enviar PUT para actualizar la cuenta
        String updateResponse = HttpRequestHelper.sendPut(USERS_ENDPOINT + "/" + userId, updatedJson);
        Assertions.assertTrue(updateResponse.contains(newAccountNumber),
                "No se actualizó el número de cuenta correctamente.");
    }
}
