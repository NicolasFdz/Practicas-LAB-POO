package gympos.service;

import gympos.model.Cliente;
import gympos.model.TipoMembresia;
import gympos.model.Membresia;
import gympos.model.Pago;
import gympos.model.Pago.MetodoPago;
import gympos.model.Empleado;
import gympos.model.ExcepcionDatos;
import gympos.model.SerializadorDatos;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

/**
 * Servicio encargado de gestionar los Tipos de Membresía, las Membresías específicas
 * de los clientes y los Pagos.
 * Cumple con los requisitos de SistemaMembresias9Mayo y ProcesadorPagos3887.
 */
public class MembresiaService
{

    private static final String TIPOS_FILE_PATH = "tipos_membresia.dat";
    private static final String PAGOS_FILE_PATH = "pagos.dat";
    // Necesitas la ruta donde se guardan los clientes para revisar sus membresías.
    private static final String CLIENTES_FILE_PATH = "clientes.dat";
    
    private List<TipoMembresia> tiposMembresia;
    private List<Pago> historialPagos;
    
    private AtomicLong nextPagoId;
    private AtomicLong nextMembresiaId;

    /**
     * Constructor. Carga los datos y establece los IDs secuenciales.
     */
    public MembresiaService()
    {
        this.tiposMembresia = new ArrayList<>();
        this.historialPagos = new ArrayList<>();
        
        cargarTiposMembresia();
        if (this.tiposMembresia.isEmpty())
        {
            inicializarTiposPrueba();
            guardarTiposMembresia();
        }
        
        cargarPagos();
        
        // Inicializa los IDs a partir de los datos cargados
        this.nextPagoId = new AtomicLong(historialPagos.stream()
            .map(p -> p.getIdPago().replaceAll("[^0-9]", ""))
            .filter(s -> !s.isEmpty())
            .mapToLong(Long::parseLong)
            .max()
            .orElse(0L) + 1);
            
        long maxId = calcularMaximoIdMembresiaExistente();
        this.nextMembresiaId = new AtomicLong(maxId + 1);
    }
    
    /**
     * Calcula el ID numérico más alto utilizado entre todas las membresías 
     * de todos los clientes persistidos (ej. de M00005 extrae el 5).
     * @return El número más alto de ID encontrado, o 0 si no hay membresías.
     */
    private long calcularMaximoIdMembresiaExistente()
    {
        long maxId = 0;
        try {
            // 1. Cargar todos los clientes del archivo de persistencia
            List<Cliente> clientes = SerializadorDatos.cargarDatos(CLIENTES_FILE_PATH);
            
            // 2. Iterar sobre todos los clientes y todas sus membresías
            for (Cliente cliente : clientes) {
                for (Membresia membresia : cliente.getMembresias()) {
                    String idStr = membresia.getIdMembresia(); // e.g., "M00005"
                    
                    // 3. Extraer el número (quitar la 'M' y convertir a long)
                    if (idStr.toUpperCase().startsWith("M") && idStr.length() > 1) {
                        try {
                            // Intentar parsear el resto de la cadena a un long
                            long currentId = Long.parseLong(idStr.substring(1));
                            if (currentId > maxId) {
                                maxId = currentId;
                            }
                        } catch (NumberFormatException e) {
                            // Manejar si hay un ID mal formado, pero seguir adelante
                            System.err.println("Advertencia: ID de Membresía mal formado: " + idStr);
                        }
                    }
                }
            }
        } catch (ExcepcionDatos e) {
            // Esto es normal si es la primera vez que se ejecuta o el archivo no existe
            System.err.println("No se encontraron clientes para calcular el Max ID de Membresía. Iniciando contador en 1. (Detalle: " + e.getMessage() + ")");
        }
        
        return maxId;
    }

    // --- Lógica de Persistencia de Tipos y Pagos ---

