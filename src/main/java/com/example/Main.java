package com.example;

import com.example.utils.HttpRequestHelper;

public class Main {

    public static void main(String[] args) {
        try {
            // Ejemplo de solicitud GET
            String response = HttpRequestHelper.sendGet("users");
            System.out.println("Respuesta del servidor: " + response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
