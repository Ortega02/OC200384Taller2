package sv.edu.udb.form;

import javax.swing.*;
import sv.edu.udb.beans.AbonoBeans;
import sv.edu.udb.datos.AbonoDatos;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;


public class Abono extends JFrame{
    private JPanel abonoPanel;
    private JRadioButton $500RadioButton;
    private JRadioButton $50RadioButton;
    private JRadioButton $10RadioButton;
    private JRadioButton $100RadioButton;
    private JRadioButton $20RadioButton;
    private JButton abonarButton;
    private JComboBox cuentasCombo;
    private JButton menuButton;
    private Menu menuForm;

    public Abono(String dui) {

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(abonoPanel);
        this.pack();
        this.setLocationRelativeTo(null);

        AbonoDatos abonoDatos = new AbonoDatos();
        List<String> cuentas = abonoDatos.getCuentasByDUI(dui);
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
        abonarButton.addActionListener(new ActionListener() {
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
                    // Crear un objeto AbonoBeans con los datos del abono
                    AbonoBeans abono = new AbonoBeans(Long.parseLong(numeroCuenta), "abono", cantidad);

                    // Llamar a la clase AbonoDatos para realizar el abono
                    AbonoDatos abonoDatos = new AbonoDatos();
                    boolean abonoExitoso = abonoDatos.realizarAbono(abono);

                    if (abonoExitoso) {
                        JOptionPane.showMessageDialog(null, "Abono exitoso.");

                        // Cerrar la ventana de abono
                        dispose();
                        menuForm = new Menu(dui); // Pasa el DUI como argumento
                        menuForm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Puedes ajustar esto según tus necesidades
                        menuForm.setSize(800, 600); // Ajusta el tamaño según tus necesidades
                        menuForm.setVisible(true);
                    } else {
                        JOptionPane.showMessageDialog(null, "Error en el abono.");
                        // Aquí manejar el caso de error
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Selecciona una cuenta y un monto de abono válido.");
                    // Aquí manejar el caso de selección incorrecta
                }
            }
        });
        menuButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();

                // Abrir la ventana del menú
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
                Abono abonoForm = new Abono("DUI_DEFAULT");
                abonoForm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                abonoForm.setSize(500, 500);
                abonoForm.setVisible(true);
            }
        });
    }

}
