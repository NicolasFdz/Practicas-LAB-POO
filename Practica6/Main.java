public class Main {
    public static void main(String[] args) {
        EmpresaTIN9698 empresa = new EmpresaTIN9698();

        GerenteFernandez gerente = new GerenteFernandez("Luis", 45, 25000);
        empresa.agregarEmpleado(gerente);

        empresa.mostrarEmpleados();

        // Interfaces en acción
        gerente.recibirBono(3000);
        gerente.evaluarDesempeño();
        gerente.promover();
    }
}
