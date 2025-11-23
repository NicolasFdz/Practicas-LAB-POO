package gympos.model;

import java.io.Serializable;


public class ExcepcionDatos extends Exception implements Serializable
{

    private static final long serialVersionUID = 1L;


    public ExcepcionDatos(String message)
    {
        super(message);
    }


    public ExcepcionDatos(String message, Throwable cause)
    {
        super(message, cause);
    }
}
