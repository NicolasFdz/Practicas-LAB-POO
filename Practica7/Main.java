public class Main {
    public static void main(String[] args) {
        SistemaBancoNF sistema = new SistemaBancoNF();

        try {
            sistema.validarMatricula("abc");
        } catch (Matricula10InvalidaException e) {
            System.out.println(e.getMessage());
            sistema.logError(e.getMessage());
        }

        try {
            sistema.retirarSaldo(500, 1000);
        } catch (Saldo12InsuficienteException e) {
            System.out.println(e.getMessage());
            sistema.logError(e.getMessage());
        }

        try {
            sistema.buscarUsuario("");
        } catch (Usuario9698NoEncontradoException e) {
            System.out.println(e.getMessage());
            sistema.logError(e.getMessage());
        }
    }
}
