package interfaz;

import Logica.*;
import Logica.Enums.Asignatura;
import Logica.Enums.Horario;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Set;

public class PanelAgregarProfesorAdmin extends JPanel {

    private JTextField campo1,campo2,campo3,campo4,campo5,campo6;
    JComboBox comboDia, comboHorario, comboAsignatura;

    public PanelAgregarProfesorAdmin(){
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
        JLabel title = new JLabel("Agregar Estudiante");
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
        JLabel cantidad = new JLabel("Cantidad Maxima de Estudiantes:");
        cantidad.setFont(fuente);
        JLabel tarifa = new JLabel("Tarifa:");
        tarifa.setFont(fuente);

        JLabel asignatura = new JLabel("Asignatura:");
        asignatura.setFont(fuente);
        comboAsignatura = new JComboBox<Asignatura>();
        for(Asignatura asignatura1: Asignatura.values()){
            comboAsignatura.addItem(asignatura1);
        }

        JLabel horario = new JLabel("Horario:");
        horario.setFont(fuente);
        comboHorario = new JComboBox<String>();
        for(Horario horario1: Horario.values()){
            comboHorario.addItem(horario1.toString());
        }

        campo1 = new JTextField("Ingrese su Nombre");
        campo2 = new JTextField("Ingrese su Apellido");
        campo3 = new JTextField("ejemplo@gmail.com");
        campo4 = new JTextField("Numero de id");
        campo5 = new JTextField("");
        campo6 = new JTextField("");

        nombre.setForeground(Color.GRAY);
        apellido.setForeground(Color.GRAY);
        correo.setForeground(Color.GRAY);
        id.setForeground(Color.GRAY);
        asignatura.setForeground(Color.GRAY);
        horario.setForeground(Color.GRAY);
        cantidad.setForeground(Color.GRAY);
        tarifa.setForeground(Color.GRAY);

        panel.add(Box.createRigidArea(new Dimension(350,50)));
        panel.add(nombre);
        panel.add(campo1);
        panel.add(Box.createRigidArea(new Dimension(350,50)));
        panel.add(Box.createRigidArea(new Dimension(350,50)));
        panel.add(apellido);
        panel.add(campo2);
        panel.add(Box.createRigidArea(new Dimension(350,50)));
        panel.add(Box.createRigidArea(new Dimension(350,50)));
        panel.add(correo);
        panel.add(campo3);
        panel.add(Box.createRigidArea(new Dimension(350,50)));
        panel.add(Box.createRigidArea(new Dimension(350,50)));
        panel.add(id);
        panel.add(campo4);
        panel.add(Box.createRigidArea(new Dimension(350,50)));
        panel.add(Box.createRigidArea(new Dimension(350,50)));
        panel.add(cantidad);
        panel.add(campo5);
        panel.add(Box.createRigidArea(new Dimension(350,50)));
        panel.add(Box.createRigidArea(new Dimension(350,50)));
        panel.add(tarifa);
        panel.add(campo6);
        panel.add(Box.createRigidArea(new Dimension(350,50)));
        panel.add(Box.createRigidArea(new Dimension(350,50)));
        panel.add(asignatura);
        panel.add(comboAsignatura);
        panel.add(Box.createRigidArea(new Dimension(350,50)));
        panel.add(Box.createRigidArea(new Dimension(350,50)));
        panel.add(horario);
        panel.add(comboHorario);

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
                Ventana.perfil();
                //Profesor profesor = ProfesorFactory.crearProfesor(campo1.getText(), campo2.getText(), campo3.getText(), campo4.getText(), Integer.parseInt(campo5.getText()),Long.parseLong(campo6.getText()), (Set<Asignatura>) comboAsignatura.getSelectedItem(), (Set<BloqueHorario>) comboHorario.getSelectedItem());
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
