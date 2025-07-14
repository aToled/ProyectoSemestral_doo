package interfaz;

import javax.swing.*;
import java.awt.*;

/**
 * Panel que representa la vista principal al lanzar el programa
 * con botones para acceder al inicio de sesión como Estudiante o Administrador.
 */
public class PanelEntrada extends JPanel {
    public PanelEntrada(){
        setBackground(new Color(30, 30, 30));
        setLayout(new FlowLayout());
        InterfazUtils.agregarTitulo("Sistema de clases Particulares", this);

        JButton botonAdmin = InterfazUtils.addBotonesConIcono("/botonAdmin.png", this, 40);
        JButton botonEstudiante = InterfazUtils.addBotonesConIcono("/botonEstudiante.png", this, 40);

        botonAdmin.addActionListener(_ -> Ventana.adminInicio());
        botonEstudiante.addActionListener(_ -> Ventana.estudiante());

        repaint();
        revalidate();
    }
}