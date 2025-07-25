package interfaz.ManejoSesion;

import interfaz.Utils.InterfazUtils;
import interfaz.Utils.JPanelConBotones;
import interfaz.Ventana;

import javax.swing.*;
import java.awt.*;

/**
 * Panel que representa la vista principal del Estudiante
 * con botones para acceder iniciar sesión o registrarse.
 */
public class PanelInicioORegistroEstudiante extends JPanelConBotones {
    public PanelInicioORegistroEstudiante(){
        super();
        setBackground(new Color(33, 33, 33));
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        InterfazUtils.agregarTitulo("Estudiante", this);

        add(Box.createRigidArea(new Dimension(0,40)));
        JButton botonInicio = InterfazUtils.addBotonesConIcono("/botonInicio.png", this, 30);
        add(Box.createRigidArea(new Dimension(0,20)));
        JButton botonRegistro = InterfazUtils.addBotonesConIcono("/botonRegistro.png", this, 30);

        botonInicio.addActionListener(_ -> Ventana.irA(new PanelInicioSesionEstudiante()));
        botonRegistro.addActionListener(_ -> Ventana.irA(new PanelRegistroEstudiante()));

        repaint();
        revalidate();
    }
}