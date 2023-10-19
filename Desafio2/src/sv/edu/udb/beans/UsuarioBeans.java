package sv.edu.udb.beans;

public class UsuarioBeans {
    private String nombre;
    private String pin;
    private String dui;

    public UsuarioBeans(String nombre, String pin, String dui) {
        this.nombre = nombre;
        this.pin = pin;
        this.dui = dui;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }

    public String getDui() {
        return dui;
    }

    public void setDui(String dui) {
        this.dui = dui;
    }
}
