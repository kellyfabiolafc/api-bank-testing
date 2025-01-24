# API Bank Testing

Este proyecto tiene como objetivo automatizar las pruebas para una API bancaria simulada. Se realizan pruebas de creación de usuarios, gestión de transacciones, y verificación de diversas reglas de negocio como la validez de los correos electrónicos y los montos de las transacciones.

## Tecnologías Utilizadas

- **Java 20**: Lenguaje de programación principal.
- **Maven**: Herramienta de construcción y gestión de dependencias.
- **JUnit 5**: Framework de pruebas unitarias.
- **Rest Assured**: Librería para pruebas de API RESTful.
- **Gson**: Librería para convertir objetos Java a JSON y viceversa.

## Requisitos

Antes de ejecutar las pruebas, asegúrate de tener instalado lo siguiente:

- **Java 20**: Puedes descargarlo desde [aquí](https://www.oracle.com/java/technologies/javase/jdk20-archive-downloads.html).
- **Maven**: Puedes descargarlo desde [aquí](https://maven.apache.org/download.cgi).
- **IDE recomendada**: Visual Studio Code (VSCode) con soporte de Maven y JDK 20.

## Instrucciones de Instalación

1. Clona el repositorio en tu máquina local:

    ```bash
    git clone https://github.com/kellyfabiolafc/api-bank-testing.git
    ```

2. Navega al directorio del proyecto:

    ```bash
    cd api-bank-testing
    ```

3. Si aún no tienes las dependencias de Maven, ejecuta el siguiente comando:

    ```bash
    mvn install
    ```

4. Abre el proyecto en tu IDE (recomendado usar [VSCode](https://code.visualstudio.com/)).

## Ejecución de las Pruebas

1. **Para ejecutar las pruebas de API**, puedes usar Maven y el plugin Surefire. Ejecuta el siguiente comando para correr todas las pruebas:

    ```bash
    mvn test
    ```

2. **Para correr pruebas específicas** con TestNG, asegúrate de tener configurado un archivo `testng.xml` si quieres personalizar los casos que deseas ejecutar.

## Estructura del Proyecto

- **src/main/java/com/example**: Contiene las clases principales del proyecto, incluidas las de negocio.
- **src/test/java/com/example**: Contiene las clases de prueba.
- **pom.xml**: Archivo de configuración de Maven, donde se definen las dependencias y plugins.
- **target/**: Directorio donde Maven coloca los archivos compilados y el informe de pruebas.

## Pruebas Implementadas

### Usuario
1. **createRandomUsers**: Crea 10 usuarios aleatorios y verifica que su correo electrónico se haya creado correctamente.
2. **verifyNoDuplicateEmails**: Verifica que no existan correos electrónicos duplicados en la base de datos de usuarios.
3. **updateUserAccountNumber**: Crea un usuario aleatorio y actualiza su número de cuenta, verificando que la actualización se haya realizado correctamente.

### Transacción
1. **testCrearTransaccion**: Verifica la creación de una transacción con valores válidos.
2. **testMontoNegativoNoPermitido**: Verifica que no se permitan montos negativos en las transacciones.
3. **testTipoDeTransaccionInvalido**: Verifica que solo se permitan tipos de transacción válidos.
4. **testFormatoDeFecha**: Verifica que la fecha de la transacción esté en el formato correcto.

## Contribuciones

Si deseas contribuir al proyecto, por favor sigue estos pasos:

1. Haz un fork del repositorio.
2. Crea una nueva rama para tu contribución (`git checkout -b feature/nueva-prueba`).
3. Realiza tus cambios y asegúrate de que las pruebas pasen.
4. Haz un commit y sube tus cambios a tu fork.
5. Crea un pull request explicando tus cambios.

<!-- ## Licencia

Este proyecto está licenciado bajo la Licencia MIT. Puedes ver los detalles completos de la licencia en el archivo [LICENSE](LICENSE).

--- -->

Gracias por usar este repositorio. Si tienes alguna pregunta o sugerencia, no dudes en abrir un *issue*.

