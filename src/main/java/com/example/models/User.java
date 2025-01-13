package com.example.models;

import java.util.Random;

public class User {
    private String id;
    private String nombre;
    private String email;
    private String numeroCuenta;
    private double saldo;

    public User(String nombre, String email, String numeroCuenta, double saldo) {
        this.nombre = nombre;
        this.email = email;
        this.numeroCuenta = numeroCuenta;
        this.saldo = saldo;
    }

    // Getters y Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNumeroCuenta() {
        return numeroCuenta;
    }

    public void setNumeroCuenta(String numeroCuenta) {
        this.numeroCuenta = numeroCuenta;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    // Método para generar usuarios aleatorios
    public static User generateRandomUser() {
        Random rand = new Random();
        String[] nombres = { "Juan", "Ana", "Carlos", "Maria", "Luis" };
        String[] emails = { "juan@example.com", "ana@example.com", "carlos@example.com", "maria@example.com",
                "luis@example.com" };
        String numeroCuenta = String.format("1000%04d", rand.nextInt(10000)); // Genera un número de cuenta aleatorio
        double saldo = rand.nextInt(10000) + rand.nextDouble(); // Genera saldo aleatorio
        String nombre = nombres[rand.nextInt(nombres.length)];
        String email = emails[rand.nextInt(emails.length)];
        return new User(nombre, email, numeroCuenta, saldo);
    }
}
