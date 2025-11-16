public class Productor implements Runnable {
    private BufferCompartido9698 buffer;

    public Productor(BufferCompartido9698 buffer) {
        this.buffer = buffer;
    }

    public void run() {
        try {
            for (int i = 0; i < 10; i++) {
                buffer.producir(i);
                Thread.sleep(100);
            }
        } catch (InterruptedException e) {}
    }
}