package sv.edu.udb.beans;

public class LoginBeans {
    private String dui;
    private String pin;

    public LoginBeans(String dui, String pin) {
        this.dui = dui;
        this.pin = pin;
    }

    public String getDui() {
        return dui;
    }

    public void setDui(String dui) {
        this.dui = dui;
    }

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }
}
