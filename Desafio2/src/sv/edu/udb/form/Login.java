package sv.edu.udb.form;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import sv.edu.udb.datos.LoginDatos;
import sv.edu.udb.beans.LoginBeans;

public class Login extends JFrame {
    private JPanel loginPanel;
    private JTextField duiField;
    private JPasswordField pinField;
    private JButton entrarButton;
    private JButton regresarButton;
    private Menu menuForm;
    private Home homeForm;

    public Login() {

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(loginPanel);
        this.pack();
        this.setLocationRelativeTo(null);

        entrarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String dui = duiField.getText();
                char[] pinChars = pinField.getPassword();
                String pin = new String(pinChars);

                // Crea una instancia de LoginBeans con los datos
                LoginBeans login = new LoginBeans(dui, pin);

                // Llama a la clase LoginDatos para autenticar
                LoginDatos loginDatos = new LoginDatos();
                boolean autenticado = loginDatos.autenticar(dui, pin);

                if (autenticado) {
                    // Éxito: Los datos de inicio de sesión son válidos
                    JOptionPane.showMessageDialog(null, "Inicio de sesión exitoso.");
                    // Cerrar la ventana de inicio de sesión
                    dispose();

                    // Abrir la ventana del menú
                    menuForm = new Menu(dui); // Pasa el DUI como argumento
                    menuForm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    menuForm.setSize(800, 600);
                    menuForm.setVisible(true);
                } else {
                    // Error: Datos de inicio de sesión incorrectos
                    JOptionPane.showMessageDialog(null, "DUI y/o PIN incorrecto(s).");
                    // No redirecciona a la siguiente vista, permitiendo que el usuario intente nuevamente
                }
            }
        });
        regresarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                homeForm = new Home(); // Pasa el DUI como argumento
                homeForm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                homeForm.setSize(800, 600);
                homeForm.setVisible(true);
            }
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                Login loginForm = new Login();
                loginForm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                loginForm.setSize(500, 500);
                loginForm.setVisible(true);
            }
        });
    }
}
