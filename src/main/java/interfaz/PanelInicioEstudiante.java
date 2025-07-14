package interfaz;

import Logica.Estudiante;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PanelInicioEstudiante extends JPanel {

    JTextField campo1;
    JTextField campo2;

    public PanelInicioEstudiante(){
        this.setBackground(Color.black);
        this.setVisible(true);
        this.setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
        this.titulo();
        add(Box.createRigidArea(new Dimension(0,100)));


        this.texto();
        add(Box.createRigidArea(new Dimension(0,160)));

        this.botones();

        add(Box.createRigidArea(new Dimension(0,10)));


        this.repaint();
        this.revalidate();
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
        JLabel id = new JLabel("Contraseña:");
        id.setFont(fuente);
        campo1 = new JTextField("Ingrese su Correo");
        campo2 = new JPasswordField("");

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




        panel.setOpaque(false);

        panel.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.add(panel);
    }

    public void botones(){
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());
        panel.setOpaque(false);

        JButton boton1 = new JButton("Iniciar Sesion");
        boton1.setPreferredSize(new Dimension(250,40));
        panel.add(boton1);

        JButton botonSalida = new JButton("Salir");
        botonSalida.setPreferredSize(new Dimension(250,40));
        panel.add(botonSalida);

        add(panel);

        boton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String correo = campo1.getText();
                String contraseña = campo2.getText();
                Estudiante estudiante = null;

                //inicio de sesion

                Ventana.solicitudEstudiante(estudiante);
            }
        });
        botonSalida.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Ventana.principal();
            }
        });
    }
}
