public class GerenteFernandez extends EmpleadoNF implements Bonificable10, Evaluable12, Promovible9698 {
    public GerenteFernandez(String nombre, int edad, double salario) {
        super(nombre, edad, salario);
    }

    public void trabajar() {
        System.out.println("Gestionando equipos y proyectos.");
    }

    public void recibirBono(double cantidad) {
        salario += cantidad;
        System.out.println("Gerente recibió bono de $" + cantidad);
    }

    public void evaluarDesempeño() {
        System.out.println("Evaluando desempeño del equipo.");
    }

    public void promover() {
        System.out.println("Gerente promovido a Director.");
    }
}
