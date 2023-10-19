package sv.edu.udb.form;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import sv.edu.udb.beans.CuentaBeans;
import sv.edu.udb.datos.CuentaDatos;


public class Menu extends JFrame {
    private JButton crearButton;
    private JButton retiroButton;
    private JButton abonarButton;
    private JButton cuentaButton;
    private JButton todasButton;
    private JButton transaccionesButton;
    private JButton salirButton;
    private JPanel menuPanel;
    private JLabel duiLabel;
    private Login loginForm;
    private Abono abonoForm;
    private Retiro retiroForm;
    private VerCuenta verCuentaForm;
    private VerCuentas verCuentasForm;
    private VerTransacciones verTransaccionesForm;

    public Menu(String dui) {

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(menuPanel);
        this.pack();
        this.setLocationRelativeTo(null);

        duiLabel.setText("DUI: " + dui);

        crearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {


                // Crea una instancia de CuentaBeans con los datos
                CuentaBeans cuenta = new CuentaBeans(0.0, dui);

                // Llama a la clase CuentaDatos para insertar la nueva cuenta
                CuentaDatos cuentaDatos = new CuentaDatos();
                int registrosAfectados = cuentaDatos.insert(cuenta);

                if (registrosAfectados > 0) {
                    // Éxito: La cuenta se creó correctamente
                    JOptionPane.showMessageDialog(null, "Cuenta creada exitosamente");
                } else {
                    // Error: No se pudo crear la cuenta
                    JOptionPane.showMessageDialog(null, "Error al crear la cuenta.");
                }
            }
        });
        retiroButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                dispose();
                retiroForm = new Retiro(dui); // Pasa el DUI como argumento
                retiroForm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                retiroForm.setSize(800, 600);
                retiroForm.setVisible(true);
            }
        });
        abonarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                dispose();
                abonoForm = new Abono(dui); // Pasa el DUI como argumento
                abonoForm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                abonoForm.setSize(800, 600);
                abonoForm.setVisible(true);
            }
        });
        cuentaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                verCuentaForm = new VerCuenta(dui); // Pasa el DUI como argumento
                verCuentaForm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                verCuentaForm.setSize(800, 600);
                verCuentaForm.setVisible(true);
            }
        });
        todasButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                verCuentasForm = new VerCuentas(dui); // Pasa el DUI como argumento
                verCuentasForm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                verCuentasForm.setSize(800, 600);
                verCuentasForm.setVisible(true);
            }
        });
        transaccionesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                verTransaccionesForm = new VerTransacciones(dui); // Pasa el DUI como argumento
                verTransaccionesForm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                verTransaccionesForm.setSize(800, 600);
                verTransaccionesForm.setVisible(true);
            }
        });
        salirButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                loginForm = new Login(); // Pasa el DUI como argumento
                loginForm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                loginForm.setSize(800, 600);
                loginForm.setVisible(true);
            }
        });
    }


    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                Menu menuForm = new Menu("DUI_DEFAULT");
                menuForm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                menuForm.setSize(430, 500);
                menuForm.setVisible(true);
            }
        });
    }
}
