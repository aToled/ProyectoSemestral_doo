package interfaz;

import Logica.Enums.Asignatura;
import Logica.Enums.Dia;
import Logica.Enums.EstadoSolicitud;
import Logica.Enums.Horario;
import Logica.Estudiante;
import Logica.Solicitud;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AgregarSolicitudEstudiante extends JPanel {
    private final String dias[] = {"LUNES","MARTES","MIERCOLES","JUEVES","VIERNES"};
    private Estudiante estudiante;
    private JComboBox<Asignatura> comboAsignatura;
    private JComboBox<String> comboHorario;
    private JComboBox<String> comboDia;
    private JCheckBox menorEstudiantes;
    private JCheckBox menorTarifa;

    public AgregarSolicitudEstudiante(Estudiante estudiante){
        this.estudiante = estudiante;
        this.setBackground(new Color(30, 30, 30));
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
                Asignatura asignaturaSeleccionada = (Asignatura) comboAsignatura.getSelectedItem();
                Horario horarioSeleccionado = Horario.valueOf((String) comboHorario.getSelectedItem());
                Dia diaSeleccionado = Dia.valueOf((String) comboDia.getSelectedItem());

                boolean prefiereMenorEstudiantes = menorEstudiantes.isSelected();
                boolean prefiereMenorTarifa = menorTarifa.isSelected();

                estudiante.addDiasInteres(diaSeleccionado);
                estudiante.addHorariosInteres(horarioSeleccionado);
                estudiante.setPreferirMenorTarifa(prefiereMenorTarifa);
                estudiante.setPreferirClaseConMenosEstudiantes(prefiereMenorEstudiantes);

                Solicitud nuevaSolicitud = estudiante.crearSolicitud(asignaturaSeleccionada);

                JOptionPane.showMessageDialog(AgregarSolicitudEstudiante.this, "Solicitud enviada con exito", "Solicitud Enviada", JOptionPane.INFORMATION_MESSAGE);

                Ventana.solicitudEstudiante(estudiante);
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

        JComboBox comboAsignatura = new JComboBox<>();
        for(Asignatura asignatura1: Asignatura.values()){
            comboAsignatura.addItem(asignatura1);
        }

        JComboBox comboHorario = new JComboBox<>();
        for(Horario horario1: Horario.values()){
            comboHorario.addItem(horario1.toString());
        }


        JComboBox comboDia = new JComboBox<>();
        for(String dia1: dias){
            comboDia.addItem(dia1);
        }

        menorEstudiantes = new JCheckBox("Clase con Menor Cantidad De Estuantes");
        menorEstudiantes.setFont(fuente2);
        menorEstudiantes.setOpaque(false);

        menorTarifa = new JCheckBox("Menor Tarifa Posible");
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