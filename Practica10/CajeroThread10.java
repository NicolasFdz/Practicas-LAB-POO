public class CajeroThread10 extends Thread {
    private final CuentaBancaria cuenta;

    public CajeroThread10(CuentaBancaria cuenta) {
        super("CajeroThread10");
        this.cuenta = cuenta;
    }

    @Override
    public void run() {
        for (int i = 0; i < 5; i++) {
            cuenta.retirar(100);
        }
    }
}
