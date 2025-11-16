import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class Main {

    // Punto 6: método para registrar eventos en log__Fernandez.csv
    public static void logEvento(String mensaje) {
        try (PrintWriter pw = new PrintWriter(new FileWriter("log__Fernandez.csv", true))) {
            pw.println(System.currentTimeMillis() + "," + mensaje);
        } catch (IOException e) {
            System.out.println("Error al escribir log: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        System.out.println("=== Simulador de Banco Concurrente ===");

        // Punto 6: medir tiempo de ejecución
        long inicio = System.nanoTime();
        logEvento("Inicio simulador banco");

        // Crear cuenta y lanzar hilos
        CuentaBancaria cuenta = new CuentaBancaria(1000);
        Thread cajero = new CajeroThread10(cuenta);
        Thread cliente = new Thread(new ClienteRunnable12(cuenta));

        cajero.start();
        cliente.start();

        try {
            cajero.join();
            cliente.join();
        } catch (InterruptedException e) {
            System.out.println("Error al esperar hilos: " + e.getMessage());
        }

        logEvento("Fin simulador banco");
        long fin = System.nanoTime();
        System.out.println(" Tiempo de ejecución: " + (fin - inicio) + " ns");

        // Mostrar saldo final
        System.out.println("Saldo final: $" + cuenta.getSaldo());

        // Punto 7: documentación de sincronización
        System.out.println("\n=== Estrategias de sincronización utilizadas ===");
        System.out.println("- synchronized en métodos de CuentaBancaria para evitar condiciones de carrera.");
        System.out.println("- join() para esperar que los hilos terminen antes de continuar.");
        System.out.println("- Cada hilo accede a métodos sincronizados para garantizar exclusión mutua.");
        System.out.println("- Se evita acceso simultáneo al saldo compartido.");
        System.out.println("\nConsulta el archivo log__Fernandez.csv para ver los eventos registrados.");
    }
}
