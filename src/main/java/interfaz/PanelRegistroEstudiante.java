package interfaz;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PanelRegistroEstudiante extends JPanel {
    public PanelRegistroEstudiante(){
        this.setBackground(Color.black);
        this.setVisible(true);
        this.setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
        this.titulo();
        add(Box.createRigidArea(new Dimension(0,40)));


        this.texto();
        add(Box.createRigidArea(new Dimension(0,30)));

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
        panel.setLayout(new GridLayout(9,4));
        JLabel nombre = new JLabel("Nombre:");
        Font fuente = new Font("Arial", Font.BOLD, 20);
        nombre.setFont(fuente);
        JLabel apellido = new JLabel("Apellido:");
        apellido.setFont(fuente);
        JLabel correo = new JLabel("Correo electronico:");
        correo.setFont(fuente);
        JLabel id = new JLabel("Id(Guardar como identificador):");
        id.setFont(fuente);
        JTextField campo1 = new JTextField("Ingrese su Nombre");
        JTextField campo2 = new JTextField("Ingrese su Apellido");
        JTextField campo3 = new JTextField("ejemplo@gmail.com");
        JTextField campo4 = new JTextField("Numero de id");


        nombre.setForeground(Color.GRAY);
        apellido.setForeground(Color.GRAY);
        correo.setForeground(Color.GRAY);
        id.setForeground(Color.GRAY);

        panel.add(Box.createRigidArea(new Dimension(350,50)));
        panel.add(Box.createRigidArea(new Dimension(350,50)));
        panel.add(Box.createRigidArea(new Dimension(350,50)));
        panel.add(Box.createRigidArea(new Dimension(350,50)));
        panel.add(Box.createRigidArea(new Dimension(350,50)));
        panel.add(nombre);
        panel.add(campo1);
        panel.add(Box.createRigidArea(new Dimension(350,50)));
        panel.add(Box.createRigidArea(new Dimension(350,50)));
        panel.add(Box.createRigidArea(new Dimension(350,50)));
        panel.add(Box.createRigidArea(new Dimension(350,50)));
        panel.add(Box.createRigidArea(new Dimension(350,50)));
        panel.add(Box.createRigidArea(new Dimension(350,50)));
        panel.add(apellido);
        panel.add(campo2);
        panel.add(Box.createRigidArea(new Dimension(350,50)));
        panel.add(Box.createRigidArea(new Dimension(350,50)));
        panel.add(Box.createRigidArea(new Dimension(350,50)));
        panel.add(Box.createRigidArea(new Dimension(350,50)));
        panel.add(Box.createRigidArea(new Dimension(350,50)));
        panel.add(Box.createRigidArea(new Dimension(350,50)));
        panel.add(correo);
        panel.add(campo3);
        panel.add(Box.createRigidArea(new Dimension(350,50)));
        panel.add(Box.createRigidArea(new Dimension(350,50)));
        panel.add(Box.createRigidArea(new Dimension(350,50)));
        panel.add(Box.createRigidArea(new Dimension(350,50)));
        panel.add(Box.createRigidArea(new Dimension(350,50)));
        panel.add(Box.createRigidArea(new Dimension(350,50)));
        panel.add(id);
        panel.add(campo4);


        panel.setOpaque(false);

        panel.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.add(panel);
    }
    public void botones(){
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());
        panel.setOpaque(false);

        JButton boton1 = new JButton("Registrarme");
        boton1.setPreferredSize(new Dimension(250,40));
        panel.add(boton1);

        JButton botonSalida = new JButton("Salir");
        botonSalida.setPreferredSize(new Dimension(250,40));
        panel.add(botonSalida);

        add(panel);

        boton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Ventana.solicitudEstudiante();
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
