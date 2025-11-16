public class Consumidor implements Runnable {
    private BufferCompartido9698 buffer;

    public Consumidor(BufferCompartido9698 buffer) {
        this.buffer = buffer;
    }

    public void run() {
        try {
            for (int i = 0; i < 10; i++) {
                buffer.consumir();
                Thread.sleep(150);
            }
        } catch (InterruptedException e) {}
    }
}