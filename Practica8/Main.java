import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        BibliotecaN9698 biblioteca = new BibliotecaN9698();
        Scanner sc = new Scanner(System.in);
        int opcion;

        biblioteca.agregarLibro(new Libro1012("Java Básico", "Nicolás Fernández", "Programación", 2023));
        biblioteca.agregarLibro(new Libro1012("Estructuras de Datos", "Ana Torres", "Algoritmos", 2022));
        biblioteca.agregarLibro(new Libro1012("POO en Java", "Carlos Ruiz", "Programación", 2021));

        do {
            System.out.println("\n--- Menú Biblioteca ---");
            System.out.println("1. Agregar libro");
            System.out.println("2. Buscar por categoría");
            System.out.println("3. Mostrar todos los libros");
            System.out.println("4. Eliminar libro por título");
            System.out.println("5. Reservar libro");
            System.out.println("6. Procesar reserva");
            System.out.println("7. Mostrar categorías únicas");
            System.out.println("8. Medir tiempo de búsqueda");
            System.out.println("0. Salir");
            System.out.print("Opción: ");
            opcion = sc.nextInt(); sc.nextLine();

            switch (opcion) {
                case 1 -> {
                    System.out.print("Título: "); String t = sc.nextLine();
                    System.out.print("Autor: "); String a = sc.nextLine();
                    System.out.print("Categoría: "); String c = sc.nextLine();
                    System.out.print("Año: "); int y = sc.nextInt(); sc.nextLine();
                    biblioteca.agregarLibro(new Libro1012(t, a, c, y));
                }
                case 2 -> {
                    System.out.print("Categoría: "); String cat = sc.nextLine();
                    biblioteca.buscarPorCategoria(cat).forEach(System.out::println);
                }
                case 3 -> biblioteca.mostrarLibrosConIterador();
                case 4 -> {
                    System.out.print("Título a eliminar: "); String t = sc.nextLine();
                    biblioteca.eliminarLibro(t);
                }
                case 5 -> {
                    System.out.print("Título a reservar: "); String t = sc.nextLine();
                    Libro1012 libro = biblioteca.buscarLibro(t);
                    if (libro != null) {
                        biblioteca.reservarLibro(libro);
                        System.out.println("Libro reservado.");
                    } else {
                        System.out.println("Libro no encontrado.");
                    }
                }
                case 6 -> {
                    Libro1012 reservado = biblioteca.procesarReserva();
                    if (reservado != null) {
                        System.out.println("Reserva procesada: " + reservado);
                    } else {
                        System.out.println("No hay reservas pendientes.");
                    }
                }
                case 7 -> biblioteca.mostrarCategoriasUnicas();
                case 8 -> {
                    System.out.print("Título a buscar: "); String t = sc.nextLine();
                    biblioteca.mostrarTiempoBusqueda(t);
                }
            }
        } while (opcion != 0);

        sc.close();
    }
}
