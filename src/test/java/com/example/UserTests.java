package com.example;

import org.junit.jupiter.api.Test;
import com.google.gson.Gson;
import org.junit.jupiter.api.Assertions;
import com.example.models.User;
import com.example.utils.HttpRequestHelper;

public class UserTests {
    private static final String USERS_ENDPOINT = "users";
    private static final Gson gson = new Gson();

    /**
     * Prueba para crear 10 usuarios aleatorios y verificar que se crean correctamente.
     */
    @Test
    public void crearUsuariosAleatorios() throws Exception {
        for (int i = 0; i < 10; i++) {
            // Generar un usuario aleatorio
            User user = User.generateRandomUser();
            String json = gson.toJson(user);

            // Realizar la solicitud POST para crear el usuario
            String response = HttpRequestHelper.sendPost(USERS_ENDPOINT, json);

            // Verificar que el correo electrónico del usuario esté presente en la respuesta
            Assertions.assertTrue(response.contains(user.getEmail()), 
                "No se creó el usuario con el email: " + user.getEmail());
        }
    }

    /**
     * Prueba para verificar que no existan correos electrónicos duplicados entre los usuarios.
     */
    // @Test
    // public void verificarNoCorreosDuplicados() throws Exception {
    //     // Obtener todos los usuarios
    //     String response = HttpRequestHelper.sendGet(USERS_ENDPOINT);

    //     // Verificar que no haya correos electrónicos duplicados
    //     String[] usuarios = response.substring(1, response.length() - 1).split("\\},\\{");
    //     for (int i = 0; i < usuarios.length; i++) {
    //         String email = usuarios[i].split("\"email\":\"")[1].split("\"")[0];
    //         for (int j = i + 1; j < usuarios.length; j++) {
    //             String emailToCompare = usuarios[j].split("\"email\":\"")[1].split("\"")[0];
    //             Assertions.assertNotEquals(email, emailToCompare, 
    //                 "Se detectó un correo electrónico duplicado: " + email);
    //         }
    //     }
    // }

    /**
     * Prueba para actualizar el número de cuenta de un usuario.
     */
    @Test
    public void actualizarNumeroCuentaUsuario() throws Exception {
        // Crear un usuario aleatorio
        User user = User.generateRandomUser();
        String json = gson.toJson(user);

        // Crear el usuario en la base de datos
        String response = HttpRequestHelper.sendPost(USERS_ENDPOINT, json);
        String userId = response.split("\"id\":\"")[1].split("\"")[0]; // Obtener el ID del usuario recién creado

        // Generar un nuevo número de cuenta aleatorio
        String nuevoNumeroCuenta = "1000" + (int) (Math.random() * 10000);
        user.setNumeroCuenta(nuevoNumeroCuenta);
        String updatedJson = gson.toJson(user);

        // Enviar una solicitud PUT para actualizar el número de cuenta
        String updateResponse = HttpRequestHelper.sendPut(USERS_ENDPOINT + "/" + userId, updatedJson);

        // Verificar que el número de cuenta se actualizó correctamente
        Assertions.assertTrue(updateResponse.contains(nuevoNumeroCuenta),
                "No se actualizó el número de cuenta correctamente.");
    }
}
