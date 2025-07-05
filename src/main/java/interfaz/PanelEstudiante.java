package interfaz;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PanelEstudiante extends JPanel {
    public PanelEstudiante(){
        this.setBackground(Color.black);
        this.setVisible(true);
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.titulo();
        add(Box.createRigidArea(new Dimension(0,40)));
        JButton boton1 = this.addBotones("/botonInicio.png");
        add(Box.createRigidArea(new Dimension(0,20)));
        JButton boton2 = this.addBotones("/botonRegistro.png");
        this.repaint();
        this.revalidate();

        boton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Ventana.estudianteInicio();
            }
        });

        boton2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Ventana.estudianteRegistro();
            }
        });
    }

    private void titulo(){
        Font fuente = new Font("Arial", Font.BOLD, 100);
        JLabel title = new JLabel("Estudiante");
        title.setForeground(Color.white);
        title.setFont(fuente);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.add(title);
        title.setVisible(true);
    }

    private JButton addBotones(String ubicacion){
        JButton boton = new JButton();
        boton.setIcon(Redimencionador.red(ubicacion, 30));
        boton.setContentAreaFilled(false);
        boton.setBorderPainted(false);
        boton.setFocusPainted(false);
        boton.setOpaque(false);
        boton.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.add(boton);
        boton.setVisible(true);
        this.revalidate();
        this.repaint();
        return boton;
    }
}
