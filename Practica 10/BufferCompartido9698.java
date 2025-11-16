import java.util.LinkedList;
import java.util.Queue;

/**
 * Buffer de tamaño basado en últimos dígitos de matrícula (9698 -> 8).
 */
public class BufferCompartido9698 {
    private final Queue<Integer> buffer = new LinkedList<>();
    private final int capacidad = 8;

    public synchronized void producir(int valor) throws InterruptedException {
        while (buffer.size() == capacidad) {
            System.out.println("[BUFFER] Lleno, productor espera...");
            wait();
        }
        buffer.add(valor);
        System.out.println("[PRODUCIR] " + Thread.currentThread().getName() + " -> " + valor + " | tamaño=" + buffer.size());
        notifyAll();
    }

    public synchronized int consumir() throws InterruptedException {
        while (buffer.isEmpty()) {
            System.out.println("[BUFFER] Vacío, consumidor espera...");
            wait();
        }
        int valor = buffer.remove();
        System.out.println("[CONSUMIR] " + Thread.currentThread().getName() + " <- " + valor + " | tamaño=" + buffer.size());
        notifyAll();
        return valor;
    }
}
