import java.util.Comparator;

public class ComparadorPorAutor implements Comparator<Libro1012> {
    public int compare(Libro1012 l1, Libro1012 l2) {
        return l1.getAutor().compareTo(l2.getAutor());
    }
}
