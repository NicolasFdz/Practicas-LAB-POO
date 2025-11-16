public class Main {
    public static void main(String[] args) {
        // Crear cuenta con validaciones
        CuentaBancaria10_12 cuenta = new CuentaBancaria10_12();
        cuenta.setTitular("Nicolás");
        cuenta.setNumeroCuenta("1234569698"); // válida
        cuenta.setSaldo(10000);               // válida
        cuenta.setActiva(true);

        // Mostrar cuenta
        System.out.println(cuenta.toString());

        // Crear cliente con composición
        ClienteFernandez cliente = new ClienteFernandez("Nicolás", "Fernández", 18, cuenta);
        cliente.mostrarCliente();

        // Prueba de validación fallida
        cuenta.setSaldo(5000); // no se actualiza porque es menor a 9698
        System.out.println("Saldo después de intento inválido: " + cuenta.getSaldo());
    }
}
