package gympos.service;

import gympos.model.Cliente;
import gympos.model.Membresia;
import gympos.model.ExcepcionDatos;
import gympos.model.SerializadorDatos;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

/**
 * Servicio encargado de gestionar los Clientes, incluyendo CRUD,
 * persistencia y lógica relacionada con las Membresías y el Control de Acceso.
 * Cumple con el requisito de GestionClientesMarquez y ControlAccesoMarquez.
 */
public class ClienteService
{
    private static final String FILE_PATH = "clientes.dat";
    private static final DateTimeFormatter ACCESS_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    
    private List<Cliente> clientes;
    private AtomicLong nextClienteId;
    
    /**
     * Constructor. Carga los datos y establece el ID secuencial.
     */
    public ClienteService()
    {
        this.clientes = new ArrayList<>();
        cargarClientes();
        
        if (this.clientes.isEmpty())
        {
            inicializarDatosPrueba();
            guardarClientes();
        }
        
        // Inicializa el ID secuencial a partir del ID más alto encontrado (e.g., C0001)
        this.nextClienteId = new AtomicLong(clientes.stream()
            .map(cliente -> cliente.getIdCliente().replaceAll("[^0-9]", ""))
            .filter(s -> !s.isEmpty())
            .mapToLong(Long::parseLong)
            .max()
            .orElse(0L) + 1);
    }

    // --- Lógica de Persistencia ---

    private void cargarClientes()
    {
        try
        {
            this.clientes = SerializadorDatos.cargarDatos(FILE_PATH);
        }
        catch (ExcepcionDatos e)
        {
            System.err.println("Error al cargar Clientes: " + e.getMessage());
            this.clientes = new ArrayList<>(); 
        }
    }
    
    public void guardarClientes()
    {
        try
        {
            SerializadorDatos.guardarDatos(this.clientes, FILE_PATH);
        }
        catch (ExcepcionDatos e)
        {
            System.err.println("Error al guardar Clientes: " + e.getMessage());
        }
    }

    /**
     * Inicializa los datos de clientes de prueba (20+ registros).
     */
    private void inicializarDatosPrueba()
    {
        System.out.println("Creando datos iniciales de Clientes (20+ registros)...");
        clientes.add(new Cliente("C0001", "Ana", "García", "ana.g@mail.com"));
        clientes.add(new Cliente("C0002", "Luis", "Martínez", "luis.m@mail.com"));
        clientes.add(new Cliente("C0003", "Sofía", "Rodríguez", "sofia.r@mail.com"));
        clientes.add(new Cliente("C0004", "David", "Sánchez", "david.s@mail.com"));
        clientes.add(new Cliente("C0005", "Elena", "Pérez", "elena.p@mail.com"));
        clientes.add(new Cliente("C0006", "Javier", "Gómez", "javier.g@mail.com"));
        clientes.add(new Cliente("C0007", "Laura", "Díaz", "laura.d@mail.com"));
        clientes.add(new Cliente("C0008", "Mario", "Torres", "mario.t@mail.com"));
        clientes.add(new Cliente("C0009", "Isabel", "Vázquez", "isabel.v@mail.com"));
        clientes.add(new Cliente("C0010", "Pablo", "Ruiz", "pablo.r@mail.com"));
        clientes.add(new Cliente("C0011", "Carmen", "López", "carmen.l@mail.com"));
        clientes.add(new Cliente("C0012", "Jorge", "Jiménez", "jorge.j@mail.com"));
        clientes.add(new Cliente("C0013", "Paula", "Morales", "paula.m@mail.com"));
        clientes.add(new Cliente("C0014", "Héctor", "Castro", "hector.c@mail.com"));
        clientes.add(new Cliente("C0015", "Rosa", "Flores", "rosa.f@mail.com"));
        clientes.add(new Cliente("C0016", "Sergio", "Herrera", "sergio.h@mail.com"));
        clientes.add(new Cliente("C0017", "Nuria", "Ortega", "nuria.o@mail.com"));
        clientes.add(new Cliente("C0018", "Óscar", "Vidal", "oscar.v@mail.com"));
        clientes.add(new Cliente("C0019", "Marta", "Ramos", "marta.r@mail.com"));
        clientes.add(new Cliente("C0020", "Adrián", "Serrano", "adrian.s@mail.com"));
        clientes.add(new Cliente("C0021", "Beatriz", "Núñez", "beatriz.n@mail.com"));
        
        System.out.println("Clientes iniciales creados: " + clientes.size());
    }
    
    // --- Lógica de Negocio (CRUD y Membresías) ---
    
    /**
     * Genera un nuevo ID para el objeto Cliente (e.g., C0022).
     * @return El nuevo ID de cliente.
     */
    public String generarNuevoClienteId()
    {
        return String.format("C%04d", nextClienteId.getAndIncrement());
    }

    /**
     * Añade un nuevo cliente.
     * @param cliente El objeto Cliente a añadir.
     * @throws ExcepcionDatos Si ya existe un cliente con el mismo ID.
     */
    public void agregarCliente(Cliente cliente) throws ExcepcionDatos
    {
        if (obtenerClientePorId(cliente.getIdCliente()).isPresent())
        {
            throw new ExcepcionDatos("Ya existe un cliente con el ID: " + cliente.getIdCliente());
        }
        this.clientes.add(cliente);
        guardarClientes();
    }
    
