package gympos.model;

import java.io.Serializable;
import java.time.DayOfWeek;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * Representa una clase grupal programada en el calendario.
 * Requisito: Calendario de clases grupales.
 */
public class ClaseGrupal implements Serializable
{
    private static final long serialVersionUID = 9L;

    private String idClase;
    private String nombreClase;
    private Empleado entrenador; // El Empleado que imparte la clase
    private DayOfWeek diaSemana;
    private LocalTime horaInicio;
    private LocalTime horaFin;
    private int capacidadMaxima;
    // Lista de IDs de cliente inscritos (simulación de registro)
    private List<String> clientesInscritosIds;

    public ClaseGrupal(String idClase, String nombreClase, Empleado entrenador, DayOfWeek diaSemana, String horaInicio, String horaFin, int capacidadMaxima) throws ExcepcionDatos
    {
        if (capacidadMaxima <= 0)
        {
            throw new ExcepcionDatos("La capacidad máxima debe ser mayor que cero.");
        }
        
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("hh:mm");

        LocalTime fechaHoraInicio = LocalTime.parse(horaInicio, formatter);
        LocalTime fechaHoraFin = LocalTime.parse(horaFin, formatter);

        if (fechaHoraInicio.isAfter(fechaHoraFin) || fechaHoraInicio.equals(fechaHoraFin))
        {
            throw new ExcepcionDatos("La hora de inicio debe ser anterior a la hora de fin.");
        }
        this.idClase = idClase;
        this.nombreClase = nombreClase;
        this.entrenador = entrenador;
        this.diaSemana = diaSemana;
        this.horaInicio = fechaHoraInicio;
        this.horaFin = fechaHoraFin;
        this.capacidadMaxima = capacidadMaxima;
        this.clientesInscritosIds = new ArrayList<>();
    }



    public void inscribirCliente(String idCliente) throws ExcepcionDatos
    {
        if (clientesInscritosIds.size() >= capacidadMaxima)
        {
            throw new ExcepcionDatos("Clase " + nombreClase + " está llena. No se puede inscribir.");
        }
        if (!clientesInscritosIds.contains(idCliente))
        {
            clientesInscritosIds.add(idCliente);
        }
    }

 
    public void cancelarInscripcion(String idCliente)
    {
        clientesInscritosIds.remove(idCliente);
    }

    public int getCuposDisponibles()
    {
        return capacidadMaxima - clientesInscritosIds.size();
    }


    public String getIdClase()
    {
        return idClase;
    }

    public String getNombreClase()
    {
        return nombreClase;
    }

    public void setNombreClase(String nombreClase)
    {
        this.nombreClase = nombreClase;
    }

    public Empleado getEntrenador()
    {
        return entrenador;
    }

    public void setEntrenador(Empleado entrenador)
    {
        this.entrenador = entrenador;
    }

    public DayOfWeek getDiaSemana()
    {
        return diaSemana;
    }

    public LocalTime getHoraInicio()
    {
        return horaInicio;
    }

    public LocalTime getHoraFin()
    {
        return horaFin;
    }

    public int getCapacidadMaxima()
    {
        return capacidadMaxima;
    }

    public List<String> getClientesInscritosIds()
    {
        return clientesInscritosIds;
    }

    public void setCapacidadMaxima(int capacidadMaxima)
    {
        this.capacidadMaxima = capacidadMaxima;
    }
}
