package com.example;

import com.example.models.Transaction;
import com.example.utils.HttpRequestHelper;
import com.google.gson.Gson;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Clase de pruebas para validar el comportamiento de la API de Transacciones.
 */
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TransactionTests {

    private static final String TRANSACTION_ENDPOINT = "Transaction";
    private static final Gson gson = new Gson();

    /**
     * Método auxiliar para crear una transacción.
     *
     * @param usuarioId ID del usuario asociado a la transacción.
     * @param tipo      Tipo de transacción ("depósito" o "retiro").
     * @param monto     Monto de la transacción.
     * @param fecha     Fecha de la transacción en formato "YYYY-MM-DD".
     * @return Objeto Transaction creado.
     */
    private Transaction crearTransaccion(String usuarioId, String tipo, double monto, String fecha) {
        Transaction transaction = new Transaction();
        transaction.setUsuarioId(usuarioId);
        transaction.setTipo(tipo);
        transaction.setMonto(monto);
        transaction.setFecha(fecha);
        return transaction;
    }

    /**
     * Método auxiliar para eliminar una transacción por su ID.
     *
     * @param id ID de la transacción a eliminar.
     */
    private void eliminarTransaccion(String id) {
        HttpRequestHelper.sendDelete(TRANSACTION_ENDPOINT + "/" + id);
    }

    /**
     * Prueba 1: Verifica que el EndPoint esté vacío.
     * Si tiene datos, los elimina para dejarlo vacío.
     */
    @Test
    @Order(1)
    void testEndpointVacio() {
        // Obtener todas las transacciones
        String response = HttpRequestHelper.sendGet(TRANSACTION_ENDPOINT);
        Transaction[] transacciones = gson.fromJson(response, Transaction[].class);

        // Eliminar cada transacción existente
        for (Transaction transaccion : transacciones) {
            eliminarTransaccion(transaccion.getId());
        }

        // Verificar que el endpoint esté vacío
        response = HttpRequestHelper.sendGet(TRANSACTION_ENDPOINT);
        transacciones = gson.fromJson(response, Transaction[].class);
        assertEquals(0, transacciones.length, "El endpoint no está vacío después de la limpieza.");
    }

    /**
     * Prueba 2: Crea una transacción válida y verifica su creación.
     */
    @Test
    @Order(2)
    void testCrearTransaccionValida() {
        // Crear una nueva transacción
        Transaction transaction = crearTransaccion("123", "depósito", 100.50, "2025-01-24");
        String jsonTransaction = gson.toJson(transaction);

        // Enviar la transacción a la API
        String response = HttpRequestHelper.sendPost(TRANSACTION_ENDPOINT, jsonTransaction);
        Transaction transaccionCreada = gson.fromJson(response, Transaction.class);

        // Validar que la transacción se creó correctamente
        assertNotNull(transaccionCreada.getId(), "La transacción no tiene un ID asignado.");
        assertEquals("123", transaccionCreada.getUsuarioId());
        assertEquals("depósito", transaccionCreada.getTipo());
        assertEquals(100.50, transaccionCreada.getMonto());
        assertEquals("2025-01-24", transaccionCreada.getFecha());

        // Limpiar: eliminar la transacción creada
        eliminarTransaccion(transaccionCreada.getId());
    }

    /**
     * Prueba 3: Verifica que no se permitan montos negativos.
     */
    @Test
    @Order(3)
    void testMontoNegativoNoPermitido() {
        Transaction transaction = new Transaction();
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            transaction.setMonto(-50.00);
        });
        assertEquals("El monto no puede ser negativo", exception.getMessage());
    }

    /**
     * Prueba 4: Verifica que solo se permitan tipos de transacción válidos.
     */
    @Test
    @Order(4)
    void testTipoDeTransaccionInvalido() {
        Transaction transaction = new Transaction();
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            transaction.setTipo("transferencia");
        });
        assertEquals("El tipo de transacción debe ser 'depósito' o 'retiro'", exception.getMessage());
    }

    /**
     * Prueba 5: Verifica el formato de la fecha.
     */
    @Test
    @Order(5)
    void testFormatoDeFecha() {
        Transaction transaction = new Transaction();
        transaction.setFecha("2025-01-24");

        String fechaEsperada = "2025-01-24";
        assertEquals(fechaEsperada, transaction.getFecha());
    }

    /**
     * Prueba 6: Verifica el depósito correcto de dinero.
     */
    @Test
    @Order(6)
    void testDepositoCorrecto() {
        // Crear una nueva transacción de depósito
        Transaction deposito = crearTransaccion("123", "depósito", 200.00, "2025-01-24");
        String jsonDeposito = gson.toJson(deposito);

        // Enviar la transacción a la API
        String response = HttpRequestHelper.sendPost(TRANSACTION_ENDPOINT, jsonDeposito);
        Transaction transaccionCreada = gson.fromJson(response, Transaction.class);

        // Validar que la transacción se creó correctamente
        assertNotNull(transaccionCreada.getId(), "La transacción no tiene un ID asignado.");
        assertEquals("123", transaccionCreada.getUsuarioId());
        assertEquals("depósito", transaccionCreada.getTipo());
        assertEquals(200.00, transaccionCreada.getMonto());
        assertEquals("2025-01-24", transaccionCreada.getFecha());

        // Limpiar: eliminar la transacción creada
        eliminarTransaccion(transaccionCreada.getId());
    }

    /**
     * Prueba 7: Verifica la retirada correcta de dinero.
     */
    @Test
    @Order(7)
    void testRetiroCorrecto() {
        // Crear una nueva transacción de retiro
        Transaction retiro = crearTransaccion("123", "retiro", 100.00, "2025-01-24");
        String jsonRetiro = gson.toJson(retiro);

        // Enviar la transacción a la API
        String response = HttpRequestHelper.sendPost(TRANSACTION_ENDPOINT, jsonRetiro);
        Transaction transaccionCreada = gson.fromJson(response, Transaction.class);

        // Validar que la transacción se creó correctamente
        assertNotNull(transaccionCreada.getId(), "La transacción no tiene un ID asignado.");
        assertEquals("123", transaccionCreada.getUsuarioId());
        assertEquals("retiro", transaccionCreada.getTipo());
        assertEquals(100.00, transaccionCreada.getMonto());
        assertEquals("2025-01-24", transaccionCreada.getFecha());
    }
}
