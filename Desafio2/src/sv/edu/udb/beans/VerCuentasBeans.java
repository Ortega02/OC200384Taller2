package sv.edu.udb.beans;

public class VerCuentasBeans {
    private String numeroCuenta;
    private String nombreTitular;
    private double saldo;
    private int transaccionesRealizadas;

    public VerCuentasBeans(String numeroCuenta, String nombreTitular, double saldo, int transaccionesRealizadas) {
        this.numeroCuenta = numeroCuenta;
        this.nombreTitular = nombreTitular;
        this.saldo = saldo;
        this.transaccionesRealizadas = transaccionesRealizadas;
    }

    public String getNumeroCuenta() {
        return numeroCuenta;
    }

    public void setNumeroCuenta(String numeroCuenta) {
        this.numeroCuenta = numeroCuenta;
    }

    public String getNombreTitular() {
        return nombreTitular;
    }

    public void setNombreTitular(String nombreTitular) {
        this.nombreTitular = nombreTitular;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public int getTransaccionesRealizadas() {
        return transaccionesRealizadas;
    }

    public void setTransaccionesRealizadas(int transaccionesRealizadas) {
        this.transaccionesRealizadas = transaccionesRealizadas;
    }
}
