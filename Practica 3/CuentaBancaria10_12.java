public class CuentaBancaria10_12 {
    // Atributos privados
    private String titular;
    private String numeroCuenta;
    private double saldo;
    private boolean activa;

    // Getters
    public String getTitular() {
        return titular;
    }

    public String getNumeroCuenta() {
        return numeroCuenta;
    }

    public double getSaldo() {
        return saldo;
    }

    public boolean isActiva() {
        return activa;
    }

    // Setters con validaciones usando los últimos dígitos de matrícula (9698)
    public void setTitular(String titular) {
        if (titular != null && titular.length() >= 4) {
            this.titular = titular;
        }
    }

    public void setNumeroCuenta(String numeroCuenta) {
        if (numeroCuenta != null && numeroCuenta.endsWith("9698")) {
            this.numeroCuenta = numeroCuenta;
        }
    }

    public void setSaldo(double saldo) {
        if (saldo >= 9698) {
            this.saldo = saldo;
        }
    }

    public void setActiva(boolean activa) {
        this.activa = activa;
    }

    // Método toString personalizado
    @Override
    public String toString() {
        return "CuentaBancaria{" +
                "titular='" + titular + '\'' +
                ", numeroCuenta='" + numeroCuenta + '\'' +
                ", saldo=" + saldo +
                ", activa=" + activa +
                '}';
    }
}
