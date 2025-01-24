package com.example.models;

/**
 * Clase que representa una transacción bancaria.
 * Incluye validaciones para el tipo de transacción y el monto.
 */
public class Transaction {
    private String id; // Identificador único de la transacción
    private String usuarioId; // ID del usuario que realiza la transacción
    private String tipo; // Tipo de transacción: "depósito" o "retiro"
    private double monto; // Monto de la transacción
    private String fecha; // Fecha en la que se realiza la transacción

    // Getters y Setters con validaciones

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(String usuarioId) {
        this.usuarioId = usuarioId;
    }

    /**
     * Obtiene el tipo de transacción.
     * @return Tipo de transacción.
     */
    public String getTipo() {
        return tipo;
    }

    /**
     * Establece el tipo de transacción con validación.
     * Solo se permiten los valores "depósito" y "retiro".
     * @param tipo Tipo de transacción.
     * @throws IllegalArgumentException Si el tipo no es válido.
     */
    public void setTipo(String tipo) {
        if (!tipo.equalsIgnoreCase("depósito") && !tipo.equalsIgnoreCase("retiro")) {
            throw new IllegalArgumentException("El tipo de transacción debe ser 'depósito' o 'retiro'");
        }
        this.tipo = tipo;
    }

    /**
     * Obtiene el monto de la transacción.
     * @return Monto de la transacción.
     */
    public double getMonto() {
        return monto;
    }

    /**
     * Establece el monto de la transacción con validación.
     * El monto no puede ser negativo.
     * @param monto Monto de la transacción.
     * @throws IllegalArgumentException Si el monto es negativo.
     */
    public void setMonto(double monto) {
        if (monto < 0) {
            throw new IllegalArgumentException("El monto no puede ser negativo");
        }
        this.monto = monto;
    }

    /**
     * Obtiene la fecha de la transacción.
     * @return Fecha de la transacción.
     */
    public String getFecha() {
        return fecha;
    }

    /**
     * Establece la fecha de la transacción.
     * @param fecha Fecha en formato "YYYY-MM-DD".
     */
    public void setFecha(String fecha) {
        // Aquí podrías agregar validación para el formato de la fecha si es necesario.
        this.fecha = fecha;
    }
}
