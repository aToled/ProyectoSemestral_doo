package interfaz;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PanelSolicitudesEstudiante extends JPanel {
    private int valor = 0;
    private JRadioButton radio1, radio2;

    public PanelSolicitudesEstudiante(){
        this.setBackground(Color.black);
        this.setVisible(true);
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.titulo();

        add(Box.createRigidArea(new Dimension(0,150)));

        radio();

        JButton boton1 = new JButton("Siguiente");
        boton1.setAlignmentX(Component.CENTER_ALIGNMENT);
        Font fuente = new Font("Arial", Font.BOLD, 20);
        boton1.setFont(fuente);
        boton1.setPreferredSize(new Dimension(150,40));
        add(boton1);

        add(Box.createRigidArea(new Dimension(0,100)));

        this.repaint();
        this.revalidate();

        boton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Ventana.admin();
            }
        });
    }
    private void titulo(){
        Font fuente = new Font("Arial", Font.BOLD, 90);
        JLabel title = new JLabel("Gestiona tus Solicitudes");
        title.setForeground(Color.white);
        title.setFont(fuente);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.add(title);
        title.setVisible(true);
    }
    private void radio(){

        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());
        panel.setOpaque(false);

        ButtonGroup bg = new ButtonGroup();

        Font fuente = new Font("Arial", Font.BOLD, 30);

        radio1=new JRadioButton("Modificar Solicitud");
        radio1.setFont(fuente);
        radio1.setForeground(Color.white);
        radio1.addItemListener(e -> {
            if (radio1.isSelected()) {
                valor = 1;
            }
        });

        radio1.setOpaque(false);
        panel.add(radio1);
        bg.add(radio1);

        panel.add(Box.createRigidArea(new Dimension(120,0)));

        radio2=new JRadioButton("Agregar Solicitud");
        radio2.setFont(fuente);
        radio2.setForeground(Color.white);
        radio2.addItemListener(e -> {
            if (radio2.isSelected()) {
                valor = 2;
            }
        });

        radio2.setOpaque(false);
        panel.add(radio2);
        bg.add(radio2);
        add(panel);
    }
}
