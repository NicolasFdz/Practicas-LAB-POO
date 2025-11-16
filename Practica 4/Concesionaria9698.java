import java.util.ArrayList;

public class Concesionaria9698 {
    // Lista para guardar cualquier tipo de vehículo
    private ArrayList<VehiculoBaseN> inventario;

    public Concesionaria9698() {
        inventario = new ArrayList<>();
    }

    // Método para agregar vehículos
    public void agregarVehiculo(VehiculoBaseN vehiculo) {
        inventario.add(vehiculo);
    }

    // Método para mostrar todos los vehículos
    public void mostrarInventario() {
        System.out.println("Inventario de la concesionaria:");
        for (VehiculoBaseN v : inventario) {
            v.mostrarInfo();   // polimorfismo: cada hijo muestra su info distinta
            System.out.println("---");
        }
    }
}
