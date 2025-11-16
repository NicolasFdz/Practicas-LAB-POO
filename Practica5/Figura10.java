public abstract class Figura10 implements CalculableN {
    protected String color;

    public Figura10(String color) {
        this.color = color;
    }

    // Métodos abstractos que deben implementarse en las clases hijas
    public abstract double calcularArea();
    public abstract double calcularPerimetro();

    // Método común para todas las figuras
    @Override
    public String descripcion() {
        return "Figura de color " + color;
    }
}
