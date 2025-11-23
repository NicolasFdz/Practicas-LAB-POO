package gympos.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * Representa una suscripción específica de un cliente.
 * Clave para el SistemaMembresias9Mayo.
 */
public class Membresia implements Serializable
{

    private static final long serialVersionUID = 5L;

    private String idMembresia;
    private TipoMembresia tipo;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private BigDecimal precioPagado;
    private BigDecimal descuentoAplicado; // Requisito: descuentos personalizados

    public Membresia(String idMembresia, TipoMembresia tipo, LocalDate fechaInicio, BigDecimal descuentoAplicado) throws ExcepcionDatos
    {
        if (tipo == null || fechaInicio == null)
        {
            throw new ExcepcionDatos("El tipo de membresía y la fecha de inicio son obligatorios.");
        }
        this.idMembresia = idMembresia;
        this.tipo = tipo;
        this.fechaInicio = fechaInicio;
        this.descuentoAplicado = descuentoAplicado;
        
        this.fechaFin = fechaInicio.plusDays(tipo.getDuracionDias());
        
        // Calcular precio pagado
        BigDecimal precioBruto = tipo.getPrecioBase();
        BigDecimal montoDescuento = precioBruto.multiply(descuentoAplicado);
        this.precioPagado = precioBruto.subtract(montoDescuento);
    }


    public boolean estaActiva()
    {
        LocalDate hoy = LocalDate.now();
        return !hoy.isBefore(fechaInicio) && !hoy.isAfter(fechaFin);
    }


    public boolean esProximoAVencer(int dias)
    {
        return fechaFin.isBefore(LocalDate.now().plusDays(dias)) && !estaActiva();
    }
    

    public String getIdMembresia()
    {
        return idMembresia;
    }

    public TipoMembresia getTipo()
    {
        return tipo;
    }

    public LocalDate getFechaInicio()
    {
        return fechaInicio;
    }

    public LocalDate getFechaFin()
    {
        return fechaFin;
    }

    public BigDecimal getPrecioPagado()
    {
        return precioPagado;
    }

    public BigDecimal getDescuentoAplicado()
    {
        return descuentoAplicado;
    }
    
}
