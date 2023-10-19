package sv.edu.udb.beans;

public class TransaccionesBeans {
    private String tipo;
    private double cantidad;
    private String numeroCuenta;
    private String fecha;

    public TransaccionesBeans(String tipo, double cantidad, String numeroCuenta, String fecha) {
        this.tipo = tipo;
        this.cantidad = cantidad;
        this.numeroCuenta = numeroCuenta;
        this.fecha = fecha;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public double getCantidad() {
        return cantidad;
    }

    public void setCantidad(double cantidad) {
        this.cantidad = cantidad;
    }

    public String getNumeroCuenta() {
        return numeroCuenta;
    }

    public void setNumeroCuenta(String numeroCuenta) {
        this.numeroCuenta = numeroCuenta;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
}
