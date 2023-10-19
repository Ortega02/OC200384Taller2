package sv.edu.udb.form;

import javax.swing.*;

import sv.edu.udb.beans.RetiroBeans;
import sv.edu.udb.datos.RetiroDatos;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class Retiro extends JFrame {
    private JRadioButton $50RadioButton;
    private JRadioButton $20RadioButton;
    private JRadioButton $10RadioButton;
    private JRadioButton $100RadioButton;
    private JRadioButton $500RadioButton;
    private JButton retirarEfectivoButton;
    private JComboBox cuentasCombo;
    private JPanel retiroPanel;
    private JButton menuButton;
    private Menu menuForm;

    public Retiro(String dui) {

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(retiroPanel);
        this.pack();
        this.setLocationRelativeTo(null);

        RetiroDatos retiroDatos = new RetiroDatos();
        List<String> cuentas = retiroDatos.getCuentasByDUI(dui);
        for (String cuenta : cuentas) {
            // Formatear el número de cuenta para agregar ceros a la izquierda
            String formattedCuenta = String.format("%010d", Integer.parseInt(cuenta));
            cuentasCombo.addItem(formattedCuenta);
        }

        $10RadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        $20RadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        $50RadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        $100RadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        $500RadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        retirarEfectivoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                // Verificar si se ha seleccionado una cuenta en el ComboBox
                if (cuentasCombo.getSelectedItem() == null) {
                    JOptionPane.showMessageDialog(null, "Por favor, seleccione una cuenta.");
                    return;
                }
                // Obtener el valor del radio button seleccionado
                double cantidad = 0.0;  // Valor predeterminado

                if ($10RadioButton.isSelected()) {
                    cantidad = 10.0;
                } else if ($20RadioButton.isSelected()) {
                    cantidad = 20.0;
                } else if ($50RadioButton.isSelected()) {
                    cantidad = 50.0;
                } else if ($100RadioButton.isSelected()) {
                    cantidad = 100.0;
                } else if ($500RadioButton.isSelected()) {
                    cantidad = 500.0;
                }

                // Obtener el número de cuenta seleccionado desde el combo box
                String numeroCuenta = (String) cuentasCombo.getSelectedItem();

                if (numeroCuenta != null && cantidad > 0.0) {
                    // Obtener el saldo de la cuenta
                    double saldoCuenta = retiroDatos.obtenerSaldoCuenta(numeroCuenta);

                    if (cantidad <= saldoCuenta) {
                        // Crear un objeto RetiroBeans con los datos del retiro
                        RetiroBeans retiro = new RetiroBeans(Long.parseLong(numeroCuenta), "retiro", cantidad);

                        // Llamar a la clase RetiroDatos para realizar el retiro
                        RetiroDatos retiroDatos = new RetiroDatos();
                        boolean retiroExitoso = retiroDatos.realizarRetiro(retiro);

                        if (retiroExitoso) {
                            JOptionPane.showMessageDialog(null, "Retiro exitoso.");

                            // Cerrar la ventana de retiro
                            dispose();
                            menuForm = new Menu(dui); // Pasa el DUI como argumento
                            menuForm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Puedes ajustar esto según tus necesidades
                            menuForm.setSize(800, 600); // Ajusta el tamaño según tus necesidades
                            menuForm.setVisible(true);
                        } else {
                            JOptionPane.showMessageDialog(null, "Error en el retiro.");
                            // Aquí puedes manejar el caso de error
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "No tienes saldo suficiente para realizar este retiro.");
                        // Puedes regresar al formulario de retiro para que seleccione una cantidad válida
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Selecciona una cuenta y un monto de retiro válido.");
                    // Aquí puedes manejar el caso de selección incorrecta
                }

            }
        });
        menuButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Cerrar la ventana de retiro
                dispose();
                menuForm = new Menu(dui); // Pasa el DUI como argumento
                menuForm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                menuForm.setSize(800, 600);
                menuForm.setVisible(true);
            }
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                Retiro retiroForm = new Retiro("DUI_DEFAULT");
                retiroForm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                retiroForm.setSize(500, 500);
                retiroForm.setVisible(true);
            }
        });
    }
}
