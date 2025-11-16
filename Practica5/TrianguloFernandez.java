public class TrianguloFernandez extends Figura10 {
    private double base, altura;

    public TrianguloFernandez(String color, double base, double altura) {
        super(color);
        this.base = base;
        this.altura = altura;
    }

    public double calcularArea() {
        return (base * altura) / 2;
    }

    public double calcularPerimetro() {
        return base * 3; // simplificado como equilátero
    }

    public String descripcion() {
        return super.descripcion() + ", tipo: Triángulo";
    }

    // Sobrecarga
    public void transformar() {
        base += 1;
        altura += 1;
    }

    public void transformar(double incremento) {
        base += incremento;
        altura += incremento;
    }

    public void transformar(String mensaje, double incremento) {
        System.out.println(mensaje);
        base += incremento;
        altura += incremento;
    }
}
