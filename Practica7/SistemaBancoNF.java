import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class SistemaBancoNF {
    public void validarMatricula(String matricula) throws Matricula10InvalidaException {
        if (!matricula.matches("\\d{4}")) {
            throw new Matricula10InvalidaException(matricula);
        }
    }

    public void retirarSaldo(double saldo, double retiro) throws Saldo12InsuficienteException {
        if (retiro > saldo) {
            throw new Saldo12InsuficienteException("Intentaste retirar $" + retiro + " con saldo $" + saldo);
        }
    }

    public void buscarUsuario(String nombre) throws Usuario9698NoEncontradoException {
        if (nombre == null || nombre.isEmpty()) {
            throw new Usuario9698NoEncontradoException("Nombre vacío");
        }
    }

    public void logError(String mensaje) {
        try (PrintWriter writer = new PrintWriter(new FileWriter("log_errores.txt", true))) {
            writer.println("Error para Nicolás Fernández: " + mensaje);
        } catch (IOException e) {
            System.out.println("No se pudo escribir en el log.");
        }
    }
}
