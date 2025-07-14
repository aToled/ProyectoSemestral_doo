package interfaz;

import javax.swing.*;
import java.awt.*;

/**
 * Panel que representa la vista principal del administrador
 * con botones para acceder a distintas funcionalidades del sistema.
 */
public class PanelAdmin extends JPanel {

    public PanelAdmin(){
        setBackground(new Color(30, 30, 30));
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        InterfazUtils.agregarTitulo("Administrador", this);

        JButton botonCalendario = InterfazUtils.addBotonesConIcono("/botonCalendario.png", this, 23);
        JButton botonPerfil = InterfazUtils.addBotonesConIcono("/botonPerfil.png", this, 23);
        JButton botonSolicitudes = InterfazUtils.addBotonesConIcono("/botonSolicitud.png", this, 23);

        AgregarBotonSalir();
        add(Box.createRigidArea(new Dimension(0,10)));

        botonCalendario.addActionListener(_ -> Ventana.calendario());
        botonPerfil.addActionListener(_ -> Ventana.perfil());
        botonSolicitudes.addActionListener(_ -> Ventana.solicitudes());

        repaint();
        revalidate();
    }

    public void AgregarBotonSalir(){
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        panel.setOpaque(false);

        JButton botonSalida = new JButton("Salir");
        botonSalida.setPreferredSize(new Dimension(200,40));
        panel.add(botonSalida);

        add(panel);
        botonSalida.addActionListener(_ -> Ventana.principal());
    }
}
