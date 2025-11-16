public class Matricula10InvalidaException extends ExceptionFernandezBase {
    public Matricula10InvalidaException(String mensaje) {
        super("Matrícula inválida: " + mensaje);
    }
}
