package interfaz;

import Logica.Calendario;
import Logica.Clase;
import Logica.Enums.Dia;
import Logica.Enums.Horario;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PanelCalendario extends JPanel {

    private String[] dias = {"Lunes","Martes","Miercoles","Jueves","Viernes"};
    private ArrayList lunes = new ArrayList<String>();
    private ArrayList martes = new ArrayList<String>();
    private ArrayList miercoles = new ArrayList<String>();
    private ArrayList jueves = new ArrayList<String>();
    private ArrayList viernes = new ArrayList<String>();
    private JButton[] botones=new JButton[5];

    private int acumulador = 0;

    public PanelCalendario(){
        setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
        setBackground(Color.black);
        titulo();
        add(Box.createRigidArea(new Dimension(0,70)));
        listaDeBotones();
        cuerpo();
        add(Box.createRigidArea(new Dimension(0,40)));
    }

    public void listaDeBotones(){
        acumulador = 0;
        for(Horario horario: Horario.values()){
            List<Clase> clasesDelBloque = Calendario.getInstancia().getClasesEnBloque(Dia.LUNES, horario);
            if (clasesDelBloque == null) {
                clasesDelBloque = Collections.emptyList();
            }
            for(Clase clase : clasesDelBloque){
                if(clase != null){
                    lunes.add(clase.getProfesor().getNombre()+" "+clase.getProfesor().getApellido()+"  "+clase.getAsignatura().toString()+ "   Cantidad Maxima de Estudiandes: " +clase.getCapacidadMaximaAlumnos()+"  Valor: $"+clase.getTarifa());
                    acumulador+=10;
                }
            }
        }
        Color color = new Color(255,255-acumulador, 255-acumulador);
        JButton botonLunes = new JButton("");
        botonLunes.setForeground(color);

        botones[0] = botonLunes;



        acumulador = 0;
        for(Horario horario: Horario.values()){
            List<Clase> clasesDelBloque = Calendario.getInstancia().getClasesEnBloque(Dia.MARTES, horario);
            if (clasesDelBloque == null) {
                clasesDelBloque = Collections.emptyList();
            }
            for(Clase clase : clasesDelBloque){
                if(clase != null){
                    martes.add(clase.getProfesor().getNombre()+" "+clase.getProfesor().getApellido()+"  "+clase.getAsignatura().toString()+ "   Cantidad Maxima de Estudiandes: " +clase.getCapacidadMaximaAlumnos()+"  Valor: $"+clase.getTarifa());
                    acumulador+=10;
                }
            }
        }
        color = new Color(255,255-acumulador, 255-acumulador);
        JButton botonMartes = new JButton("");
        botonMartes.setForeground(color);

        botones[1] = botonMartes;



        acumulador = 0;
        for(Horario horario: Horario.values()){
            List<Clase> clasesDelBloque = Calendario.getInstancia().getClasesEnBloque(Dia.MIERCOLES, horario);
            if (clasesDelBloque == null) {
                clasesDelBloque = Collections.emptyList();
            }
            for(Clase clase : clasesDelBloque){
                if(clase != null){
                    miercoles.add(clase.getProfesor().getNombre()+" "+clase.getProfesor().getApellido()+"  "+clase.getAsignatura().toString()+ "   Cantidad Maxima de Estudiandes: " +clase.getCapacidadMaximaAlumnos()+"  Valor: $"+clase.getTarifa());
                    acumulador+=10;
                }
            }
        }
        color = new Color(255,255-acumulador, 255-acumulador);
        JButton botonMiercoles = new JButton("");
        botonMiercoles.setForeground(color);

        botones[2] = botonMiercoles;



        acumulador = 0;
        for(Horario horario: Horario.values()){
            List<Clase> clasesDelBloque = Calendario.getInstancia().getClasesEnBloque(Dia.JUEVES, horario);
            if (clasesDelBloque == null) {
                clasesDelBloque = Collections.emptyList();
            }
            for(Clase clase : clasesDelBloque){
                if(clase != null){
                    jueves.add(clase.getProfesor().getNombre()+" "+clase.getProfesor().getApellido()+"  "+clase.getAsignatura().toString()+ "   Cantidad Maxima de Estudiandes: " +clase.getCapacidadMaximaAlumnos()+"  Valor: $"+clase.getTarifa());
                    acumulador+=10;
                }
            }
        }
        color = new Color(255,255-acumulador, 255-acumulador);
        JButton botonJueves = new JButton("");
        botonJueves.setForeground(color);

        botones[3] = botonJueves;



        acumulador = 0;
        for(Horario horario: Horario.values()){
            List<Clase> clasesDelBloque = Calendario.getInstancia().getClasesEnBloque(Dia.VIERNES, horario);
            if (clasesDelBloque == null) {
                clasesDelBloque = Collections.emptyList();
            }
            for(Clase clase : clasesDelBloque){
                if(clase != null){
                    viernes.add(clase.getProfesor().getNombre()+" "+clase.getProfesor().getApellido()+"  "+clase.getAsignatura().toString()+ "   Cantidad Maxima de Estudiandes: " +clase.getCapacidadMaximaAlumnos()+"  Valor: $"+clase.getTarifa());
                    acumulador+=10;
                }
            }
        }
        color = new Color(255,255-acumulador, 255-acumulador);
        JButton botonViernes = new JButton("");
        botonViernes.setForeground(color);

        botones[4] = botonViernes;

        botonLunes.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                StringBuilder mensajeLunes = new StringBuilder();
                if (!lunes.isEmpty()) {
                    for (Object info : lunes) {
                        mensajeLunes.append(info.toString()).append("\n\n");
                    }
                } else {
                    mensajeLunes.append("No hay clases programadas para el Lunes.");
                }
                JOptionPane.showMessageDialog(PanelCalendario.this, mensajeLunes.toString(), "Clases del Lunes", JOptionPane.INFORMATION_MESSAGE);
            }
        });
        botonMartes.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                StringBuilder mensajeMartes = new StringBuilder();
                if (!martes.isEmpty()) {
                    for (Object info : martes) {
                        mensajeMartes.append(info.toString()).append("\n\n");
                    }
                } else {
                    mensajeMartes.append("No hay clases programadas para el Martes.");
                }
                JOptionPane.showMessageDialog(PanelCalendario.this, mensajeMartes.toString(), "Clases del Martes", JOptionPane.INFORMATION_MESSAGE);
            }
        });
        botonMiercoles.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                StringBuilder mensajeMiercoles = new StringBuilder();
                if (!miercoles.isEmpty()) {
                    for (Object info : miercoles) {
                        mensajeMiercoles.append(info.toString()).append("\n\n");
                    }
                } else {
                    mensajeMiercoles.append("No hay clases programadas para el Miercoles.");
                }
                JOptionPane.showMessageDialog(PanelCalendario.this, mensajeMiercoles.toString(), "Clases del Miercoles", JOptionPane.INFORMATION_MESSAGE);
            }
        });
        botonJueves.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                StringBuilder mensajeJueves = new StringBuilder();
                if (!jueves.isEmpty()) {
                    for (Object info : jueves) {
                        mensajeJueves.append(info.toString()).append("\n\n");
                    }
                } else {
                    mensajeJueves.append("No hay clases programadas para el Jueves.");
                }
                JOptionPane.showMessageDialog(PanelCalendario.this, mensajeJueves.toString(), "Clases del Jueves", JOptionPane.INFORMATION_MESSAGE);
            }
        });
        botonViernes.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                StringBuilder mensajeViernes = new StringBuilder();
                if (!viernes.isEmpty()) {
                    for (Object info : viernes) {
                        mensajeViernes.append(info.toString()).append("\n\n");
                    }
                } else {
                    mensajeViernes.append("No hay clases programadas para el Viernes.");
                }
                JOptionPane.showMessageDialog(PanelCalendario.this, mensajeViernes.toString(), "Clases del Viernes", JOptionPane.INFORMATION_MESSAGE);
            }
        });
    }

    private void cuerpo() {
        JPanel tabla = new JPanel();
        tabla.setLayout(new GridLayout(2, 5, 5, 5));
        Font fuente = new Font("Arial", Font.BOLD, 20);
        for(String dia: dias){
            JLabel str = new JLabel(dia);
            str.setFont(fuente);
            tabla.add(str);
        }
        for(JButton boton: botones){
            tabla.add(boton);
        }
        add(tabla);
    }

    private void titulo(){
        Font fuente = new Font("Arial", Font.BOLD, 90);
        JLabel title = new JLabel("Calendario");
        title.setForeground(Color.white);
        title.setFont(fuente);
        this.add(title);
        title.setVisible(true);
    }
}
