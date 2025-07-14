package interfaz;

import Logica.Estudiante;
import Logica.EstudianteFactory;

import javax.swing.*;
import java.awt.*;
import java.util.Set;

/**
 * Panel que permite al Estudiante iniciar sesión.
 */
public class PanelInicioEstudiante extends JPanel {
    JTextField campoCorreo;
    JTextField campoPassword;

    /**
     * Inicializa el panel con los componentes necesarios para el inicio de sesión.
     */
    public PanelInicioEstudiante(){
        setBackground(new Color(30, 30, 30));
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        InterfazUtils.agregarTitulo("Inicio Sesión Estudiante", this);
        add(Box.createRigidArea(new Dimension(0, 100)));

        agregarTexto();
        add(Box.createRigidArea(new Dimension(0, 160)));
        agregarBotones();

        repaint();
        revalidate();
    }

    /**
     * Agrega los campos de texto para correo y contraseña al panel.
     */
    private void agregarTexto(){
        JPanel panel = new JPanel(new GridLayout(5, 4));
        panel.setOpaque(false);
        panel.setAlignmentX(Component.CENTER_ALIGNMENT);

        Font fuente = new Font("Arial", Font.BOLD, 25);
        JLabel labelCorreo = InterfazUtils.label("Correo:", fuente);
        JLabel labelPassword = InterfazUtils.label("Contraseña:", fuente);

        campoCorreo = new JTextField();
        campoPassword = new JPasswordField();

        // Fila vacía
        for (int i = 0; i < 4; i++) panel.add(Box.createRigidArea(new Dimension(350, 50)));

        panel.add(Box.createRigidArea(new Dimension(350, 50)));
        panel.add(labelCorreo);
        panel.add(campoCorreo);
        panel.add(Box.createRigidArea(new Dimension(350, 50)));

        for (int i = 0; i < 4; i++) panel.add(Box.createRigidArea(new Dimension(350, 50)));
        panel.add(Box.createRigidArea(new Dimension(350, 50)));
        panel.add(labelPassword);
        panel.add(campoPassword);
        panel.add(Box.createRigidArea(new Dimension(350, 50)));

        add(panel);
    }

    /**
     * Agrega los botones de iniciar sesión y salir.
     */
    public void agregarBotones(){
        JPanel panel = new JPanel(new FlowLayout());
        panel.setOpaque(false);

        JButton botonIniciar = new JButton("Iniciar Sesión");
        botonIniciar.setPreferredSize(new Dimension(250, 40));
        panel.add(botonIniciar);

        JButton botonSalida = new JButton("Salir");
        botonSalida.setPreferredSize(new Dimension(250, 40));
        panel.add(botonSalida);

        botonIniciar.addActionListener(_ -> procesarInicioSesion());
        botonSalida.addActionListener(_ -> Ventana.principal());

        add(panel);
    }

    /**
     * Válida los datos ingresados y busca el estudiante entre los que están en el JSON.
     */
    private void procesarInicioSesion(){
        String correo = campoCorreo.getText();
        String password = campoPassword.getText();
        Set<Estudiante> estudiantes = EstudianteFactory.getInstancia().getObjetosNoModificable();

        for (Estudiante est : estudiantes) {
            // Compara el correo y el ID (contraseña)
            // Asegúrate de que getCorreo() y getId() existen en tu clase Estudiante
            if (est.getCorreo().equals(correo) && est.getId().equals(password)) {
                JOptionPane.showMessageDialog(this, "Inicio de sesión exitoso para: " + est.getNombre() + " " + est.getApellido(), "Éxito", JOptionPane.INFORMATION_MESSAGE);
                Ventana.solicitudEstudiante(est); // Pasamos la instancia del estudiante encontrado
                return; // Salimos del bucle una vez que lo encontramos
            }
        }

        JOptionPane.showMessageDialog(this, "Correo o contraseña incorrectos.", "Error de Inicio de Sesión", JOptionPane.ERROR_MESSAGE);
    }
}