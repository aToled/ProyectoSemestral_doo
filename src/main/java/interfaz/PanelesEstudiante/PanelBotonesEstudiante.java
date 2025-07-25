package interfaz.PanelesEstudiante;

import interfaz.Utils.InterfazUtils;
import interfaz.Utils.JPanelConBotones;
import interfaz.Ventana;

import javax.swing.*;
import java.awt.*;

/**
 * Panel que le da la opción al estudiante de seleccionar preferencias, clases y enviar solicitud
 */
public class PanelBotonesEstudiante extends JPanelConBotones {

    public PanelBotonesEstudiante() {
        super();
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

        botonPreferencias.addActionListener(_ -> Ventana.irA(new PanelPreferencias(Ventana.getEstudianteActual())));
        botonSolicitudClases.addActionListener(_ -> Ventana.irA(new PanelEnviarSolicitudEstudiante(Ventana.getEstudianteActual())));
    }
}