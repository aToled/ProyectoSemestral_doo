package interfaz;

import javax.swing.*;
import java.awt.*;

public class PanelAdmin extends JPanel {

    public PanelAdmin(){
        this.setBackground(Color.black);
        this.setVisible(true);
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.titulo();
        JButton boton1 = this.addBotones("/botonCalendario.png");
        JButton boton2 = this.addBotones("/botonPerfil.png");
        JButton boton3 = this.addBotones("/botonSolicitud.png");
        this.repaint();
        this.revalidate();
    }

    private void titulo(){
        Font fuente = new Font("Arial", Font.BOLD, 70);
        JLabel title = new JLabel("Administrador");
        title.setForeground(Color.white);
        title.setFont(fuente);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.add(title);
        title.setVisible(true);
    }

    private JButton addBotones(String ubicacion){
        JButton boton = new JButton();
        boton.setIcon(Redimencionador.red(ubicacion, 25));
        boton.setAlignmentX(Component.CENTER_ALIGNMENT);
        boton.setContentAreaFilled(false);
        boton.setBorderPainted(false);
        boton.setFocusPainted(false);
        boton.setOpaque(false);
        this.add(boton);
        boton.setVisible(true);
        this.revalidate();
        this.repaint();
        return boton;
    }
}
