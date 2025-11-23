package gympos.service;

import gympos.model.InventarioItem;
import gympos.model.ExcepcionDatos;
import gympos.model.SerializadorDatos;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

/**
 * Servicio encargado de la gestión del InventarioItem, incluyendo CRUD,
 * persistencia y lógica de stock.
 * Cumple con el requisito de Control de Inventario.
 */
public class InventarioService {

    private static final String FILE_PATH = "inventario.dat";
    
    private List<InventarioItem> inventario;
    private AtomicLong nextItemId;

    /**
     * Constructor. Carga los datos y establece el ID secuencial.
     */
    public InventarioService() {
        this.inventario = new ArrayList<>();
        cargarInventario();
        
        if (this.inventario.isEmpty()) {
            inicializarDatosPrueba();
            guardarInventario();
        }
        
        // Inicializa el ID secuencial a partir del ID más alto encontrado
        this.nextItemId = new AtomicLong(inventario.stream()
            .map(item -> item.getIdItem().replaceAll("[^0-9]", ""))
            .filter(s -> !s.isEmpty())
            .mapToLong(Long::parseLong)
            .max()
            .orElse(0L) + 1);
    }

    // --- Lógica de Persistencia ---

    private void cargarInventario() {
        try {
            this.inventario = SerializadorDatos.cargarDatos(FILE_PATH);
        } catch (ExcepcionDatos e) {
            System.err.println("Error al cargar Inventario: " + e.getMessage());
            this.inventario = new ArrayList<>(); 
        }
    }
    
    public void guardarInventario() {
        try {
            SerializadorDatos.guardarDatos(this.inventario, FILE_PATH);
        } catch (ExcepcionDatos e) {
            System.err.println("Error al guardar Inventario: " + e.getMessage());
        }
    }

    /**
     * Inicializa los datos de inventario de prueba.
     */
    private void inicializarDatosPrueba() {
        System.out.println("Creando datos iniciales de Inventario...");
        inventario.add(new InventarioItem("INV001", "Barra Olímpica", "Barra de levantamiento de pesas estándar.", 10, "Disponible"));
        inventario.add(new InventarioItem("INV002", "Mancuerna de 10kg", "Juego de mancuernas de peso fijo.", 50, "Disponible"));
        inventario.add(new InventarioItem("INV003", "Banda de Resistencia", "Bandas de diferentes resistencias para entrenamiento ligero.", 150, "Disponible"));
        inventario.add(new InventarioItem("INV004", "Máquina de Correr", "Cinta de correr eléctrica para cardio.", 3, "Mantenimiento"));
        inventario.add(new InventarioItem("INV005", "Pelota de Yoga", "Pelotas grandes para ejercicios de estabilidad.", 25, "Disponible"));
        System.out.println("Items de Inventario creados: " + inventario.size());
    }
    
    // --- Lógica de Negocio (CRUD) ---
    
    /**
     * Genera un nuevo ID para el objeto InventarioItem (e.g., INV006).
     * @return El nuevo ID de item.
     */
    public String generarNuevoItemId() {
        return String.format("INV%03d", nextItemId.getAndIncrement());
    }

    /**
     * Añade un nuevo item al inventario.
     * @param item El InventarioItem a añadir.
     * @throws ExcepcionDatos Si ya existe un item con el mismo ID.
     */
    public void agregarItem(InventarioItem item) throws ExcepcionDatos {
        if (inventario.stream().anyMatch(i -> i.getIdItem().equals(item.getIdItem()))) {
            throw new ExcepcionDatos("Ya existe un item de inventario con el ID: " + item.getIdItem());
        }
        this.inventario.add(item);
        guardarInventario();
    }
    
    /**
     * Actualiza un item existente.
     * @param item El InventarioItem con los datos actualizados.
     * @throws ExcepcionDatos Si el item no existe.
     */
    public void actualizarItem(InventarioItem item) throws ExcepcionDatos {
        for (int i = 0; i < inventario.size(); i++) {
            if (inventario.get(i).getIdItem().equals(item.getIdItem())) {
                inventario.set(i, item);
                guardarInventario();
                return;
            }
        }
        throw new ExcepcionDatos("No se encontró el item con ID: " + item.getIdItem() + " para actualizar.");
    }
    
    /**
     * Elimina un item del inventario.
     * @param idItem El ID del item a eliminar.
     * @throws ExcepcionDatos Si el item no existe.
     */
    public void eliminarItem(String idItem) throws ExcepcionDatos {
        boolean eliminado = inventario.removeIf(i -> i.getIdItem().equals(idItem));
        if (!eliminado) {
            throw new ExcepcionDatos("No se encontró el item con ID: " + idItem + " para eliminar.");
        }
        guardarInventario();
    }

    /**
     * Ajusta el stock de un item específico.
     * @param idItem ID del item.
     * @param cambio Cantidad a añadir (positivo) o quitar (negativo).
     * @throws ExcepcionDatos Si el item no existe o el cambio resulta en stock negativo.
     */
    public void ajustarStock(String idItem, int cambio) throws ExcepcionDatos {
        Optional<InventarioItem> itemOpt = inventario.stream()
                .filter(i -> i.getIdItem().equals(idItem))
                .findFirst();

        if (itemOpt.isEmpty()) {
            throw new ExcepcionDatos("Item de inventario no encontrado: " + idItem);
        }
        
        InventarioItem item = itemOpt.get();
        int nuevoStock = item.getCantidadStock() + cambio;
        
        if (nuevoStock < 0) {
            throw new ExcepcionDatos("El stock no puede ser negativo. Intento ajustar a: " + nuevoStock);
        }
        
        item.setCantidadStock(nuevoStock);
        actualizarItem(item); // Usa el método de actualización para guardar
    }
    
    // --- Getters y Búsqueda ---
    
    public List<InventarioItem> getInventario() {
        return new ArrayList<>(inventario);
    }
    
    public Optional<InventarioItem> obtenerItemPorId(String idItem) {
        return inventario.stream()
                .filter(i -> i.getIdItem().equals(idItem))
                .findFirst();
    }
    
    /**
     * Filtra el inventario por estado (e.g., "Mantenimiento").
     */
    public List<InventarioItem> obtenerPorEstado(String estado) {
        return inventario.stream()
                .filter(i -> i.getEstado().equalsIgnoreCase(estado))
                .collect(Collectors.toList());
    }
}