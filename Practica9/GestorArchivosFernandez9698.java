import java.io.*;
import java.nio.file.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class GestorArchivosFernandez9698 {

    public void leerArchivoTexto(String ruta) throws IOException {
        Files.lines(Paths.get(ruta)).forEach(System.out::println);
    }

    public void escribirArchivoTexto(String ruta, String contenido) throws IOException {
        Files.write(Paths.get(ruta), contenido.getBytes(), StandardOpenOption.CREATE, StandardOpenOption.APPEND);
    }

    public void escribirBinario(String ruta, byte[] datos) throws IOException {
        try (FileOutputStream fos = new FileOutputStream(ruta)) {
            fos.write(datos);
        }
    }

    public byte[] leerBinario(String ruta) throws IOException {
        return Files.readAllBytes(Paths.get(ruta));
    }

    public void crearDirectorio(String nombre) throws IOException {
        Files.createDirectories(Paths.get(nombre));
    }

    public void crearBackup(String rutaOriginal, String rutaDestino) throws IOException {
        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"));
        Path destino = Paths.get(rutaDestino + "/backup__1012_" + timestamp + ".dat");
        Files.copy(Paths.get(rutaOriginal), destino, StandardCopyOption.REPLACE_EXISTING);
    }

    public void guardarObjeto(String ruta, Object obj) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(ruta))) {
            oos.writeObject(obj);
        }
    }

    public Object cargarObjeto(String ruta) throws IOException, ClassNotFoundException {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(ruta))) {
            return ois.readObject();
        }
    }

    public void procesarCSV(String ruta) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(ruta))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split(",");
                System.out.println("Nombre: " + datos[0] + ", Edad: " + datos[1]);
            }
        }
    }

    public void generarLogCSV(String ruta) throws IOException {
        try (PrintWriter writer = new PrintWriter(new FileWriter(ruta))) {
            writer.println("Nicolás,18");
            writer.println("Ana,22");
            writer.println("Luis,20");
        }
    }
}
