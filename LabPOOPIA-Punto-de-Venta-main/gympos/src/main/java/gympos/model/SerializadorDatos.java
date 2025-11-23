package gympos.model;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Utilidad estática para serializar y deserializar colecciones de objetos
 * que implementan la interfaz Serializable.
 */
public class SerializadorDatos
{

    public static <T extends Serializable> void guardarDatos(List<T> data, String filePath) throws ExcepcionDatos
    {
        try (FileOutputStream fileOut = new FileOutputStream(filePath);
             ObjectOutputStream objectOut = new ObjectOutputStream(fileOut))
        {

            objectOut.writeObject(data);
            System.out.println("Datos guardados exitosamente en: " + filePath);
        }
        catch (IOException e)
        {
            // Lanza la excepción personalizada para ser manejada por el controlador
            throw new ExcepcionDatos("Error al guardar los datos en el archivo: " + filePath, e);
        }
    }

    public static <T extends Serializable> List<T> cargarDatos(String filePath) throws ExcepcionDatos
    {
        File file = new File(filePath);
        if (!file.exists() || file.length() == 0)
        {
            System.out.println("Archivo de datos no encontrado o vacío. Retornando lista vacía: " + filePath);
            return new ArrayList<>();
        }

        try (FileInputStream fileIn = new FileInputStream(filePath);
             ObjectInputStream objectIn = new ObjectInputStream(fileIn))
        {

            @SuppressWarnings("unchecked")
            List<T> data = (List<T>) objectIn.readObject();
            System.out.println("Datos cargados exitosamente desde: " + filePath);
            return data;

        }
        catch (FileNotFoundException e)
        {
            return new ArrayList<>();
        }
        catch (IOException | ClassNotFoundException e)
        {
            throw new ExcepcionDatos("Error al cargar o leer los datos del archivo: " + filePath, e);
        }
    }
}
