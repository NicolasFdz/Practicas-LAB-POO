package gympos.model;

import java.io.Serializable;

public class Empleado implements Serializable
{
    private static final long serialVersionUID = 3L;

    public enum Rol
    {
        ADMIN, RECEPCIONISTA, ENTRENADOR
    }

    private String idEmpleado;
    private String usuario; 
    private String passwordHash;
    private Rol rol;

    public Empleado(String idEmpleado, String usuario, String passwordHash, Rol rol)
    {
        this.idEmpleado = idEmpleado;
        this.usuario = usuario;
        this.passwordHash = passwordHash;
        this.rol = rol;
    }



    public String getIdEmpleado()
    {
        return idEmpleado;
    }

    public void setIdEmpleado(String idEmpleado)
    {
        this.idEmpleado = idEmpleado;
    }

    public String getUsuario()
    {
        return usuario;
    }

    public void setUsuario(String usuario)
    {
        this.usuario = usuario;
    }

    public String getPasswordHash()
    {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash)
    {
        this.passwordHash = passwordHash;
    }

    public Rol getRol()
    {
        return rol;
    }

    public void setRol(Rol rol)
    {
        this.rol = rol;
    }
}
