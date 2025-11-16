public class Saldo12InsuficienteException extends ExceptionFernandezBase {
    public Saldo12InsuficienteException(String mensaje) {
        super("Saldo insuficiente: " + mensaje);
    }
}
