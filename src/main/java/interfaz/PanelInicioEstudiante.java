package interfaz;

import Logica.Estudiante;
import Logica.EstudianteFactory;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Set;

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


                Set<Estudiante> todosLosEstudiantes = EstudianteFactory.cargarEstudiantes();
                if (todosLosEstudiantes != null) {
                    for (Estudiante est : todosLosEstudiantes) {
                        // Compara el correo y el ID (contraseña)
                        // Asegúrate de que getCorreo() y getId() existen en tu clase Estudiante
                        if (est.getCorreo().equals(correo) && est.getId().equals(contraseña)) {
                            estudiante = est; // ¡Encontramos al estudiante!
                            break; // Salimos del bucle una vez que lo encontramos
                        }
                    }
                }

                if (estudiante != null) {
                    JOptionPane.showMessageDialog(PanelInicioEstudiante.this,
                            "Inicio de sesión exitoso para: " + estudiante.getNombre() + " " + estudiante.getApellido(),
                            "Éxito", JOptionPane.INFORMATION_MESSAGE);
                    Ventana.solicitudEstudiante(estudiante); // Pasamos la instancia del estudiante encontrado
                } else {
                    JOptionPane.showMessageDialog(PanelInicioEstudiante.this,
                            "Correo o contraseña incorrectos.",
                            "Error de Inicio de Sesión", JOptionPane.ERROR_MESSAGE);
                }
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
