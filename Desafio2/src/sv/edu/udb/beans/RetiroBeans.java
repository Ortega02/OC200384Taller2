package sv.edu.udb.beans;

public class RetiroBeans {
    private long numeroCuenta;
    private String tipoTransaccion;
    private double cantidad;

    public RetiroBeans(long numeroCuenta, String tipoTransaccion, double cantidad) {
        this.numeroCuenta = numeroCuenta;
        this.tipoTransaccion = tipoTransaccion;
        this.cantidad = cantidad;
    }

    public long getNumeroCuenta() {
        return numeroCuenta;
    }

    public String getTipoTransaccion() {
        return tipoTransaccion;
    }

    public double getCantidad() {
        return cantidad;
    }
    // Otros métodos y atributos según sea necesario
}
