package interfaz;

import javax.swing.*;
import java.awt.*;

/**
 * Panel base con botones de navegación comunes: Volver Atrás y Cerrar Sesión
 */
public abstract class JPanelConBotones extends JPanel {

    public JPanelConBotones() {
        setLayout(new BorderLayout());
        JPanel barraNavegacion = new JPanel(new FlowLayout(FlowLayout.CENTER));
        barraNavegacion.setBackground(new Color(33, 33, 33));
        barraNavegacion.setMaximumSize(new Dimension(1280, 50));

        JButton btnVolver = new JButton("Volver Atrás");
        JButton btnCerrar = new JButton("Cerrar Sesión");

        btnVolver.setPreferredSize(new Dimension(130, 30));
        btnCerrar.setPreferredSize(new Dimension(130, 30));
        btnVolver.addActionListener(_ -> Ventana.volverAtras());
        btnCerrar.addActionListener(_ -> Ventana.cerrarSesion());

        barraNavegacion.add(btnVolver);
        barraNavegacion.add(btnCerrar);
        add(barraNavegacion, BorderLayout.NORTH);
    }
}
