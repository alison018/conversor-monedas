import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import static jdk.internal.net.http.common.Log.logError;

public class ConversorDeDivisas {
    private static final HttpClient client = HttpClient.newHttpClient();
    private static final String apiKey  = VariableProtection.getApiKey();
    private static final String log_file = "error_log.txt";
    private static final Map<Integer, String> menuDivisas;
    private static final Map<String, String> paisACodigo;

    static {
        menuDivisas = new HashMap<>();
        menuDivisas.put(1, "USD");
        menuDivisas.put(2, "EUR");
        menuDivisas.put(3, "JPY");
        menuDivisas.put(4, "GBP");
        menuDivisas.put(5, "AUD");
        menuDivisas.put(6, "CAD");
        menuDivisas.put(7, "CHF");
        menuDivisas.put(8, "CNH");
        menuDivisas.put(9, "HKD");
        menuDivisas.put(10, "NZD");

        paisACodigo = new HashMap<>();
        paisACodigo.put("UNITED STATES", "USD");
        paisACodigo.put("EUROPE", "EUR");
        paisACodigo.put("JAPAN", "JPY");
        paisACodigo.put("UNITED KINGDOM", "GBP");
        paisACodigo.put("AUSTRALIA", "AUD");
        paisACodigo.put("CANADA", "CAD");
        paisACodigo.put("SWITZERLAND", "CHF");
        paisACodigo.put("CHINA", "CNH");
        paisACodigo.put("HONG KONG", "HKD");
        paisACodigo.put("NEW ZEALAND", "NZD");
    }

    public static HttpRequest crearRequest(String baseCurrency){
        String url = "https://v6.exchangerate-api.com/v6/"+ apiKey + "/latest/" + baseCurrency;
        return HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET()
                .build();
    }

    public static HttpRequest crearRequestEnriched(String baseCurrency, String targetCurrency){
        String url = "https://v6.exchangerate-api.com/v6/"+ apiKey + "/enriched/" + baseCurrency + "/" +targetCurrency;
        return HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET()
                .build();
    }
    public static JsonObject obtencionTasaCambio(String baseCurrency){
        try{
            HttpRequest peticion = crearRequest(baseCurrency);
            HttpResponse<String> respuesta = client
                    .send(peticion, HttpResponse.BodyHandlers.ofString());
            JsonElement root = JsonParser.parseString(respuesta.body());
            return root.getAsJsonObject();
        }catch (IOException | InterruptedException e){
            logError(e);
            throw new RuntimeException("Er;ror al obtener las tasas de cambio", e);
        }
    }
    public static JsonObject obtencionTasaCambioEnriched(String baseCurrency, String targetCurrency){
        try{
            HttpRequest peticion = crearRequestEnriched(baseCurrency, targetCurrency);
            HttpResponse<String> respuesta = client
                    .send(peticion, HttpResponse.BodyHandlers.ofString());
            JsonElement root = JsonParser.parseString(respuesta.body());
            return root.getAsJsonObject();
        }catch (IOException | InterruptedException e){
            logError(e);
            throw new RuntimeException("Er;ror al obtener las tasas de cambio", e);
        }
    }
    public static double obtenerTarifa(JsonObject jsonObj, String codigoDePais){
        try{
            JsonObject tasasDeCambio = jsonObj.getAsJsonObject("conversion_rates");
            return tasasDeCambio.get(codigoDePais).getAsDouble();
        }catch (NullPointerException e){
            logError(e);
            throw new RuntimeException("Error al obtener la tasa para la moneda: " +
                    codigoDePais, e);
        }
    }
    public static void imprimirMenuDivisas() {

        System.out.println("\n| Opción | Divisa                | Código |");
        System.out.println("|--------|-----------------------|--------|");
        System.out.println("| 1      | US dollar             | USD    |");
        System.out.println("| 2      | Euro                  | EUR    |");
        System.out.println("| 3      | Japanese yen          | JPY    |");
        System.out.println("| 4      | Pound sterling        | GBP    |");
        System.out.println("| 5      | Australian dollar     | AUD    |");
        System.out.println("| 6      | Canadian dollar       | CAD    |");
        System.out.println("| 7      | Swiss franc           | CHF    |");
        System.out.println("| 8      | Chinese renminbi      | CNH    |");
        System.out.println("| 9      | Hong Kong dollar      | HKD    |");
        System.out.println("| 10     | New Zealand dollar    | NZD    |");
        System.out.println("| 11     | Otra Divisa/Pais      |        |");
        System.out.println("| 12     | Salir                 |        |");
    }

    public static String obtenerCodigoDivisa(int opcion) {
        return menuDivisas.get(opcion);
    }

    public static String obtenerCodigoDesdePais(String nombreDelPais) {
        return paisACodigo.get(nombreDelPais.toUpperCase());
    }

    public static double conversionDivisa(double suma, double tasa){
        return suma*tasa;
    }
    private static void logError(Exception e) {
        try (FileWriter fw = new FileWriter(log_file, true);
             PrintWriter pw = new PrintWriter(fw)) {
            pw.println(LocalDateTime.now() + " - " + e.getClass().getName() + ": " + e.getMessage());
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }


}