    private void cargarTiposMembresia()
    {
        try
        {
            this.tiposMembresia = SerializadorDatos.cargarDatos(TIPOS_FILE_PATH);
        }
        catch (ExcepcionDatos e)
        {
            System.err.println("Error al cargar Tipos de Membresía: " + e.getMessage());
            this.tiposMembresia = new ArrayList<>(); 
        }
    }
    
    public void guardarTiposMembresia()
    {
        try
        {
            SerializadorDatos.guardarDatos(this.tiposMembresia, TIPOS_FILE_PATH);
        }
        catch (ExcepcionDatos e)
        {
            System.err.println("Error al guardar Tipos de Membresía: " + e.getMessage());
        }
    }
    
    private void cargarPagos()
    {
        try
        {
            this.historialPagos = SerializadorDatos.cargarDatos(PAGOS_FILE_PATH);
        }
        catch (ExcepcionDatos e)
        {
            System.err.println("Error al cargar Historial de Pagos: " + e.getMessage());
            this.historialPagos = new ArrayList<>(); 
        }
    }
    
    public void guardarPagos()
    {
        try
        {
            SerializadorDatos.guardarDatos(this.historialPagos, PAGOS_FILE_PATH);
        }
        catch (ExcepcionDatos e)
        {
            System.err.println("Error al guardar Historial de Pagos: " + e.getMessage());
        }
    }

    /**
     * Inicializa los tipos de membresía de prueba.
     */
    private void inicializarTiposPrueba()
    {
        System.out.println("Creando Tipos de Membresía iniciales...");
        //public TipoMembresia(String nombre, int duracionDias, BigDecimal precioBase, boolean accesoIlimitadoClases)
        // Mensual (30 días)
        tiposMembresia.add(new TipoMembresia("Mensual Básico", 30, new BigDecimal("49.3887"), false));
        // Trimestral (90 días)
        tiposMembresia.add(new TipoMembresia("Trimestral Premium", 90, new BigDecimal("129.9698"), true));
        // Anual (365 días)
        tiposMembresia.add(new TipoMembresia("Anual VIP", 365, new BigDecimal("399.3887"), true));
        
        System.out.println("Tipos de Membresía creados: " + tiposMembresia.size());
    }
    
    // --- Lógica de Negocio de Membresías y Pagos ---
    /**
    * Genera un nuevo ID único y secuencial para una Membresía.
    * Ejemplo: M00001, M00002, etc.
    * @return El nuevo ID de membresía.
    */
   public String generarNuevoIdMembresia() {
       // 1. Obtiene el valor actual y LUEGO lo incrementa
       long nextId = nextMembresiaId.getAndIncrement(); 

       // 2. Formatea el número para asegurar el relleno de ceros (ej: 1 -> 00001)
       // Usamos 'M' como prefijo y '%05d' para 5 dígitos con ceros iniciales.
       return String.format("M%05d", nextId); 
   }
    public void registrarPagoMembresia(Membresia membresia, BigDecimal monto, Pago.MetodoPago metodo, Empleado empleadoRegistro) throws ExcepcionDatos
   {
       // 1. Crear el objeto Pago
       String idPago = "P" + nextPagoId.getAndIncrement();
       Pago nuevoPago = new Pago(idPago, membresia, monto, metodo, empleadoRegistro);

       // 2. Registrar el pago en el historial
       this.historialPagos.add(nuevoPago);

       // 3. Persistir el historial (guardar en archivo)
       guardarPagos(); // Necesitas implementar este método de persistencia

       // NOTA: La lógica para asociar la 'membresia' al 'Cliente' se haría 
       // en el controlador/vista principal después de que el pago es exitoso.
   }
    /**
     * Genera un nuevo ID para el objeto Membresia (e.g., M00001).
     * @return El nuevo ID de membresía.
     */
    public String generarNuevoMembresiaId()
    {
        return String.format("M%05d", nextMembresiaId.getAndIncrement());
    }
    
