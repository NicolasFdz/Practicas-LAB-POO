public class CirculoFernandez extends Figura10 {
    private double radio;

    public CirculoFernandez(String color, double radio) {
        super(color);
        this.radio = radio;
    }

    public double calcularArea() {
        return Math.PI * radio * radio;
    }

    public double calcularPerimetro() {
        return 2 * Math.PI * radio;
    }

    public String descripcion() {
        return super.descripcion() + ", tipo: Círculo";
    }

    // Sobrecarga de métodos
    public void escalar() {
        radio *= 2;
    }

    public void escalar(double factor) {
        radio *= factor;
    }

    public void escalar(String mensaje, double factor) {
        System.out.println(mensaje);
        radio *= factor;
    }
}
