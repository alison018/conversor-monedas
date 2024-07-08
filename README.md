# Conversor de Divisas

## Descripción

El Conversor de Divisas es una aplicación Java que permite a los usuarios convertir cantidades entre diferentes divisas. Utiliza la API de ExchangeRate para obtener las tasas de cambio actualizadas y ofrece una interfaz de consola interactiva para realizar las conversiones. El historial de conversiones se guarda en un archivo para referencia futura.
La realización de este proyecto es requerida para la obtención del certificado de Backend en ALURA, Equipo Oracle Next Education.

## Características

- **Interfaz de Consola Interactiva**: Los usuarios pueden seleccionar la divisa base y la divisa objetivo para realizar conversiones.
- **Opciones de Divisas Principales**: Se proporciona un menú con las divisas más utilizadas, incluyendo US Dollar (USD), Euro (EUR), Japanese Yen (JPY), entre otras.
- **Entrada de Código de Divisa o Nombre del País**: Si la divisa deseada no está en el menú principal, el usuario puede ingresar el código de la divisa o el nombre del país.
- **Historial de Conversiones**: Cada conversión se guarda en un archivo `conversion_log.txt` para referencia futura.
- **Opciones de Salida**: Los usuarios pueden elegir salir de la aplicación en cualquier momento.

## Requisitos

- Java 11 o superior
- [Gson](https://github.com/google/gson) para el manejo de JSON

## Configuración

1. **Clonar el repositorio**:

    ```bash
    git clone <URL_DEL_REPOSITORIO>
    cd conversor-de-divisas
    ```

2. **Configurar la clave API**:

    Asegúrate de tener una clave API de [ExchangeRate-API](https://www.exchangerate-api.com/) y configúrala como una variable de entorno `API_KEY`.

    En sistemas Unix:

    ```bash
    export API_KEY=tu_clave_api
    ```

    En Windows:

    ```bash
    set API_KEY=tu_clave_api
    ```

3. **Compilar el proyecto**:

    ```bash
    javac -cp gson-2.11.0.jar:. Principal.java
    ```

4. **Ejecutar la aplicación**:

    ```bash
    java -cp gson-2.11.0.jar:. Principal
    ```

## Uso

### Menú Principal

Al iniciar la aplicación, se solicita al usuario que ingrese la divisa base que desea convertir. Luego, se muestra un menú con las siguientes opciones:

```markdown
| Opción | Divisa                | Código |
|--------|-----------------------|--------|
| 1      | US dollar             | USD    |
| 2      | Euro                  | EUR    |
| 3      | Japanese yen          | JPY    |
| 4      | Pound sterling        | GBP    |
| 5      | Australian dollar     | AUD    |
| 6      | Canadian dollar       | CAD    |
| 7      | Swiss franc           | CHF    |
| 8      | Chinese renminbi      | CNH    |
| 9      | Hong Kong dollar      | HKD    |
| 10     | New Zealand dollar    | NZD    |
| 11     | Otra Divisa/Pais      |        |
| 12     | Salir                 |        |
```
## Conversión de Divisas

### Seleccionar una opción:

El usuario selecciona una opción de divisa del 1 al 10, ingresa el código de divisa o nombre del país (opción 11), o elige salir (opción 12).

### Ingresar la cantidad:

Si se selecciona una divisa válida, el usuario debe ingresar la cantidad que desea convertir.

### Resultado de la conversión:

La aplicación muestra el resultado de la conversión y pregunta si el usuario desea realizar otra conversión.

### Salir:

Si el usuario selecciona la opción de salida, se muestra el historial de conversiones y la aplicación termina.

## Historial de Conversiones

Cada conversión realizada se guarda en un archivo `conversion_log.txt` con la fecha y hora de la conversión. El historial completo se muestra cuando el usuario decide salir de la aplicación.

## Contribuciones

Las contribuciones son bienvenidas. Si deseas contribuir a este proyecto, por favor sigue estos pasos:

1. Haz un fork del repositorio.
2. Crea una rama nueva (`git checkout -b feature/nueva-funcionalidad`).
3. Realiza tus cambios y haz commit (`git commit -am 'Agregar nueva funcionalidad'`).
4. Haz push a la rama (`git push origin feature/nueva-funcionalidad`).
5. Abre un Pull Request.

## Licencia

Este proyecto está bajo la Licencia MIT. Ver el archivo [LICENSE](LICENSE) para más detalles.
