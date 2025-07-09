package interfaz;

import Logica.Enums.Asignatura;
import Logica.Enums.Dia;
import Logica.Enums.Horario;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AgregarSolicitudEstudiante extends JPanel {
    private final String dias[] = {"LUNES","MARTES","MIERCOLES","JUEVES","VIERNES"};
    public AgregarSolicitudEstudiante(){
        this.setBackground(Color.black);
        this.setVisible(true);
        this.setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
        this.titulo();
        add(Box.createRigidArea(new Dimension(0,40)));


        this.texto();
        add(Box.createRigidArea(new Dimension(0,30)));

        JButton boton1 = new JButton("Enviar Solicitud");
        boton1.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(boton1);
        add(Box.createRigidArea(new Dimension(0,10)));


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
        Font fuente = new Font("Arial", Font.BOLD, 80);
        JLabel title = new JLabel("Agregar Solicitud");
        title.setForeground(Color.white);
        title.setFont(fuente);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.add(title);
        title.setVisible(true);
    }


    private void texto(){
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(9,4));
        JLabel asignatura = new JLabel("Asignatura:");
        Font fuente = new Font("Arial", Font.BOLD, 20);
        Font fuente2 = new Font("Arial", Font.BOLD, 14);
        asignatura.setFont(fuente);

        JLabel horario = new JLabel("Horario:");
        horario.setFont(fuente);

        JLabel dia = new JLabel("Dia:");
        dia.setFont(fuente);

        JLabel preferencias = new JLabel("Preferencias:");
        preferencias.setFont(fuente);

        JComboBox comboAsignatura = new JComboBox<Asignatura>();
        for(Asignatura asignatura1: Asignatura.values()){
            comboAsignatura.addItem(asignatura1);
        }

        JComboBox comboHorario = new JComboBox<String>();
        for(Horario horario1: Horario.values()){
            comboHorario.addItem(horario1.toString());
        }


        JComboBox comboDia = new JComboBox<String>();
        for(String dia1: dias){
            comboDia.addItem(dia1);
        }

        JCheckBox menorEstudiantes = new JCheckBox("Clase con Menor Cantidad De Estuantes");
        menorEstudiantes.setFont(fuente2);
        menorEstudiantes.setOpaque(false);

        JCheckBox menorTarifa = new JCheckBox("Menor Tarifa Posible");
        menorTarifa.setFont(fuente2);
        menorTarifa.setOpaque(false);


        asignatura.setForeground(Color.GRAY);
        horario.setForeground(Color.GRAY);
        dia.setForeground(Color.GRAY);
        preferencias.setForeground(Color.GRAY);
        menorEstudiantes.setForeground(Color.GRAY);
        menorTarifa.setForeground(Color.GRAY);

        panel.add(Box.createRigidArea(new Dimension(350,50)));
        panel.add(Box.createRigidArea(new Dimension(350,50)));
        panel.add(Box.createRigidArea(new Dimension(350,50)));
        panel.add(Box.createRigidArea(new Dimension(350,50)));
        panel.add(Box.createRigidArea(new Dimension(350,50)));
        panel.add(asignatura);
        panel.add(comboAsignatura);
        panel.add(Box.createRigidArea(new Dimension(350,50)));
        panel.add(Box.createRigidArea(new Dimension(350,50)));
        panel.add(Box.createRigidArea(new Dimension(350,50)));
        panel.add(Box.createRigidArea(new Dimension(350,50)));
        panel.add(Box.createRigidArea(new Dimension(350,50)));
        panel.add(Box.createRigidArea(new Dimension(350,50)));
        panel.add(horario);
        panel.add(comboHorario);
        panel.add(Box.createRigidArea(new Dimension(350,50)));
        panel.add(Box.createRigidArea(new Dimension(350,50)));
        panel.add(Box.createRigidArea(new Dimension(350,50)));
        panel.add(Box.createRigidArea(new Dimension(350,50)));
        panel.add(Box.createRigidArea(new Dimension(350,50)));
        panel.add(Box.createRigidArea(new Dimension(350,50)));
        panel.add(dia);
        panel.add(comboDia);
        panel.add(Box.createRigidArea(new Dimension(350,50)));
        panel.add(Box.createRigidArea(new Dimension(350,50)));
        panel.add(Box.createRigidArea(new Dimension(350,50)));
        panel.add(Box.createRigidArea(new Dimension(350,50)));
        panel.add(Box.createRigidArea(new Dimension(350,50)));

        panel.add(Box.createRigidArea(new Dimension(350,50)));
        panel.add(preferencias);
        panel.add(menorEstudiantes);
        panel.add(menorTarifa);

        panel.setOpaque(false);

        panel.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.add(panel);
    }
}

/**
 *
 * para Inicio y Registro
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


 para ventana

 protected static void solicitudEstudiante(){
 instancia.remove(panel);
 panel = new AgregarSolicitudEstudiante();
 instancia.add(panel);
 instancia.repaint();
 instancia.revalidate();
 }
 */
