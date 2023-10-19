package sv.edu.udb.form;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import sv.edu.udb.beans.VerCuentasBeans;
import sv.edu.udb.datos.VerCuentasDatos;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class VerCuentas extends JFrame {
    private JTable cuentasTable;
    private JPanel verCuentasPanel;
    private JButton menuButton;
    private Menu menuForm;

    public VerCuentas(String dui) {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(verCuentasPanel);
        this.pack();
        this.setLocationRelativeTo(null);

        // Obtener y mostrar cuentas y transacciones
        mostrarCuentasYTransacciones(dui);
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

    private void mostrarCuentasYTransacciones(String dui) {
        // Crear modelo de tabla
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Número de Cuenta");
        model.addColumn("Titular de la Cuenta");
        model.addColumn("Saldo");
        model.addColumn("Transacciones Realizadas");

        VerCuentasDatos verCuentasDatos = new VerCuentasDatos();
        List<VerCuentasBeans> cuentas = verCuentasDatos.getCuentasYTransacciones(dui);

        for (VerCuentasBeans cuenta : cuentas) {
            String numeroCuenta = String.format("%010d", Integer.parseInt(cuenta.getNumeroCuenta()));
            model.addRow(new Object[]{
                    numeroCuenta,
                    cuenta.getNombreTitular(),
                    cuenta.getSaldo(),
                    cuenta.getTransaccionesRealizadas()
            });
        }

        cuentasTable.setModel(model);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            VerCuentas verCuentasForm = new VerCuentas("DUI_DEFAULT");
            verCuentasForm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            verCuentasForm.setSize(450, 500);
            verCuentasForm.setVisible(true);
        });
    }
}
