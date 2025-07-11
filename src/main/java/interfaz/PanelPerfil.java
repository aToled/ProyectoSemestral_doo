package interfaz;

import Logica.Enums.Asignatura;
import Logica.Enums.Horario;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PanelPerfil extends JPanel {
    JComboBox combo;
    JPanel panel;
    private boolean elegido = false;
    private final String dias[] = {"LUNES","MARTES","MIERCOLES","JUEVES","VIERNES"};

    public PanelPerfil(){

        this.setBackground(Color.black);
        this.setVisible(true);
        this.setLayout(new GridLayout(0,2));
        this.titulo("Eliminar");
        this.titulo("Agregar");

        this.eliminar();
        this.texto();
        this.parteBaja();

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
        panel = new JPanel();
        panel.setLayout(new GridLayout(9,2));
        JLabel nombre = new JLabel("Nombre:");
        Font fuente = new Font("Arial", Font.BOLD, 15);
        nombre.setFont(fuente);
        JLabel apellido = new JLabel("Apellido:");
        apellido.setFont(fuente);
        JLabel correo = new JLabel("Correo electronico:");
        correo.setFont(fuente);
        JLabel id = new JLabel("Id:");
        id.setFont(fuente);
        JLabel perfil = new JLabel("Tipo de Persona: ");
        perfil.setFont(fuente);

        JTextField campo1 = new JTextField("Ingrese su Nombre");
        JTextField campo2 = new JTextField("Ingrese su Apellido");
        JTextField campo3 = new JTextField("ejemplo@gmail.com");
        JTextField campo4 = new JTextField("Numero de id");

        this.combo = new JComboBox<String>();
        combo.addItem("");
        combo.addItem("ESTUDIANTE");
        combo.addItem("PROFESOR");

        nombre.setForeground(Color.GRAY);
        apellido.setForeground(Color.GRAY);
        correo.setForeground(Color.GRAY);
        id.setForeground(Color.GRAY);
        perfil.setForeground(Color.GRAY);

        panel.add(perfil);
        panel.add(combo);
        panel.add(nombre);
        panel.add(campo1);
        panel.add(apellido);
        panel.add(campo2);
        panel.add(correo);
        panel.add(campo3);
        panel.add(id);
        panel.add(campo4);


        panel.setOpaque(false);

        panel.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.add(panel);
    }

    public void parteBaja(){
        JButton boton = new JButton("AGREGAR");
        boton.setEnabled(false);

        add(Box.createRigidArea(new Dimension(200,40)));
        JPanel panelBoton = new JPanel(new FlowLayout());
        panelBoton.setOpaque(false);
        add(panelBoton);
        boton.setPreferredSize(new Dimension(100,40));

        panelBoton.add(boton);

        combo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String opcionSeleccionada = (String) combo.getSelectedItem();

                if ("".equals(opcionSeleccionada)) {
                    boton.setEnabled(false);
                    elegido = true;


                } else if("PROFESOR".equals(opcionSeleccionada)){
                    JLabel asignatura = new JLabel("Asignatura:");
                    Font fuente = new Font("Arial", Font.BOLD, 15);
                    asignatura.setFont(fuente);
                    asignatura.setForeground(Color.GRAY);

                    JLabel capacidad = new JLabel("Capacidad de Estudiantes:");
                    capacidad.setFont(fuente);
                    JTextField cantidad = new JTextField();

                    JComboBox comboAsignatura = new JComboBox<Asignatura>();
                    for(Asignatura asignatura1: Asignatura.values()){
                        comboAsignatura.addItem(asignatura1);
                    }


                    JLabel horario = new JLabel("Horario:");
                    horario.setFont(fuente);
                    JComboBox comboHorario = new JComboBox<String>();
                    for(Horario horario1: Horario.values()){
                        comboHorario.addItem(horario1.toString());
                    }

                    JLabel dia = new JLabel("Dia:");
                    dia.setFont(fuente);
                    JComboBox comboDia = new JComboBox<String>();
                    for(String dia1: dias){
                        comboDia.addItem(dia1);
                    }

                    asignatura.setForeground(Color.GRAY);
                    capacidad.setForeground(Color.GRAY);
                    horario.setForeground(Color.GRAY);
                    dia.setForeground(Color.GRAY);

                    panel.add(asignatura);
                    panel.add(comboAsignatura);
                    panel.add(capacidad);
                    panel.add(cantidad);
                    panel.add(horario);
                    panel.add(comboHorario);
                    panel.add(dia);
                    panel.add(comboDia);
                    panel.revalidate();
                    panel.repaint();
                    Ventana.refrescar();


                    boton.setEnabled(true);
                    if (elegido == true){
                        elegido = false;
                        panel.remove(comboAsignatura);
                    }else{
                        elegido = false;
                        panel.remove(asignatura);
                        panel.remove(comboAsignatura);
                        panel.remove(capacidad);
                        panel.remove(cantidad);
                        panel.remove(horario);
                        panel.remove(comboHorario);
                        panel.remove(dia);
                        panel.remove(comboDia);
                    }
                }
            }
        });
    }
}