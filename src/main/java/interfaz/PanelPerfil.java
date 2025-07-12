package interfaz;

import Logica.Enums.Asignatura;
import Logica.Enums.Horario;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PanelPerfil extends JPanel {
    JPanel panel;
    int valor = 0;
    private final String dias[] = {"LUNES","MARTES","MIERCOLES","JUEVES","VIERNES"};

    public PanelPerfil(){

        this.setBackground(Color.black);
        this.setVisible(true);
        this.setLayout(new GridLayout(0,2));
        this.titulo("Eliminar");
        this.titulo("Agregar");

        this.eliminar();
        this.cualAgregar();

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

    private void cualAgregar(){
        panel = new JPanel();
        panel.setLayout(new FlowLayout());
        panel.setOpaque(false);

        Font fuente = new Font("Arial", Font.BOLD, 20);

        JRadioButton radio1=new JRadioButton("Profesor");
        radio1.setFont(fuente);
        radio1.setForeground(Color.white);
        radio1.setOpaque(false);
        radio1.addItemListener(e -> {
            if (radio1.isSelected()) {
                Ventana.agregarProfesor();
            }
        });

        JRadioButton radio2 =new JRadioButton("Estudiante");
        radio2.setFont(fuente);
        radio2.setForeground(Color.white);
        radio2.setOpaque(false);
        radio2.addItemListener(e -> {
            if (radio2.isSelected()) {
                Ventana.agregarEstudiante();
            }
        });
        panel.add(radio1);
        panel.add(radio2);
        add(panel);
    }
}