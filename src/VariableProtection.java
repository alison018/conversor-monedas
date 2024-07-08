import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class VariableProtection {
    public static String getApiKey() {
        return System.getenv("API_KEY");
    }

    public static void demonstrateProcessBuilder() {
        String apiKey = getApiKey();
        if (apiKey == null) {
            System.err.println("Error: La clave API no está configurada en las variables de entorno.");
            return;
        }

        try {
            ProcessBuilder processBuilder = new ProcessBuilder("echo", "Hello, world!");
            Process process = processBuilder.start();
            InputStreamReader inputStreamReader = new InputStreamReader(process.getInputStream());
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                System.out.println(line);
            }
            bufferedReader.close();
            process.waitFor();
        } catch (InterruptedException | IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        demonstrateProcessBuilder();
    }
}
