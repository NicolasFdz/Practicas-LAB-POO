package gympos.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


public class Cliente implements Serializable
{
    private static final long serialVersionUID = 2L;

    private String idCliente;
    private String nombre;
    private String apellido;
    private String email;
    private int puntos; // Requisito: Sistema de puntos/recompensas
    private List<Membresia> membresias;
    // Lista de registros de acceso (para ControlAccesoMarquez)
    private List<String> historialAccesos; 

    public Cliente(String idCliente, String nombre, String apellido, String email)
    {
        this.idCliente = idCliente;
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
        this.puntos = 0;
        this.membresias = new ArrayList<>();
        this.historialAccesos = new ArrayList<>();
    }


    public void agregarPuntos(int cantidad)
    {
        if (cantidad > 0)
        {
            this.puntos += cantidad;
        }
    }


    public void registrarAcceso(String registro)
    {
        this.historialAccesos.add(registro);
    }


    public String getIdCliente()
    {
        return idCliente;
    }

    public void setIdCliente(String idCliente)
    {
        this.idCliente = idCliente;
    }

    public String getNombre()
    {
        return nombre;
    }

    public void setNombre(String nombre)
    {
        this.nombre = nombre;
    }

    public String getApellido()
    {
        return apellido;
    }

    public void setApellido(String apellido)
    {
        this.apellido = apellido;
    }

    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public int getPuntos()
    {
        return puntos;
    }

    public void setPuntos(int puntos)
    {
        this.puntos = puntos;
    }

    public List<Membresia> getMembresias()
    {
        return membresias;
    }

    public List<String> getHistorialAccesos()
    {
        return historialAccesos;
    }

}
