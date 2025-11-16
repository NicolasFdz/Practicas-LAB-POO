import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        GestorArchivosFernandez9698 gestor = new GestorArchivosFernandez9698();
        Scanner sc = new Scanner(System.in);

        try {
            System.out.println("--- Menú I/O Java ---");
            System.out.println("1. Crear archivo de texto");
            System.out.println("2. Leer archivo de texto");
            System.out.println("3. Guardar objeto serializado");
            System.out.println("4. Leer objeto serializado");
            System.out.println("5. Procesar archivo CSV");
            System.out.println("6. Crear backup");
            System.out.println("7. Escribir archivo binario");
            System.out.println("8. Leer archivo binario");
            System.out.println("9. Generar archivo log CSV");
            System.out.print("Opción: ");
            int op = sc.nextInt(); sc.nextLine();

            switch (op) {
                case 1 -> gestor.escribirArchivoTexto("datos__9698.txt", "Nicolás,18\n");
                case 2 -> gestor.leerArchivoTexto("datos__9698.txt");
                case 3 -> gestor.guardarObjeto("persona.dat", new PersonaNSerializable("Nicolás", 18));
                case 4 -> {
                    PersonaNSerializable p = (PersonaNSerializable) gestor.cargarObjeto("persona.dat");
                    System.out.println("Objeto leído: " + p);
                }
                case 5 -> gestor.procesarCSV("log__Fernandez.csv");
                case 6 -> gestor.crearBackup("datos__9698.txt", ".");
                case 7 -> gestor.escribirBinario("archivo.bin", "Hola binario".getBytes());
                case 8 -> {
                    byte[] datos = gestor.leerBinario("archivo.bin");
                    System.out.println("Contenido binario: " + new String(datos));
                }
                case 9 -> gestor.generarLogCSV("log__Fernandez.csv");
                default -> System.out.println("Opción no válida.");
            }

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }

        sc.close();
    }
}
