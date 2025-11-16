public class AutoFernandez extends VehiculoBaseN {
    private int puertas;

    public AutoFernandez(String marca, String modelo, int año, int puertas) {
        super(marca, modelo, año); // llama al constructor de la clase padre
        this.puertas = puertas;
    }

    @Override
    public void encender() {
        System.out.println("Auto encendido con llave.");
    }

    @Override
    public void apagar() {
        System.out.println("Auto apagado.");
    }

    @Override
    public void mostrarInfo() {
        super.mostrarInfo(); // reutiliza el método de la clase padre
        System.out.println("Puertas: " + puertas);
    }
}