    /**
     * Genera un nuevo ID para el objeto Pago (e.g., P00001).
     * @return El nuevo ID de pago.
     */
    public String generarNuevoPagoId() {
        return String.format("P%05d", nextPagoId.getAndIncrement());
    }

    /**
     * Registra una nueva membresía y el pago asociado a un cliente.
     * Esta es la operación principal de venta/renovación.
     * * @param tipo El tipo de membresía seleccionada.
     * @param fechaInicio La fecha de inicio de la membresía.
     * @param descuentoPorcentaje El porcentaje de descuento aplicado (e.g., 0.10 para 10%).
     * @param metodoPago El método de pago utilizado.
     * @param empleadoRegistro El empleado que realiza el registro.
     * @return La Membresia creada.
     * @throws ExcepcionDatos Si los datos son inválidos.
     */
    public Membresia registrarMembresia(TipoMembresia tipo, LocalDate fechaInicio, BigDecimal descuentoPorcentaje, MetodoPago metodoPago, Empleado empleadoRegistro) throws ExcepcionDatos {
        
        // 1. Crear Membresía
        String idMembresia = generarNuevoMembresiaId();
        Membresia nuevaMembresia = new Membresia(idMembresia, tipo, fechaInicio, descuentoPorcentaje);
        
        // 2. Crear Pago (ProcesadorPagos3887)
        String idPago = generarNuevoPagoId();
        Pago nuevoPago = new Pago(
            idPago, 
            nuevaMembresia, 
            nuevaMembresia.getPrecioPagado(), // El monto del pago es el precio final de la membresía
            metodoPago, 
            empleadoRegistro
        );
        
        // 3. Persistir el Pago
        this.historialPagos.add(nuevoPago);
        guardarPagos();
        
        // La Membresía no se guarda aquí, sino que se retorna y el ClienteService
        // se encargará de añadirla al objeto Cliente y guardarlo.
        
        return nuevaMembresia;
    }

    /**
     * Obtiene todos los tipos de membresía disponibles.
     * @return Lista de TipoMembresia.
     */
    public List<TipoMembresia> getTiposMembresia() {
        return new ArrayList<>(tiposMembresia);
    }
    
    /**
     * Obtiene el historial completo de pagos.
     * @return Lista de Pago.
     */
    public List<Pago> getHistorialPagos() {
        return new ArrayList<>(historialPagos);
    }
    
    /**
     * Obtiene los pagos realizados por una membresía específica.
     * NOTA: En este diseño, cada membresía solo tiene un pago asociado a su creación/renovación.
     * @param idMembresia ID de la membresía.
     * @return Lista de Pagos asociados.
     */
    public List<Pago> obtenerPagosPorMembresia(String idMembresia) {
        return historialPagos.stream()
                .filter(p -> p.getMembresiaAsociada().getIdMembresia().equals(idMembresia))
                .collect(Collectors.toList());
    }
    
    // --- Lógica de Gestión (CRUD TipoMembresia) ---
    
    /**
     * Agrega un nuevo tipo de membresía.
     * @param tipo El TipoMembresia a agregar.
     * @throws ExcepcionDatos Si ya existe uno con el mismo nombre.
     */
    public void agregarTipoMembresia(TipoMembresia tipo) throws ExcepcionDatos {
        if (tiposMembresia.stream().anyMatch(t -> t.getNombre().equalsIgnoreCase(tipo.getNombre()))) {
            throw new ExcepcionDatos("Ya existe un tipo de membresía con el nombre: " + tipo.getNombre());
        }
        tiposMembresia.add(tipo);
        guardarTiposMembresia();
    }
    
    /**
     * Busca un tipo de membresía por nombre.
     */
    public Optional<TipoMembresia> obtenerTipoPorNombre(String nombre) {
        return tiposMembresia.stream()
                .filter(t -> t.getNombre().equalsIgnoreCase(nombre))
                .findFirst();
    }
    
    
}