import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadPoolFernandez {
    public void ejecutarTareas() {
        ExecutorService pool = Executors.newFixedThreadPool(3);
        for (int i = 0; i < 5; i++) {
            pool.execute(() -> System.out.println("Tarea ejecutada por " + Thread.currentThread().getName()));
        }
        pool.shutdown();
    }
}
