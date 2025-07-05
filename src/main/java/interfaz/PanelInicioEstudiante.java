package interfaz;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PanelInicioEstudiante extends JPanel {
    public PanelInicioEstudiante(){
        this.setBackground(Color.black);
        this.setVisible(true);
        this.setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
        this.titulo();
        add(Box.createRigidArea(new Dimension(0,100)));


        this.texto();
        add(Box.createRigidArea(new Dimension(0,160)));

        JButton boton1 = new JButton("IniciarSecion");
        add(boton1);

        JButton botonSalida = new JButton("Salir");
        add(botonSalida);

        add(Box.createRigidArea(new Dimension(0,10)));


        this.repaint();
        this.revalidate();

        boton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Ventana.admin();
            }
        });

        botonSalida.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Ventana.principal();
            }
        });
    }
    private void titulo(){
        Font fuente = new Font("Arial", Font.BOLD, 80);
        JLabel title = new JLabel("Registro Estudiante");
        title.setForeground(Color.white);
        title.setFont(fuente);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.add(title);
        title.setVisible(true);
    }


    private void texto(){
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(5,4));
        Font fuente = new Font("Arial", Font.BOLD, 25);
        JLabel correo = new JLabel("Correo:");
        correo.setFont(fuente);
        JLabel id = new JLabel("Id:");
        id.setFont(fuente);
        JTextField campo1 = new JTextField("Ingrese su Correo");
        JTextField campo2 = new JTextField("Ingresar  Identificador");

        correo.setForeground(Color.GRAY);
        id.setForeground(Color.GRAY);

        panel.add(Box.createRigidArea(new Dimension(350,50)));
        panel.add(Box.createRigidArea(new Dimension(350,50)));
        panel.add(Box.createRigidArea(new Dimension(350,50)));
        panel.add(Box.createRigidArea(new Dimension(350,50)));
        panel.add(Box.createRigidArea(new Dimension(350,50)));
        panel.add(correo);
        panel.add(campo1);
        panel.add(Box.createRigidArea(new Dimension(350,50)));
        panel.add(Box.createRigidArea(new Dimension(350,50)));
        panel.add(Box.createRigidArea(new Dimension(350,50)));
        panel.add(Box.createRigidArea(new Dimension(350,50)));
        panel.add(Box.createRigidArea(new Dimension(350,50)));
        panel.add(Box.createRigidArea(new Dimension(350,50)));
        panel.add(id);
        panel.add(campo2);
        panel.add(Box.createRigidArea(new Dimension(350,50)));
        panel.add(Box.createRigidArea(new Dimension(350,50)));
        panel.add(Box.createRigidArea(new Dimension(350,50)));
        panel.add(Box.createRigidArea(new Dimension(350,50)));
        panel.add(Box.createRigidArea(new Dimension(350,50)));



        panel.setOpaque(false);

        panel.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.add(panel);
    }
}
