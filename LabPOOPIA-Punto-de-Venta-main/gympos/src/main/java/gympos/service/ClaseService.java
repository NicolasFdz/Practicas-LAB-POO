package gympos.service;

import gympos.model.ClaseGrupal;
import gympos.model.Empleado;
import gympos.model.ExcepcionDatos;
import gympos.model.SerializadorDatos;
import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

/**
 * Servicio encargado de gestionar las Clases Grupales, incluyendo CRUD,
 * persistencia y la lógica de inscripción.
 * Cumple con el requisito de GestionClasesMarquez.
 */
public class ClaseService
{

    private static final String FILE_PATH = "clases_grupales.dat";
    
    private List<ClaseGrupal> clases;
    private AtomicLong nextClaseId;

    /**
     * Constructor. Carga los datos y establece el ID secuencial.
     */
    public ClaseService() throws ExcepcionDatos
    {
        this.clases = new ArrayList<>();
        cargarClases();
        
        if (this.clases.isEmpty())
        {
            inicializarDatosPrueba();
            guardarClases();
        }
        
        // Inicializa el ID secuencial a partir del ID más alto encontrado (e.g., CL001)
        this.nextClaseId = new AtomicLong(clases.stream()
            .map(clase -> clase.getIdClase().replaceAll("[^0-9]", ""))
            .filter(s -> !s.isEmpty())
            .mapToLong(Long::parseLong)
            .max()
            .orElse(0L) + 1);
    }

    // --- Lógica de Persistencia ---

    private void cargarClases()
    {
        try
        {
            this.clases = SerializadorDatos.cargarDatos(FILE_PATH);
        }
        catch (ExcepcionDatos e)
        {
            System.err.println("Error al cargar Clases Grupales: " + e.getMessage());
            this.clases = new ArrayList<>(); 
        }
    }
    
    public void guardarClases()
    {
        try
        {
            SerializadorDatos.guardarDatos(this.clases, FILE_PATH);
        } 
       catch (ExcepcionDatos e)
        {
            System.err.println("Error al guardar Clases Grupales: " + e.getMessage());
        }
    }

    /**
     * Inicializa los datos de clases de prueba.
     */
    private void inicializarDatosPrueba() throws ExcepcionDatos
    {
        System.out.println("Creando datos iniciales de Clases Grupales...");
                    // ClaseGrupal(String idClase, String nombreClase, Empleado entrenador, DayOfWeek diaSemana, LocalTime horaInicio, LocalTime horaFin, int capacidadMaxima) throws ExcepcionDatos
        Empleado emp1 = new Empleado("EMP003", "trainer", "pass", Empleado.Rol.ENTRENADOR);
        clases.add(new ClaseGrupal(generarNuevoClaseId(), "Zumba Fitness", emp1 , DayOfWeek.MONDAY, "18:00", "19:00", 30));
        clases.add(new ClaseGrupal(generarNuevoClaseId(), "Yoga Vinyasa", emp1, DayOfWeek.TUESDAY, "9:30", "11:30", 20));
        clases.add(new ClaseGrupal(generarNuevoClaseId(), "Spinning Intenso", emp1, DayOfWeek.WEDNESDAY, "19:00", "20:00", 25));
        clases.add(new ClaseGrupal(generarNuevoClaseId(), "Entrenamiento Funcional", emp1, DayOfWeek.THURSDAY , "17:00", "18:30", 15));
        clases.add(new ClaseGrupal(generarNuevoClaseId(), "Pilates Mat", emp1, DayOfWeek.FRIDAY, "10:00", "12:30", 18));
        clases.add(new ClaseGrupal(generarNuevoClaseId(), "Boxeo Cardio", emp1, DayOfWeek.SATURDAY, "11:00", "13:30", 22));
        
        System.out.println("Clases iniciales creadas: " + clases.size());
    }
    
    // --- Lógica de Negocio (CRUD y Reserva) ---
    
    /**
     * Genera un nuevo ID para el objeto ClaseGrupal (e.g., CL007).
     * @return El nuevo ID de clase.
     */
    public String generarNuevoClaseId()
    {
        return String.format("CL%03d", nextClaseId.getAndIncrement());
    }

    /**
     * Añade una nueva clase.
     * @param clase El objeto ClaseGrupal a añadir.
     * @throws ExcepcionDatos Si ya existe una clase con el mismo ID.
     */
    public void agregarClase(ClaseGrupal clase) throws ExcepcionDatos
    {
        if (obtenerClasePorId(clase.getIdClase()).isPresent())
        {
            throw new ExcepcionDatos("Ya existe una clase con el ID: " + clase.getIdClase());
        }
        this.clases.add(clase);
        guardarClases();
    }
    
