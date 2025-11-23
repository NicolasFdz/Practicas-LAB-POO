// gympos.service.ReporteTask.java

package gympos.service;

import javax.swing.SwingWorker;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.logging.Logger;

// T = File (Resultado), V = Void (no reportaremos progreso detallado)
public class ReporteTask extends SwingWorker<File, Void> { 
    
    private static final Logger logger = Logger.getLogger(ReporteTask.class.getName());
    private final String tipoReporte;
    private final String formato;
    private final String rutaDestino;
    
    public ReporteTask(String tipoReporte, String formato, String rutaDestino) {
        this.tipoReporte = tipoReporte;
        this.formato = formato;
        this.rutaDestino = rutaDestino;
    }
    
    @Override
    protected File doInBackground() throws Exception {
        // La lógica pesada va aquí.
        logger.info("Iniciando la generación del reporte: " + tipoReporte + " en formato " + formato);
        
        // Simulación de consulta y procesamiento (para que se note que es concurrente)
        Thread.sleep(1500); 

        // 1. Definir la ruta del archivo
        File outputFile = new File(rutaDestino);
        
        // 2. Generar el contenido TXT (la forma más simple)
        try (PrintWriter writer = new PrintWriter(new FileWriter(outputFile))) {
            
            writer.println("==============================================");
            writer.println("   GYMPOS - REPORTE ESTADÍSTICO DE GIMNASIO   ");
            writer.println("==============================================");
            writer.println("Tipo de Reporte: " + tipoReporte);
            writer.println("Fecha de Generación: " + new java.util.Date());
            writer.println("----------------------------------------------");
            
            // --- DATOS ESTADÍSTICOS SIMULADOS ---
            writer.println("  Estadística 1: Ingresos Totales del Mes: $15,450.00");
            writer.println("  Estadística 2: Nuevos Clientes: 5");
            writer.println("  Estadística 3: Membresías Vencidas: 7");
            writer.println("----------------------------------------------");
            
        } catch (java.io.IOException e) {
            logger.severe("Error de I/O al escribir el archivo: " + e.getMessage());
            throw new Exception("Error de escritura en disco.", e);
        }
        
        logger.info("Archivo TXT generado en: " + outputFile.getAbsolutePath());
        return outputFile;
    }
    
}
