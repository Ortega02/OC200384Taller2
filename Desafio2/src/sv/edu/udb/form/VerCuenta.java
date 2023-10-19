package sv.edu.udb.form;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import sv.edu.udb.beans.VerCuentaBeans;
import sv.edu.udb.datos.VerCuentaDatos;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class VerCuenta extends JFrame {
    private JPanel verCuentaPanel;
    private JTable datosTable;
    private JComboBox cuentasCombo;
    private JButton obtenerInformaciónButton;
    private JButton menuButton;
    private Menu menuForm;
    public VerCuenta(String dui) {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(verCuentaPanel);
        this.pack();
        this.setLocationRelativeTo(null);

        VerCuentaDatos verCuentaDatos = new VerCuentaDatos();
        List<String> cuentas = verCuentaDatos.getCuentasByDUI(dui);
        for (String cuenta : cuentas) {
            // Formatear el número de cuenta para agregar ceros a la izquierda
            String formattedCuenta = String.format("%010d", Integer.parseInt(cuenta));
            cuentasCombo.addItem(formattedCuenta);
        }
        obtenerInformaciónButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Verificar si se ha seleccionado una cuenta en el ComboBox
                if (cuentasCombo.getSelectedItem() == null) {
                    JOptionPane.showMessageDialog(null, "Por favor, seleccione una cuenta.");
                    return;
                }

                // Obtener el número de cuenta seleccionado
                String numeroCuenta = cuentasCombo.getSelectedItem().toString();

                // Llamar a la clase VerCuentaDatos para obtener la información
                VerCuentaDatos verCuentaDatos = new VerCuentaDatos();
                VerCuentaBeans cuentaInfo = verCuentaDatos.obtenerInformacionCuenta(numeroCuenta);

                // Verificar si se encontraron datos para la cuenta
                if (cuentaInfo != null) {
                    // Formatear el número de cuenta con diez dígitos
                    String numeroCuentaFormateado = String.format("%010d", Integer.parseInt(cuentaInfo.getNumeroCuenta()));

                    // Crear un modelo de tabla para mostrar la información
                    DefaultTableModel tableModel = new DefaultTableModel();
                    tableModel.addColumn("Número de Cuenta");
                    tableModel.addColumn("Titular de la Cuenta");
                    tableModel.addColumn("Saldo");
                    tableModel.addColumn("Transacciones Realizadas");  // Nueva columna

                    // Agregar la información de la cuenta
                    tableModel.addRow(new Object[] {
                            numeroCuentaFormateado,
                            cuentaInfo.getNombreTitular(),
                            cuentaInfo.getSaldo(),
                            cuentaInfo.getTransaccionesRealizadas()
                    });

                    datosTable.setModel(tableModel);
                } else {
                    // Si no se encontraron datos para la cuenta
                    JOptionPane.showMessageDialog(null, "No se encontraron datos para la cuenta.");
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

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                VerCuenta verCuentaForm = new VerCuenta("DUI_DEFAULT");
                verCuentaForm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                verCuentaForm.setSize(450, 500);
                verCuentaForm.setVisible(true);
            }
        });
    }
}
