import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class LogConversion {

    private List<String> historia = new ArrayList<>();
    private static final String log_file = "conversion_log.txt";
    public void adicionDeConversion(String conversion){
        String record = (LocalDateTime.now() + ": " + conversion);
        historia.add(record);
        escribirEnArchivo(record);
    }
    public void imprimirHistoria(){
        for(String record : historia){
            System.out.println(record);
        }
    }
    private void escribirEnArchivo(String record){
        try(FileWriter fw = new FileWriter(log_file, true);
            PrintWriter pw = new PrintWriter(fw)){
            pw.println(record);
        }catch (IOException e){
            System.err.println("Error al escribir en el archivo de historial: " + e.getMessage());
        }
    }
}
