public class RectanguloFernandez extends Figura10 {
    private double base, altura;

    public RectanguloFernandez(String color, double base, double altura) {
        super(color);
        this.base = base;
        this.altura = altura;
    }

    public double calcularArea() {
        return base * altura;
    }

    public double calcularPerimetro() {
        return 2 * (base + altura);
    }

    public String descripcion() {
        return super.descripcion() + ", tipo: Rectángulo";
    }

    // Sobrecarga
    public void redimensionar() {
        base *= 2;
        altura *= 2;
    }

    public void redimensionar(double factor) {
        base *= factor;
        altura *= factor;
    }

    public void redimensionar(String mensaje, double factor) {
        System.out.println(mensaje);
        base *= factor;
        altura *= factor;
    }
}
