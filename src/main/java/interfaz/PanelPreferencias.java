package interfaz;

import Logica.Estudiante;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PanelPreferencias extends JPanel {
    protected static boolean menorTarifa = false;
    protected static boolean menorCantidadEstudiantes = false;
    protected static boolean diaPreferido = false;
    protected static boolean horaPreferido = false;
    protected static boolean diaHoraPreferido = false;
    private Estudiante estudiante;

    public PanelPreferencias(Estudiante estudiante){

        this.estudiante = estudiante;

        setBackground(new Color(30, 30, 30));
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        InterfazUtils.agregarTitulo("Preferencias de Solicitud", this);
        opciones();
        botonContinuar();

    }

    private void opciones(){
     JPanel panel = new JPanel();
     panel.setOpaque(false);
     panel.setLayout(new GridLayout(5,3));
     Font fuente = new Font("Arial", Font.BOLD, 20);

     JCheckBox checkTarifa = new JCheckBox("Menor Tarifa Posible");
     checkTarifa.setOpaque(false);
     checkTarifa.setFont(fuente);
     checkTarifa.setForeground(Color.white);

     JCheckBox checkEstudiantes = new JCheckBox("Menor Cantidad de Estudiantes");
     checkEstudiantes.setOpaque(false);
     checkEstudiantes.setFont(fuente);
     checkEstudiantes.setForeground(Color.white);

     ButtonGroup bg = new ButtonGroup();
     JRadioButton radioDia = new JRadioButton("Dia Preferido");
     radioDia.setOpaque(false);
     radioDia.setFont(fuente);
     radioDia.setForeground(Color.white);

     JRadioButton radioHorario = new JRadioButton("Horario Preferido");
     radioHorario.setOpaque(false);
     radioHorario.setFont(fuente);
     radioHorario.setForeground(Color.white);

     JRadioButton radioDiaHorario = new JRadioButton("Dia + Horario Preferido");
     radioDiaHorario.setOpaque(false);
     radioDiaHorario.setFont(fuente);
     radioDiaHorario.setForeground(Color.white);

     bg.add(radioDia);
     bg.add(radioHorario);
     bg.add(radioDiaHorario);

         radioDia.addActionListener(new ActionListener() {
             @Override
             public void actionPerformed(ActionEvent e) {

                 JRadioButton boton = (JRadioButton) e.getSource();
                 if (boton.isSelected()) {
                     diaPreferido = true;
                 } else {
                        diaPreferido = false;
                 }
             }
         });
        radioHorario.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                JRadioButton boton = (JRadioButton) e.getSource();
                if (boton.isSelected()) {
                    horaPreferido = true;
                } else {
                    horaPreferido = false;
                }
            }
        });
        radioDia.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                JRadioButton boton = (JRadioButton) e.getSource();
                if (boton.isSelected()) {
                    diaHoraPreferido = true;
                } else {
                    diaHoraPreferido = false;
                }
            }
        });

        checkTarifa.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JCheckBox source = (JCheckBox) e.getSource();
                if (source.isSelected()) {
                    menorTarifa = true;
                } else {
                    menorTarifa = false;
                }
            }
        });

        checkEstudiantes.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JCheckBox source = (JCheckBox) e.getSource();
                if (source.isSelected()) {
                    menorCantidadEstudiantes = true;
                } else {
                    menorCantidadEstudiantes = false;
                }
            }
        });

        panel.add(Box.createRigidArea(new Dimension(50, 50)));
        panel.add(checkTarifa);
        panel.add(Box.createRigidArea(new Dimension(50, 50)));

        panel.add(Box.createRigidArea(new Dimension(50, 50)));
        panel.add(Box.createRigidArea(new Dimension(50, 50)));
        panel.add(Box.createRigidArea(new Dimension(50, 50)));

        panel.add(Box.createRigidArea(new Dimension(50, 50)));
        panel.add(checkEstudiantes);
        panel.add(Box.createRigidArea(new Dimension(50, 50)));

        panel.add(Box.createRigidArea(new Dimension(50, 50)));
        panel.add(Box.createRigidArea(new Dimension(50, 50)));
        panel.add(Box.createRigidArea(new Dimension(50, 50)));

        panel.add(radioDia);
        panel.add(radioHorario);
        panel.add(radioDiaHorario);

        add(panel);
    }

    private void botonContinuar(){
        JPanel panel = new JPanel();
        panel.setOpaque(false);
        panel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        add(Box.createRigidArea(new Dimension(0, 40)));


        JButton botonContinuar = new JButton("CONTINUAR");
        panel.add(botonContinuar);

        add(panel);
        panel.add(Box.createRigidArea(new Dimension(0, 20)));

        botonContinuar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Ventana.solicitudEstudiante(estudiante);
            }
        });
    }
}
