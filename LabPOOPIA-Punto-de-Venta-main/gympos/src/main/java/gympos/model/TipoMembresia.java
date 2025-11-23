package gympos.model;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Define los tipos de membresía disponibles en el gimnasio.
 * Implementa Serializable para persistencia.
 */
public class TipoMembresia implements Serializable
{
    private static final long serialVersionUID = 4L;

    private String nombre;
    private int duracionDias;
    private BigDecimal precioBase;
    private boolean accesoIlimitadoClases;

    public TipoMembresia(String nombre, int duracionDias, BigDecimal precioBase, boolean accesoIlimitadoClases)
    {
        this.nombre = nombre;
        this.duracionDias = duracionDias;
        this.precioBase = precioBase;
        this.accesoIlimitadoClases = accesoIlimitadoClases;
    }


    public String getNombre()
    {
        return nombre;
    }

    public void setNombre(String nombre)
    {
        this.nombre = nombre;
    }

    public int getDuracionDias()
    {
        return duracionDias;
    }

    public void setDuracionDias(int duracionDias)
    {
        this.duracionDias = duracionDias;
    }

    public BigDecimal getPrecioBase()
    {
        return precioBase;
    }

    public void setPrecioBase(BigDecimal precioBase)
    {
        this.precioBase = precioBase;
    }

    public boolean isAccesoIlimitadoClases()
    {
        return accesoIlimitadoClases;
    }

    public void setAccesoIlimitadoClases(boolean accesoIlimitadoClases)
    {
        this.accesoIlimitadoClases = accesoIlimitadoClases;
    }
}
