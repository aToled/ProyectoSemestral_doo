package interfaz;

import Logica.Estudiante;
import javax.swing.*;
import java.awt.*;

/**
 * Panel que le da la opción al estudiante de seleccionar preferencias, clases y enviar solicitud
 */
public class AgregarSolicitudEstudiante extends JPanel {
    public AgregarSolicitudEstudiante(Estudiante estudiante) {
        setBackground(new Color(30, 30, 30));
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        InterfazUtils.agregarTitulo("Estudiante", this);
        JButton botonPreferencias = InterfazUtils.addBotonesConIcono("/botonPreferencias.png", this, 30);
        JButton botonSolicitudClases = InterfazUtils.addBotonesConIcono("/botonSolicitudClases.png", this, 30);

        add(Box.createRigidArea(new Dimension(0, 50)));
        add(botonPreferencias);
        add(Box.createRigidArea(new Dimension(0, 40)));
        add(botonSolicitudClases);
        add(Box.createRigidArea(new Dimension(0, 60)));

        botonPreferencias.addActionListener(_ -> Ventana.preferencias(estudiante));
        botonSolicitudClases.addActionListener(_ -> Ventana.seleccionarClase(estudiante));
    }
}