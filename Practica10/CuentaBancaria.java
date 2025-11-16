public class CuentaBancaria {
    private int saldo;

    public CuentaBancaria(int saldoInicial) {
        this.saldo = saldoInicial;
    }

    public synchronized void retirar(int cantidad) {
        if (saldo >= cantidad) {
            saldo -= cantidad;
            System.out.println("Retiro: $" + cantidad + " | Saldo: $" + saldo);
        } else {
            System.out.println("Fondos insuficientes para retirar $" + cantidad);
        }
    }

    public synchronized void depositar(int cantidad) {
        saldo += cantidad;
        System.out.println("Depósito: $" + cantidad + " | Saldo: $" + saldo);
    }

    public int getSaldo() {
        return saldo;
    }
}
