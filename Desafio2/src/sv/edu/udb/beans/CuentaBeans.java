package sv.edu.udb.beans;

public class CuentaBeans {
    private double saldo;
    private String dui;  // Cambiado de int a String

    public CuentaBeans(double saldo, String dui) {  // Cambiado de int a String
        this.saldo = saldo;
        this.dui = dui;  // Cambiado de int a String
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public String getDui() {  // Cambiado de int a String
        return dui;
    }

    public void setDui(String dui) {  // Cambiado de int a String
        this.dui = dui;
    }
}
