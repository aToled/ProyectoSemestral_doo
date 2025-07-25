package interfaz;

import Logica.Estudiante;
import Logica.EstudianteFactory;


import javax.swing.*;
import java.awt.*;
import java.util.UUID;

/**
 * Panel de registro para nuevos estudiantes. Permite ingresar nombre, apellido, correo y contraseña,
 * luego crea una cuenta de Estudiante en el sistema.
 */
public class PanelRegistroEstudiante extends JPanelConBotones {

    private JTextField campoNombre, campoApellido, campoCorreo, campoPassword;

    /**
     * Configura la vista principal del panel, su layout y componentes.
     */
    public PanelRegistroEstudiante(){
        super();
        setBackground(new Color(33, 33, 33));
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        agregarTitulo();
        add(Box.createRigidArea(new Dimension(0,40)));

        texto();
        add(Box.createRigidArea(new Dimension(0,30)));

        botones();
        add(Box.createRigidArea(new Dimension(0,10)));

        repaint();
        revalidate();
    }

    private void agregarTitulo(){
        JLabel title = new JLabel("Registro Estudiante");
        title.setForeground(Color.white);
        title.setFont(new Font("Arial", Font.BOLD, 80));
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(title);
    }

    /**
     * Agrega los campos de texto y etiquetas para ingresar los datos del Estudiante
     */
    private void texto(){
        JPanel panel = new JPanel(new GridLayout(4, 2, 10, 10));
        panel.setOpaque(false);
        panel.setAlignmentX(Component.CENTER_ALIGNMENT);

        campoNombre = new JTextField();
        campoApellido = new JTextField();
        campoCorreo = new JTextField();
        campoPassword = new JPasswordField();

        agregarCampo(panel, "Nombre:", campoNombre);
        agregarCampo(panel, "Apellido:", campoApellido);
        agregarCampo(panel, "Correo electrónico:", campoCorreo);
        agregarCampo(panel, "Contraseña:", campoPassword);

        add(panel);
    }

    private void agregarCampo(JPanel panel, String texto, JTextField campo) {
        JLabel label = new JLabel(texto);
        label.setFont(new Font("Arial", Font.BOLD, 20));
        label.setForeground(Color.GRAY);
        panel.add(label);
        panel.add(campo);
    }

    /**
     * Agrega los botones de acción para registrarse o salir,
     * el botón de registro crea un nuevo estudiante y navega a la pantalla correspondiente
     * y el botón de salida regresa al menú principal.
     */
    private void botones(){
        JPanel panel = new JPanel(new FlowLayout());
        panel.setOpaque(false);

        JButton botonRegistrar = new JButton("Registrarme");
        botonRegistrar.setPreferredSize(new Dimension(250,40));
        panel.add(botonRegistrar);

        botonRegistrar.addActionListener(_ -> registrarEstudiante());
        add(panel);
    }

    private void registrarEstudiante() {
        String id = "e" + UUID.randomUUID();
        Estudiante estudiante = EstudianteFactory.crearEstudiante(campoNombre.getText(), campoApellido.getText(), campoCorreo.getText(), id);
        if (estudiante.setPassword(campoPassword.getText())) {
            Ventana.irA(new PanelInicioSesionEstudiante());
            EstudianteFactory.agregarEstudiante(estudiante);
        }
    }
}