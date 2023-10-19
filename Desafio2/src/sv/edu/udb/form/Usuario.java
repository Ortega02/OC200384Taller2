package sv.edu.udb.form;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import sv.edu.udb.datos.UsuarioDatos;
import sv.edu.udb.beans.UsuarioBeans;

public class Usuario extends JFrame {
    private JPanel usuarioPanel;
    private JLabel usuarioLabel;
    private JLabel nombreLabel;
    private JLabel pinLabel;
    private JLabel duiLabel;
    private JTextField nombreField;
    private JTextField pinField;
    private JTextField duiField;
    private JButton crearButton;
    private JButton regresarButton;
    private Home homeForm;

    public Usuario() {
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Cierra solo la ventana actual
        this.setContentPane(usuarioPanel);
        this.setSize(400, 300);
        this.setLocationRelativeTo(null);

        crearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Obtén los datos ingresados en el formulario
                String nombre = nombreField.getText();
                String pin = pinField.getText();
                String dui = duiField.getText();

                // Verificar si el número de DUI ya existe en la base de datos
                if (verificarDUIExistente(dui)) {
                    // Mostrar un mensaje de error al usuario
                    JOptionPane.showMessageDialog(null, "El número de DUI ya existe en la base de datos. Ingrese un número de DUI diferente.");
                } else {
                    // El número de DUI es único, procede con la inserción en la base de datos
                    UsuarioBeans usuario = new UsuarioBeans(nombre, pin, dui);
                    UsuarioDatos usuarioDatos = new UsuarioDatos();
                    int registrosAfectados = usuarioDatos.insert(usuario);

                    if (registrosAfectados > 0) {
                        // Éxito: los datos se guardaron en la base de datos
                        JOptionPane.showMessageDialog(null, "Registro exitoso, ahora puede acceder a su cuenta.");

                        // Ocultar la ventana de Usuario
                        setVisible(false);

                        // Crear una instancia de la ventana de Home y mostrarla
                        Home homeForm = new Home();
                        homeForm.setVisible(true);
                    } else {
                        // Error: no se pudieron guardar los datos
                        int respuesta = JOptionPane.showConfirmDialog(null, "Error al guardar los datos en la base de datos. ¿Desea intentarlo de nuevo?", "Error", JOptionPane.YES_NO_OPTION);

                        if (respuesta == JOptionPane.YES_OPTION) {
                            // Limpiar los campos para que el usuario pueda volver a ingresar los datos
                            nombreField.setText("");
                            pinField.setText("");
                            duiField.setText("");
                        } else {
                            // Ocultar la ventana de Usuario
                            setVisible(false);
                        }
                    }
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

    private boolean verificarDUIExistente(String dui) {
        // Aquí puedes implementar la lógica para verificar si el número de DUI ya existe en la base de datos.
        // Debes hacer una consulta a la base de datos y devolver true si existe, o false si no existe.
        // Puedes utilizar la clase UsuarioDatos para realizar la consulta.
        // Este método debe ser implementado de acuerdo a la estructura de tu base de datos.
        // Devuelve true si el número de DUI ya existe, o false si es único.
        UsuarioDatos usuarioDatos = new UsuarioDatos();
        return usuarioDatos.existeDUI(dui);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                Usuario usuarioForm = new Usuario();
                usuarioForm.setVisible(true);
            }
        });
    }
}
