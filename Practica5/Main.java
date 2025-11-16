public class Main {
    public static void main(String[] args) {
        // 1. Instanciación de figuras
        Figura10 circulo = new CirculoFernandez("Rojo", 5);
        Figura10 rectangulo = new RectanguloFernandez("Azul", 4, 6);
        Figura10 triangulo = new TrianguloFernandez("Verde", 3, 5);

        // 2. Procesar un array polimórfico de figuras
        Figura10[] figuras = new Figura10[3];
        figuras[0] = circulo;
        figuras[1] = rectangulo;
        figuras[2] = triangulo;

        CalculadoraGeometrica9698 calc = new CalculadoraGeometrica9698();

        System.out.println("=== Procesando array polimórfico de figuras ===");
        for (Figura10 f : figuras) {
            calc.mostrarCalculos(f); // polimorfismo: cada figura se comporta según su clase
        }

        // 3. Demostración de casting
        System.out.println("=== Demostración de casting ===");
        CirculoFernandez c = (CirculoFernandez) figuras[0]; // casting explícito
        c.escalar("Escalando círculo con factor 1.5...", 1.5);
        calc.mostrarCalculos(c);

        RectanguloFernandez r = (RectanguloFernandez) figuras[1];
        r.redimensionar("Redimensionando rectángulo con factor 2...", 2);
        calc.mostrarCalculos(r);

        TrianguloFernandez t = (TrianguloFernandez) figuras[2];
        t.transformar("Transformando triángulo con incremento 3...", 3);
        calc.mostrarCalculos(t);

        System.out.println("=== Fin de la demostración ===");
    }
}