    /**
     * Actualiza los datos de una clase existente.
     * @param clase La ClaseGrupal con los datos actualizados.
     * @throws ExcepcionDatos Si la clase no existe.
     */
    public void actualizarClase(ClaseGrupal clase) throws ExcepcionDatos
    {
        for (int i = 0; i < clases.size(); i++)
        {
            if (clases.get(i).getIdClase().equals(clase.getIdClase()))
            {
                clases.set(i, clase);
                guardarClases();
                return;
            }
        }
        throw new ExcepcionDatos("No se encontró la clase con ID: " + clase.getIdClase() + " para actualizar.");
    }
    
    /**
     * Elimina una clase.
     * @param idClase El ID de la clase a eliminar.
     * @throws ExcepcionDatos Si la clase no existe.
     */
    public void eliminarClase(String idClase) throws ExcepcionDatos
    {
        boolean eliminado = clases.removeIf(c -> c.getIdClase().equals(idClase));
        if (!eliminado)
        {
            throw new ExcepcionDatos("No se encontró la clase con ID: " + idClase + " para eliminar.");
        }
        guardarClases();
    }
    
    /**
     * Inscribe un cliente a una clase grupal.
     * @param idClase ID de la clase.
     * @param idCliente ID del cliente.
     * @throws ExcepcionDatos Si la clase no existe, está llena o el cliente ya está inscrito.
     */
    public void inscribirCliente(String idClase, String idCliente) throws ExcepcionDatos
    {
        Optional<ClaseGrupal> claseOpt = obtenerClasePorId(idClase);
        
        if (claseOpt.isEmpty())
        {
            throw new ExcepcionDatos("Clase no encontrada con ID: " + idClase);
        }
        
        ClaseGrupal clase = claseOpt.get();
        
        if (clase.getCuposDisponibles() <= 0)
        {
            throw new ExcepcionDatos("La clase '" + clase.getNombreClase() + "' está llena. Capacidad máxima alcanzada.");
        }
        
        if (clase.getClientesInscritosIds().contains(idCliente))
        {
            throw new ExcepcionDatos("El cliente con ID " + idCliente + " ya está inscrito en esta clase.");
        }
        
        clase.inscribirCliente(idCliente);
        actualizarClase(clase); // Persiste el cambio
    }
    
    /**
     * Cancela la inscripción de un cliente a una clase grupal.
     * @param idClase ID de la clase.
     * @param idCliente ID del cliente.
     * @throws ExcepcionDatos Si la clase no existe o el cliente no estaba inscrito.
     */
    public void cancelarInscripcion(String idClase, String idCliente) throws ExcepcionDatos
    {
        Optional<ClaseGrupal> claseOpt = obtenerClasePorId(idClase);
        
        if (claseOpt.isEmpty())
        {
            throw new ExcepcionDatos("Clase no encontrada con ID: " + idClase);
        }
        
        ClaseGrupal clase = claseOpt.get();
        
        if (!clase.getClientesInscritosIds().contains(idCliente))
        {
            throw new ExcepcionDatos("El cliente con ID " + idCliente + " no está inscrito en esta clase.");
        }
        
        clase.cancelarInscripcion(idCliente);
        actualizarClase(clase); // Persiste el cambio
    }

    // --- Getters y Búsqueda ---
    
    public List<ClaseGrupal> getClases()
    {
        return new ArrayList<>(clases);
    }
    
    public Optional<ClaseGrupal> obtenerClasePorId(String idClase)
    {
        return clases.stream()
                .filter(c -> c.getIdClase().equals(idClase))
                .findFirst();
    }
    
    /**
     * Busca clases por nombre o instructor.
     */
    public List<ClaseGrupal> buscarClases(String query)
    {
        String lowerCaseQuery = query.toLowerCase();
        return clases.stream()
                .filter(c -> c.getNombreClase().toLowerCase().contains(lowerCaseQuery) || 
                             c.getEntrenador().getUsuario().toLowerCase().contains(lowerCaseQuery))
                .collect(Collectors.toList());
    }
    
    /**
     * Obtiene una lista de clases que aún tienen cupo disponible.
     */
    public List<ClaseGrupal> obtenerClasesDisponibles()
    {
        return clases.stream()
                .filter(c -> c.getClientesInscritosIds().size() < c.getCapacidadMaxima())
                .collect(Collectors.toList());
    }
}
