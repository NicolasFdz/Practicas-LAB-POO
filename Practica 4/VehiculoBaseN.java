public class VehiculoBaseN {
    protected String marca;
    protected String modelo;
    protected int año;

    public VehiculoBaseN(String marca, String modelo, int año) {
        this.marca = marca;
        this.modelo = modelo;
        this.año = año;
    }

    public void encender() {
        System.out.println("Encendiendo vehículo...");
    }

    public void apagar() {
        System.out.println("Apagando vehículo...");
    }

    public void mostrarInfo() {
        System.out.println("Marca: " + marca + ", Modelo: " + modelo + ", Año: " + año);
    }
}
