import com.google.gson.JsonObject;
import java.util.Scanner;

public class Principal {

    public static void main(String[] args) {
        Scanner lector = new Scanner(System.in);
        LogConversion historia = new LogConversion();

        while (true) {
            try {
                System.out.println("Ingrese la divisa que quiere convertir:");
                String baseCurrency = lector.next().toUpperCase();
                JsonObject tasasDeCambio = ConversorDeDivisas.obtencionTasaCambio(baseCurrency);

                while (true) {
                    try {
                        System.out.print("Seleccione una de las siguientes opciones de divisa (Escoja del 1-12): ");
                        ConversorDeDivisas.imprimirMenuDivisas();
                        String opcion = lector.next();

                        if (opcion.equals("12")) {
                            System.out.println("Gracias por utilizar mi API");
                            System.out.println("Historia de la conversión:");
                            historia.imprimirHistoria();
                            return;  // Salir del programa
                        }

                        String targetCurrency = null;
                        if (opcion.matches("\\d+")) {
                            int seleccion = Integer.parseInt(opcion);
                            if (seleccion == 11) {
                                System.out.print("Ingrese el código de la divisa o el nombre del país: ");
                                String entrada = lector.next().toUpperCase();
                                targetCurrency = ConversorDeDivisas.obtenerCodigoDesdePais(entrada);
                                if (targetCurrency == null) {
                                    targetCurrency = entrada;  // Asume que el usuario ha ingresado un código de divisa válido
                                }
                            } else {
                                targetCurrency = ConversorDeDivisas.obtenerCodigoDivisa(seleccion);
                            }
                        } else {
                            targetCurrency = opcion.toUpperCase();
                        }

                        if (targetCurrency == null) {
                            System.out.println("Opción no válida.");
                            continue;
                        }

                        System.out.print("Ingrese la cantidad en " + baseCurrency + ": ");
                        double suma = lector.nextDouble();

                        double tasa = ConversorDeDivisas.obtenerTarifa(tasasDeCambio, targetCurrency);
                        double cantidadConvertida = ConversorDeDivisas.conversionDivisa(suma, tasa);

                        String conversion = suma + " " + baseCurrency + " = " + cantidadConvertida + " " + targetCurrency;
                        System.out.println(conversion);
                        historia.adicionDeConversion(conversion);

                        System.out.println("¿Quieres convertir otras divisas (si/no)?");
                        String eleccion = lector.next();
                        if (!eleccion.equalsIgnoreCase("si")) {
                            System.out.println("#####################################");
                            System.out.println("Historia de la conversión:");
                            historia.imprimirHistoria();
                            System.out.println("Muchas gracias. Te espero pronto!");
                            return;  // Salir del programa
                        } else {
                            break;  // Salir del bucle interno para permitir al usuario ingresar una nueva divisa base
                        }
                    } catch (RuntimeException e) {
                        System.out.println("Ha ocurrido un error: " + e.getMessage());
                    }
                }
            } catch (RuntimeException e) {
                System.out.println("Ha ocurrido un error: " + e.getMessage());
            }
        }
    }
}
