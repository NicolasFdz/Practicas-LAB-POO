public class ClienteFernandez {
    protected String nombre;
    protected String apellido;
    protected int edad;
    protected CuentaBancaria10_12 cuenta;

    public ClienteFernandez(String nombre, String apellido, int edad, CuentaBancaria10_12 cuenta) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.edad = edad;
        this.cuenta = cuenta;
    }

    public void mostrarCliente() {
        System.out.println("Cliente: " + nombre + " " + apellido + ", Edad: " + edad);
        System.out.println("Cuenta asociada: " + cuenta.toString());
    }
}
