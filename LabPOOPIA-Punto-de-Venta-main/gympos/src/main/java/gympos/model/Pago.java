package gympos.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Registra una transacción de pago realizada por un cliente.
 * Clave para el ProcesadorPagos3887.
 */
public class Pago implements Serializable
{

    private static final long serialVersionUID = 6L;

    public enum MetodoPago
    {
        EFECTIVO, TARJETA, TRANSFERENCIA
    }

    private String idPago;
    private Membresia membresiaAsociada;
    private LocalDateTime fechaPago;
    private BigDecimal monto;
    private MetodoPago metodo;
    private Empleado empleadoRegistro;

    public Pago(String idPago, Membresia membresiaAsociada, BigDecimal monto, MetodoPago metodo, Empleado empleadoRegistro) throws ExcepcionDatos
    {
        if (membresiaAsociada == null || monto == null || monto.compareTo(BigDecimal.ZERO) <= 0)
        {
            throw new ExcepcionDatos("Membresía asociada y monto válido son obligatorios para el pago.");
        }
        this.idPago = idPago;
        this.membresiaAsociada = membresiaAsociada;
        this.monto = monto;
        this.metodo = metodo;
        this.empleadoRegistro = empleadoRegistro;
        this.fechaPago = LocalDateTime.now(); // Se registra la hora actual del pago
    }


    public String getIdPago()
    {
        return idPago;
    }

    public Membresia getMembresiaAsociada()
    {
        return membresiaAsociada;
    }

    public LocalDateTime getFechaPago()
    {
        return fechaPago;
    }

    public BigDecimal getMonto()
    {
        return monto;
    }

    public MetodoPago getMetodo()
    {
        return metodo;
    }

    public Empleado getEmpleadoRegistro()
    {
        return empleadoRegistro;
    }
    
}
