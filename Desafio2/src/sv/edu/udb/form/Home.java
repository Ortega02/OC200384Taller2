package sv.edu.udb.form;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Home extends JFrame {
    private JPanel homePanel;
    private JButton crearButton;
    private JButton accederButton;
    private JButton salirButton;
    private JFrame mainFrame;
    private Usuario usuarioForm;
    private Login loginForm;

    public Home() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(homePanel);
        this.setSize(600, 400);
        this.setLocationRelativeTo(null);

        crearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Crea una instancia del formulario Usuario
                usuarioForm = new Usuario(); // Crear una única instancia
                usuarioForm.setVisible(true);

                // Cierra la ventana actual de Home
                dispose();
            }
        });

        accederButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Crea una instancia del formulario Usuario
                loginForm = new Login(); // Crear una única instancia
                loginForm.setVisible(true);

                // Cierra la ventana actual de Home
                dispose();
            }
        });
        salirButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //cierra formulario
                dispose();
                //termina la ejecución
                System.exit(0);
            }
        });
    }

    public void setMainFrame(JFrame mainFrame) {
        this.mainFrame = mainFrame;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                Home frame = new Home();
                frame.setVisible(true);
            }
        });
    }
}
