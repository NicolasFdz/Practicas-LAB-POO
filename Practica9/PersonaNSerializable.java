import java.io.Serializable;

public class PersonaNSerializable implements Serializable {
    private String nombre;
    private int edad;

    public PersonaNSerializable(String nombre, int edad) {
        this.nombre = nombre;
        this.edad = edad;
    }

    public String getNombre() {
        return nombre;
    }

    public int getEdad() {
        return edad;
    }

    @Override
    public String toString() {
        return nombre + " (" + edad + " años)";
    }
}
