public class MotocicletaFernandez extends VehiculoBaseN {
    private boolean cascoIncluido;

    public MotocicletaFernandez(String marca, String modelo, int año, boolean cascoIncluido) {
        super(marca, modelo, año);
        this.cascoIncluido = cascoIncluido;
    }

    @Override
    public void encender() {
        System.out.println("Motocicleta encendida con botón.");
    }

    @Override
    public void apagar() {
        System.out.println("Motocicleta apagada.");
    }

    @Override
    public void mostrarInfo() {
        super.mostrarInfo();
        System.out.println("Casco incluido: " + cascoIncluido);
    }
}