    /**
     * Actualiza los datos de un cliente existente.
     * @param cliente El Cliente con los datos actualizados.
     * @throws ExcepcionDatos Si el cliente no existe.
     */
    public void actualizarCliente(Cliente cliente) throws ExcepcionDatos
    {
        for (int i = 0; i < clientes.size(); i++)
        {
            if (clientes.get(i).getIdCliente().equals(cliente.getIdCliente()))
            {
                clientes.set(i, cliente);
                guardarClientes();
                return;
            }
        }
        throw new ExcepcionDatos("No se encontró el cliente con ID: " + cliente.getIdCliente() + " para actualizar.");
    }
    
    /**
     * Elimina un cliente.
     * @param idCliente El ID del cliente a eliminar.
     * @throws ExcepcionDatos Si el cliente no existe.
     */
    public void eliminarCliente(String idCliente) throws ExcepcionDatos
    {
        boolean eliminado = clientes.removeIf(c -> c.getIdCliente().equals(idCliente));
        if (!eliminado)
        {
            throw new ExcepcionDatos("No se encontró el cliente con ID: " + idCliente + " para eliminar.");
        }
        guardarClientes();
    }

    /**
     * Asigna una membresía recién creada al historial de un cliente.
     * Nota: La Membresia debe ser creada primero por MembresiaService.
     * @param idCliente El ID del cliente.
     * @param membresia La Membresia a asignar.
     * @throws ExcepcionDatos Si el cliente no existe.
     */
    public void asignarMembresia(String idCliente, Membresia membresia) throws ExcepcionDatos
    {
        Optional<Cliente> clienteOpt = obtenerClientePorId(idCliente);
        
        if (clienteOpt.isEmpty())
        {
            throw new ExcepcionDatos("Cliente no encontrado con ID: " + idCliente);
        }
        
        Cliente cliente = clienteOpt.get();
        cliente.getMembresias().add(membresia);
        // Guardar el cliente actualizado
        actualizarCliente(cliente); 
    }
    
    // --- Lógica de Control de Acceso (ControlAccesoMarquez) ---
    
    /**
     * Verifica si un cliente tiene una membresía activa y registra su acceso.
     * @param idCliente ID del cliente.
     * @param esEntrada True si es una entrada, False si es una salida.
     * @throws ExcepcionDatos Si el cliente no existe o su membresía no está activa.
     */
    public void registrarAcceso(String idCliente, boolean esEntrada) throws ExcepcionDatos
    {
        Optional<Cliente> clienteOpt = obtenerClientePorId(idCliente);
        
        if (clienteOpt.isEmpty())
        {
            throw new ExcepcionDatos("Acceso Denegado: Cliente no registrado.");
        }
        
        Cliente cliente = clienteOpt.get();
        
        if (esEntrada)
        {
            // En la entrada, verificamos la membresía
            if (!tieneMembresiaActiva(cliente))
            {
                throw new ExcepcionDatos("Acceso Denegado: La membresía del cliente " + cliente.getIdCliente() + " ha expirado o no existe.");
            }
            cliente.registrarAcceso("Entrada: " + LocalDateTime.now().format(ACCESS_FORMATTER));
            System.out.println("Acceso REGISTRADO para cliente " + idCliente);

        }
        else
        {
            // En la salida, solo registramos
            cliente.registrarAcceso("Salida: " + LocalDateTime.now().format(ACCESS_FORMATTER));
            System.out.println("Salida REGISTRADA para cliente " + idCliente);
        }
        
        // El cliente debe ser actualizado para guardar el historial de acceso
        actualizarCliente(cliente);
    }
    
    /**
     * Revisa si el cliente tiene al menos una membresía activa.
     */
    public boolean tieneMembresiaActiva(Cliente cliente)
    {
        return cliente.getMembresias().stream()
                .anyMatch(Membresia::estaActiva);
    }
    
    // --- Getters y Búsqueda ---
    
    public List<Cliente> getClientes()
    {
        return new ArrayList<>(clientes);
    }
    
    public Optional<Cliente> obtenerClientePorId(String idCliente)
    {
        return clientes.stream()
                .filter(c -> c.getIdCliente().equals(idCliente))
                .findFirst();
    }
    
    /**
     * Busca clientes por nombre o apellido (simula una búsqueda parcial).
     */
    public List<Cliente> buscarClientes(String query)
    {
        String lowerCaseQuery = query.toLowerCase();
        return clientes.stream()
                .filter(c -> c.getNombre().toLowerCase().contains(lowerCaseQuery) || 
                             c.getApellido().toLowerCase().contains(lowerCaseQuery))
                .collect(Collectors.toList());
    }
    
    /**
     * Obtiene una lista de clientes con membresía activa.
     */
    public List<Cliente> obtenerClientesActivos()
    {
        return clientes.stream()
                .filter(this::tieneMembresiaActiva)
                .collect(Collectors.toList());
    }
    
    public Optional<Cliente> buscarClientePorNombreCompleto(String nombre, String apellido) {
    String nombreBusqueda = nombre.trim().toLowerCase();
    String apellidoBusqueda = apellido.trim().toLowerCase();
    
    return clientes.stream()
            .filter(cliente -> cliente.getNombre().trim().toLowerCase().equals(nombreBusqueda) &&
                               cliente.getApellido().trim().toLowerCase().equals(apellidoBusqueda))
            .findFirst(); // Devuelve el primer cliente que coincida
    }
    
}




