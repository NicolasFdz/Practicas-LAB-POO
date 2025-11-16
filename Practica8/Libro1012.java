public class Libro1012 implements Comparable<Libro1012> {
    private String titulo;
    private String autor;
    private String categoria;
    private int año;

    public Libro1012(String titulo, String autor, String categoria, int año) {
        this.titulo = titulo;
        this.autor = autor;
        this.categoria = categoria;
        this.año = año;
    }

    public String getTitulo() { return titulo; }
    public String getAutor() { return autor; }
    public String getCategoria() { return categoria; }
    public int getAño() { return año; }

    @Override
    public int compareTo(Libro1012 otro) {
        return this.titulo.compareTo(otro.titulo);
    }

    @Override
    public String toString() {
        return titulo + " - " + autor + " (" + año + ") [" + categoria + "]";
    }
}
