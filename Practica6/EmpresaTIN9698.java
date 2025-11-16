import java.util.ArrayList;

public class EmpresaTIN9698 {
    private ArrayList<EmpleadoNF> empleados;

    public EmpresaTIN9698() {
        empleados = new ArrayList<>();
    }

    public void agregarEmpleado(EmpleadoNF e) {
        empleados.add(e);
    }

    public void mostrarEmpleados() {
        for (EmpleadoNF e : empleados) {
            e.mostrarDatos();
            e.trabajar();
            System.out.println("---");
        }
    }
}
