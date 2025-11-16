public class CamionFernandez extends VehiculoBaseN {
    private double capacidadCarga;

    public CamionFernandez(String marca, String modelo, int año, double capacidadCarga) {
        super(marca, modelo, año);
        this.capacidadCarga = capacidadCarga;
    }

    @Override
    public void encender() {
        System.out.println("Camión encendido con botón de seguridad.");
    }

    @Override
    public void apagar() {
        System.out.println("Camión apagado.");
    }

    @Override
    public void mostrarInfo() {
        super.mostrarInfo();
        System.out.println("Capacidad de carga: " + capacidadCarga + " toneladas");
    }
}
