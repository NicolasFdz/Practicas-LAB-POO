package gympos.model;

import java.io.Serializable;

/**
 * Representa un artículo o equipo para el control de inventario.
 */
public class InventarioItem implements Serializable
{
    private static final long serialVersionUID = 7L;

    private String idItem;
    private String nombre;
    private String descripcion;
    private int cantidadStock;
    private String estado; 

    public InventarioItem(String idItem, String nombre, String descripcion, int cantidadStock, String estado)
    {
        this.idItem = idItem;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.cantidadStock = cantidadStock;
        this.estado = estado;
    }


    public void ajustarStock(int cambio)
    {
        this.cantidadStock += cambio;
    }

    public String getIdItem()
    {
        return idItem;
    }

    public String getNombre()
    {
        return nombre;
    }

    public void setNombre(String nombre)
    {
        this.nombre = nombre;
    }

    public String getDescripcion()
    {
        return descripcion;
    }

    public void setDescripcion(String descripcion)
    {
        this.descripcion = descripcion;
    }

    public int getCantidadStock()
    {
        return cantidadStock;
    }

    public void setCantidadStock(int cantidadStock)
    {
        this.cantidadStock = cantidadStock;
    }

    public String getEstado()
    {
        return estado;
    }

    public void setEstado(String estado)
    {
        this.estado = estado;
    }
}
