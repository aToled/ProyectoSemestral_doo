package interfaz;

import javax.swing.*;
import java.awt.*;

/**
 * Panel que representa la vista principal del administrador
 * con botones para acceder a distintas funcionalidades del sistema.
 */
public class PanelBotonesAdmin extends JPanelConBotones {

    public PanelBotonesAdmin(){
        super();
        setBackground(new Color(30, 30, 30));
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        InterfazUtils.agregarTitulo("Administrador", this);

        JButton botonCalendario = InterfazUtils.addBotonesConIcono("/botonCalendario.png", this, 17);
        JButton botonPerfil = InterfazUtils.addBotonesConIcono("/botonPerfil.png", this, 17);
        JButton botonSolicitudes = InterfazUtils.addBotonesConIcono("/botonGestionarSolicitudes.png", this, 17);
        JButton botonCrearClase = InterfazUtils.addBotonesConIcono("/botonCrearClase.png", this, 17);

        add(Box.createRigidArea(new Dimension(0,10)));

        botonCalendario.addActionListener(_ -> Ventana.irA(new PanelCalendario()));
        botonPerfil.addActionListener(_ -> Ventana.irA(new PanelCrearEliminarPerfil()));
        botonSolicitudes.addActionListener(_ -> Ventana.irA(new PanelSolicitudesAdmin()));
        botonCrearClase.addActionListener(_ -> Ventana.irA(new PanelCrearClases()));

        repaint();
        revalidate();
    }
}