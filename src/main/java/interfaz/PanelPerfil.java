package interfaz;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PanelPerfil extends JPanel {
    public PanelPerfil(){

        this.setBackground(Color.black);
        this.setVisible(true);
        this.setLayout(new GridLayout(0,2));
        this.titulo("Eliminar");
        this.titulo("Agregar");

        this.eliminar();
        this.texto();

        this.repaint();
        this.revalidate();

    }

    private void titulo(String texto){
        Font fuente = new Font("Arial", Font.BOLD, 60);
        JLabel title = new JLabel(texto);
        title.setForeground(Color.white);
        title.setFont(fuente);
        this.add(title);
        title.setVisible(true);
    }

    private void eliminar(){
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panel.setOpaque(false);
        JComboBox combo = new JComboBox<String>();
        combo.setPreferredSize(new Dimension(250,30));
        panel.add(combo);
        JButton boton = new JButton("Eliminar");
        panel.add(boton);
        add(panel);

        boton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                //eliminar lo que esta en el jcombobox
            }
        });
    }

    private void texto(){
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(7,3));
        JLabel nombre = new JLabel("Nombre:");
        Font fuente = new Font("Arial", Font.BOLD, 15);
        nombre.setFont(fuente);
        JLabel apellido = new JLabel("Apellido:");
        apellido.setFont(fuente);
        JLabel correo = new JLabel("Correo electronico:");
        correo.setFont(fuente);
        JLabel id = new JLabel("Id(Es el identificador):");
        id.setFont(fuente);
        JTextField campo1 = new JTextField("Ingrese su Nombre");
        JTextField campo2 = new JTextField("Ingrese su Apellido");
        JTextField campo3 = new JTextField("ejemplo@gmail.com");
        JTextField campo4 = new JTextField("Numero de id");


        nombre.setForeground(Color.GRAY);
        apellido.setForeground(Color.GRAY);
        correo.setForeground(Color.GRAY);
        id.setForeground(Color.GRAY);


        panel.add(nombre);
        panel.add(campo1);
        panel.add(Box.createRigidArea(new Dimension(300,50)));
        panel.add(Box.createRigidArea(new Dimension(300,50)));
        panel.add(Box.createRigidArea(new Dimension(300,50)));
        panel.add(Box.createRigidArea(new Dimension(300,50)));
        panel.add(apellido);
        panel.add(campo2);
        panel.add(Box.createRigidArea(new Dimension(300,50)));
        panel.add(Box.createRigidArea(new Dimension(300,50)));
        panel.add(Box.createRigidArea(new Dimension(300,50)));
        panel.add(Box.createRigidArea(new Dimension(300,50)));
        panel.add(correo);
        panel.add(campo3);
        panel.add(Box.createRigidArea(new Dimension(300,50)));
        panel.add(Box.createRigidArea(new Dimension(300,50)));
        panel.add(Box.createRigidArea(new Dimension(300,50)));
        panel.add(Box.createRigidArea(new Dimension(300,50)));
        panel.add(id);
        panel.add(campo4);


        panel.setOpaque(false);

        panel.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.add(panel);
    }
}