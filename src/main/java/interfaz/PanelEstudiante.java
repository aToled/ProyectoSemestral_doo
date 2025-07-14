package interfaz;

import javax.swing.*;
import java.awt.*;

/**
 * Panel que representa la vista principal del Estudiante
 * con botones para acceder iniciar sesión o registrarse.
 */
public class PanelEstudiante extends JPanel {
    public PanelEstudiante(){
        setBackground(new Color(30, 30, 30));
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        InterfazUtils.agregarTitulo("Estudiante", this);

        add(Box.createRigidArea(new Dimension(0,40)));
        JButton botonInicio = InterfazUtils.addBotonesConIcono("/botonInicio.png", this, 30);
        add(Box.createRigidArea(new Dimension(0,20)));
        JButton botonRegistro = InterfazUtils.addBotonesConIcono("/botonRegistro.png", this, 30);

        botonInicio.addActionListener(_ -> Ventana.estudianteInicio());
        botonRegistro.addActionListener(_ -> Ventana.estudianteRegistro());

        repaint();
        revalidate();
    }
}