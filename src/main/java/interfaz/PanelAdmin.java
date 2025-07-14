package interfaz;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PanelAdmin extends JPanel {

    public PanelAdmin(){
        this.setBackground(Color.black);
        this.setVisible(true);
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.titulo();
        JButton boton1 = this.addBotones("/botonCalendario.png");
        JButton boton2 = this.addBotones("/botonPerfil.png");
        JButton boton3 = this.addBotones("/botonSolicitud.png");
        this.botones();
        add(Box.createRigidArea(new Dimension(0,10)));
        this.repaint();
        this.revalidate();

        boton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Ventana.calendario();
            }
        });
        boton2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Ventana.perfil();
            }
        });
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
        boton.setIcon(Redimencionador.red(ubicacion, 23));
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

    public void botones(){
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        panel.setOpaque(false);

        JButton botonSalida = new JButton("Salir");
        botonSalida.setPreferredSize(new Dimension(200,40));
        panel.add(botonSalida);

        add(panel);
        botonSalida.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Ventana.principal();
            }
        });
    }
}
