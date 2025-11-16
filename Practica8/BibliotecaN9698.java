import java.util.*;

public class BibliotecaN9698 {
    private ArrayList<Libro1012> librosDisponibles;
    private LinkedList<Libro1012> colaReservas;
    private HashMap<String, Usuario> usuariosRegistrados;
    private HashSet<String> categoriasUnicas;

    public BibliotecaN9698() {
        librosDisponibles = new ArrayList<>();
        colaReservas = new LinkedList<>();
        usuariosRegistrados = new HashMap<>();
        categoriasUnicas = new HashSet<>();
    }

    public void agregarLibro(Libro1012 libro) {
        librosDisponibles.add(libro);
        categoriasUnicas.add(libro.getCategoria());
    }

    public void eliminarLibro(String titulo) {
        librosDisponibles.removeIf(libro -> libro.getTitulo().equalsIgnoreCase(titulo));
    }

    public Libro1012 buscarLibro(String titulo) {
        return librosDisponibles.stream()
            .filter(libro -> libro.getTitulo().equalsIgnoreCase(titulo))
            .findFirst()
            .orElse(null);
    }

    public void mostrarLibrosConIterador() {
        Iterator<Libro1012> it = librosDisponibles.iterator();
        while (it.hasNext()) {
            System.out.println(it.next());
        }
    }

    public List<Libro1012> buscarPorCategoria(String categoria) {
        return librosDisponibles.stream()
            .filter(libro -> libro.getCategoria().equalsIgnoreCase(categoria))
            .toList();
    }

    public void registrarUsuario(String matricula, Usuario usuario) {
        usuariosRegistrados.put(matricula, usuario);
    }

    public Usuario obtenerUsuario(String matricula) {
        return usuariosRegistrados.get(matricula);
    }

    public void reservarLibro(Libro1012 libro) {
        colaReservas.add(libro);
    }

    public Libro1012 procesarReserva() {
        return colaReservas.poll();
    }

    public void mostrarCategoriasUnicas() {
        System.out.println("Categorías únicas:");
        for (String c : categoriasUnicas) {
            System.out.println("- " + c);
        }
    }

    public void mostrarTiempoBusqueda(String titulo) {
        long inicio = System.nanoTime();
        buscarLibro(titulo);
        long fin = System.nanoTime();
        System.out.println("Tiempo de búsqueda: " + (fin - inicio) + " ns");
    }
}
