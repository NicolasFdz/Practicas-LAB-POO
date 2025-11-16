public class ClienteRunnable12 implements Runnable {
    private final CuentaBancaria cuenta;

    public ClienteRunnable12(CuentaBancaria cuenta) {
        this.cuenta = cuenta;
    }

    @Override
    public void run() {
        Thread.currentThread().setName("ClienteRunnable12");
        for (int i = 0; i < 5; i++) {
            cuenta.depositar(150);
        }
    }
}
