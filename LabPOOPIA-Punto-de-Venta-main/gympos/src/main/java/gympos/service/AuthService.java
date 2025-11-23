package gympos.service;

import gympos.model.Empleado;
import gympos.model.Empleado.Rol;
import gympos.model.ExcepcionDatos;
import gympos.model.SerializadorDatos;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Servicio encargado de gestionar la lista de empleados, su persistencia
 * y la lógica de autenticación (inicio de sesión).
 */
public class AuthService
{
    private static final String FILE_PATH = "empleados.dat";
    private List<Empleado> empleados;
    private Empleado usuarioActual;

    /**
     * Constructor. Inicializa el servicio cargando los datos del archivo.
     * Si no existen, inicializa la lista con datos de prueba.
     */
    public AuthService()
    {
        this.empleados = new ArrayList<>();
        cargarEmpleados();
        if (this.empleados.isEmpty())
        {
            inicializarDatosPrueba();
            guardarEmpleados();
        }
    }

    // --- Lógica de Persistencia ---

    /**
     * Carga la lista de empleados desde el archivo de serialización.
     */
    private void cargarEmpleados()
    {
        try
        {
            // Asume que SerializadorDatos puede manejar List<Empleado>
            this.empleados = SerializadorDatos.cargarDatos(FILE_PATH);
        }
        catch (ExcepcionDatos e)
        {
            System.err.println("Error al cargar empleados: " + e.getMessage());
            // Se inicializa la lista vacía para evitar NullPointerException
            this.empleados = new ArrayList<>(); 
        }
    }

    /**
     * Guarda la lista actual de empleados en el archivo de serialización.
     */
    public void guardarEmpleados()
    {
        try
        {
            SerializadorDatos.guardarDatos(this.empleados, FILE_PATH);
        }
        catch (ExcepcionDatos e)
        {
            System.err.println("Error al guardar empleados: " + e.getMessage());
            // Aquí se podría notificar al usuario en una aplicación real
        }
    }

    /**
     * Inicializa la lista con los 20+ registros de prueba requeridos.
     * Nota: En una aplicación real, las contraseñas deberían ser hasheadas
     * con algoritmos robustos (e.g., BCrypt). Aquí usamos un simple String.
     */
    private void inicializarDatosPrueba()
    {
        System.out.println("Creando datos iniciales de Empleados (20+ registros)...");
        // Empleados Clave
        //public Empleado(String idEmpleado, String usuario, String passwordHash, Rol rol)
        empleados.add(new Empleado("EMP001", "admin", "1234", Rol.ADMIN));
        empleados.add(new Empleado("EMP002", "nicolas.f", "1234", Rol.ADMIN)); // Nicolas Fernandez
        empleados.add(new Empleado("EMP003", "maria.m", "1234", Rol.ADMIN)); // Maria Marquez
        empleados.add(new Empleado("EMP004", "recepcion", "pass", Rol.RECEPCIONISTA));
        empleados.add(new Empleado("EMP005", "trainer", "pass", Rol.ENTRENADOR));

        // Empleados de Prueba Adicionales (hasta superar 20)
        for (int i = 6; i <= 25; i++)
        {
            String id = String.format("EMP%03d", i);
            String user = "empleado" + i;
            Rol rol = (i % 3 == 0) ? Rol.ENTRENADOR : Rol.RECEPCIONISTA;
            empleados.add(new Empleado(id, user, "123", rol));
        }
        System.out.println("Datos iniciales de Empleados creados: " + empleados.size());
    }

    // --- Lógica de Autenticación ---

    /**
     * Intenta autenticar a un empleado con su usuario y contraseña.
     * @param usuario El nombre de usuario.
     * @param password La contraseña (simulada como String plano).
     * @return El objeto Empleado si la autenticación es exitosa.
     * @throws ExcepcionDatos Si el usuario no existe o la contraseña es incorrecta.
     */
    public Empleado autenticar(String usuario, String password) throws ExcepcionDatos
    {
        Optional<Empleado> empleadoOpt = empleados.stream()
                .filter(e -> e.getUsuario().equalsIgnoreCase(usuario))
                .findFirst();

        if (empleadoOpt.isEmpty())
        {
            throw new ExcepcionDatos("Usuario no encontrado.");
        }

        Empleado empleado = empleadoOpt.get();
        // NOTA: Se simula la verificación del "passwordHash"
        if (empleado.getPasswordHash().equals(password))
        { 
            this.usuarioActual = empleado;
            return empleado;
        }
        else
        {
            throw new ExcepcionDatos("Contraseña incorrecta para el usuario: " + usuario);
        }
    }

    // --- Lógica de Gestión (CRUD básico de Empleados) ---

    /**
     * Añade un nuevo empleado a la lista y guarda los datos.
     * @param empleado El objeto Empleado a añadir.
     * @throws ExcepcionDatos Si ya existe un empleado con el mismo usuario o ID.
     */
    public void agregarEmpleado(Empleado empleado) throws ExcepcionDatos
    {
        if (empleados.stream().anyMatch(e -> e.getIdEmpleado().equals(empleado.getIdEmpleado())))
        {
            throw new ExcepcionDatos("Ya existe un empleado con el ID: " + empleado.getIdEmpleado());
        }
        if (empleados.stream().anyMatch(e -> e.getUsuario().equalsIgnoreCase(empleado.getUsuario())))
        {
             throw new ExcepcionDatos("Ya existe un empleado con el nombre de usuario: " + empleado.getUsuario());
        }
        this.empleados.add(empleado);
        guardarEmpleados();
    }
    
    // --- Métodos de Estado ---
    
    public Empleado getUsuarioActual()
    {
        return usuarioActual;
    }

    public List<Empleado> getEmpleados()
    {
        return new ArrayList<>(empleados); // Retorna copia para evitar modificación externa
    }
}