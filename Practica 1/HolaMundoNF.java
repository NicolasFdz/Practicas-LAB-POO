import java.time.LocalDate;

public class HolaMundoNF {
    public static void main(String[] args) {
        String nombreCompleto = "Nicolas de Jesus Fernandez Martinez";
        String matricula = "2099698";
        LocalDate fechaActual = LocalDate.now();

        System.out.println("Nombre: " + nombreCompleto);
        System.out.println("Matricula: " + matricula);
        System.out.println("Fecha actual del sistema: " + fechaActual);
    }
}
