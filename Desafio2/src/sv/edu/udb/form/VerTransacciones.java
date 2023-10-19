package sv.edu.udb.form;

import javax.swing.*;
import sv.edu.udb.beans.TransaccionesBeans;
import sv.edu.udb.datos.TransaccionesDatos;

import javax.swing.table.DefaultTableModel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class VerTransacciones extends JFrame {
    private JTable transaccionesTable;
    private JComboBox cuentasCombo;
    private JPanel transaccionesPanel;
    private JButton transaccionesButton;
    private JButton menuButton;
    private Menu menuForm;
    public VerTransacciones(String dui) {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(transaccionesPanel);
        this.pack();
        this.setLocationRelativeTo(null);

        TransaccionesDatos transaccionesDatos = new TransaccionesDatos();
        List<String> cuentas = transaccionesDatos.getCuentasByDUI(dui);
        for (String cuenta : cuentas) {
            // Formatear el número de cuenta para agregar ceros a la izquierda
            String formattedCuenta = String.format("%010d", Integer.parseInt(cuenta));
            cuentasCombo.addItem(formattedCuenta);
        }

        transaccionesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String numeroCuenta = cuentasCombo.getSelectedItem() != null ? cuentasCombo.getSelectedItem().toString() : null;

                if (numeroCuenta == null) {
                    JOptionPane.showMessageDialog(null, "Por favor, seleccione una cuenta.");
                } else {
                    cargarTransacciones(numeroCuenta);
                }
            }
        });
        menuButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                menuForm = new Menu(dui); // Pasa el DUI como argumento
                menuForm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                menuForm.setSize(800, 600);
                menuForm.setVisible(true);
            }
        });
    }

    private void cargarTransacciones(String numeroCuenta) {
        TransaccionesDatos transaccionesDatos = new TransaccionesDatos();
        List<TransaccionesBeans> transacciones = transaccionesDatos.getTransaccionesByCuenta(numeroCuenta);

        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Tipo");
        model.addColumn("Cantidad");
        model.addColumn("Número de Cuenta");
        model.addColumn("Fecha");

        for (TransaccionesBeans transaccion : transacciones) {
            model.addRow(new Object[]{
                    transaccion.getTipo(),
                    transaccion.getCantidad(),
                    transaccion.getNumeroCuenta(),
                    transaccion.getFecha()
            });
        }

        transaccionesTable.setModel(model);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                VerTransacciones verTransaccionesForm = new VerTransacciones("DUI_DEFAULT");
                verTransaccionesForm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                verTransaccionesForm.setSize(430, 500);
                verTransaccionesForm.setVisible(true);
            }
        });
    }
}
