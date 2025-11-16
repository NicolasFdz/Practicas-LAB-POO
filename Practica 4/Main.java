public class Main {
    public static void main(String[] args) {
        // Prueba de AutoFernandez
        AutoFernandez auto = new AutoFernandez("Toyota", "Corolla", 2020, 4);
        auto.encender(); // Esperado: "Auto encendido con llave."
        auto.mostrarInfo(); // Esperado: marca, modelo, año, puertas
        System.out.println();

        // Prueba de MotocicletaFernandez
        MotocicletaFernandez moto = new MotocicletaFernandez("Yamaha", "R3", 2022, true);
        moto.encender(); // Esperado: "Motocicleta encendida con botón."
        moto.mostrarInfo(); // Esperado: marca, modelo, año, cascoIncluido
        System.out.println();

        // Prueba de CamionFernandez
        CamionFernandez camion = new CamionFernandez("Volvo", "FH", 2019, 18.5);
        camion.encender(); // Esperado: "Camión encendido con botón de seguridad."
        camion.mostrarInfo(); // Esperado: marca, modelo, año, capacidadCarga
        System.out.println();

        // Prueba de Concesionaria9698
        Concesionaria9698 concesionaria = new Concesionaria9698();
        concesionaria.agregarVehiculo(auto);
        concesionaria.agregarVehiculo(moto);
        concesionaria.agregarVehiculo(camion);
        concesionaria.mostrarInventario(); // Esperado: muestra todos los vehículos
    }
}
