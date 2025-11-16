public abstract class EmpleadoNF {
    protected String nombre;
    protected int edad;
    protected double salario;

    public EmpleadoNF(String nombre, int edad, double salario) {
        this.nombre = nombre;
        this.edad = edad;
        this.salario = salario;
    }

    public void mostrarDatos() {
        System.out.println("Nombre: " + nombre);
        System.out.println("Edad: " + edad);
        System.out.println("Salario: $" + salario);
    }

    public abstract void trabajar();
}
