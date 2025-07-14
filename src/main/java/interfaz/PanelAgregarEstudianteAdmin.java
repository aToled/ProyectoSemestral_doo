package interfaz;

import Logica.Estudiante;
import Logica.EstudianteFactory;

import javax.swing.*;
import java.awt.*;

/**
 * Panel que permite al administrador registrar a un nuevo Estudiante.
 */
public class PanelAgregarEstudianteAdmin extends JPanel {
    private JTextField campoNombre, campoApellido, campoCorreo, campoPassword;

    /**
     * Inicializa el panel con los componentes necesarios para el registro.
     */
    public PanelAgregarEstudianteAdmin(){
        setBackground(new Color(30, 30, 30));
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        InterfazUtils.agregarTitulo("Agregar Estudiante", this);
        add(Box.createRigidArea(new Dimension(0, 40)));

        agregarTexto();
        add(Box.createRigidArea(new Dimension(0, 30)));

        agregarBotones();
        add(Box.createRigidArea(new Dimension(0, 10)));

        repaint();
        revalidate();
    }

    /**
     * Agrega los campos de entrada para los datos del Estudiante, incluyendo nombre, correo, contraseña, etc.
     */
    private void agregarTexto() {
        JPanel panel = new JPanel(new GridLayout(4, 2, 10, 15));
        panel.setOpaque(false);
        panel.setMaximumSize(new Dimension(700, 200));

        Font fuente = new Font("Arial", Font.BOLD, 20);

        campoNombre = new JTextField();
        campoApellido = new JTextField();
        campoCorreo = new JTextField();
        campoPassword = new JTextField();

        panel.add(InterfazUtils.label( "Nombre:", fuente)); panel.add(campoNombre);
        panel.add(InterfazUtils.label("Apellido:", fuente)); panel.add(campoApellido);
        panel.add(InterfazUtils.label("Correo electrónico:", fuente)); panel.add(campoCorreo);
        panel.add(InterfazUtils.label("Contraseña:", fuente)); panel.add(campoPassword);

        panel.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(panel);
    }

    /**
     * Agrega los botones para registrar al Estudiante o volver al menú principal, adémas, el botón para registrar al Estudiante
     * hace todas las validaciones necesarias para asegurarse que la información que se ingresa es correcta.
     */
    private void agregarBotones() {
        JPanel panel = new JPanel(new FlowLayout());
        panel.setOpaque(false);

        JButton botonAgregar = new JButton("Agregar");
        botonAgregar.setPreferredSize(new Dimension(250, 40));
        panel.add(botonAgregar);

        JButton botonSalir = new JButton("Salir");
        botonSalir.setPreferredSize(new Dimension(250, 40));
        panel.add(botonSalir);

        botonAgregar.addActionListener(_ -> {
            int id = EstudianteFactory.getInstancia().getCantidadObjetos() + 1;
            Estudiante  estudiante = EstudianteFactory.crearEstudiante(campoNombre.getText(), campoApellido.getText(), campoCorreo.getText(), String.valueOf(id));
            if (estudiante.setPassword(campoPassword.getText())) {
                JOptionPane.showMessageDialog(this, "Estudiante agregado correctamente.",
                        "Éxito", JOptionPane.INFORMATION_MESSAGE);
                Ventana.perfil();
            } else {
                EstudianteFactory.eliminarEstudiante(String.valueOf(id));
                JOptionPane.showMessageDialog(this, "Contraseña inválida.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
        botonSalir.addActionListener(_ -> Ventana.principal());
        add(panel);
    }
}