package gympos.model;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Representa una recompensa que puede ser canjeada usando los puntos del cliente.
 * Requisito: Sistema de puntos/recompensas.
 */
public class Recompensa implements Serializable
{
    private static final long serialVersionUID = 8L;

    private String idRecompensa;
    private String nombre;
    private String descripcion;
    private int costoEnPuntos;
    private BigDecimal valorDescuento; // Puede ser un valor monetario o 0.00
    private int stockDisponible;

  
    public Recompensa(String idRecompensa, String nombre, String descripcion, int costoEnPuntos, BigDecimal valorDescuento, int stockDisponible) throws ExcepcionDatos
    {
        if (costoEnPuntos <= 0)
        {
            throw new ExcepcionDatos("El costo de la recompensa debe ser positivo.");
        }
        this.idRecompensa = idRecompensa;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.costoEnPuntos = costoEnPuntos;
        this.valorDescuento = valorDescuento;
        this.stockDisponible = stockDisponible;
    }


    public void canjear() throws ExcepcionDatos
    {
        if (this.stockDisponible <= 0)
        {
            throw new ExcepcionDatos("Stock agotado para la recompensa: " + this.nombre);
        }
        this.stockDisponible--;
    }

    public void devolverStock()
    {
        this.stockDisponible++;
    }

    public String getIdRecompensa()
    {
        return idRecompensa;
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

    public int getCostoEnPuntos()
    {
        return costoEnPuntos;
    }

    public void setCostoEnPuntos(int costoEnPuntos) throws ExcepcionDatos
    {
         if (costoEnPuntos <= 0)
         {
            throw new ExcepcionDatos("El costo en puntos debe ser positivo.");
        }
        this.costoEnPuntos = costoEnPuntos;
    }

    public BigDecimal getValorDescuento()
    {
        return valorDescuento;
    }

    public void setValorDescuento(BigDecimal valorDescuento)
    {
        this.valorDescuento = valorDescuento;
    }

    public int getStockDisponible()
    {
        return stockDisponible;
    }

    public void setStockDisponible(int stockDisponible)
    {
        this.stockDisponible = stockDisponible;
    }
}
