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

/**
 * Panel que le da la opcion al estudiante de seleccionar preferencias, clases y enviar solicitud
 */
public class AgregarSolicitudEstudiante extends JPanel {

    private Estudiante estudiante;

    public AgregarSolicitudEstudiante(Estudiante estudiante) {
        this.estudiante = estudiante;
        this.setBackground(new Color(30, 30, 30));
        this.setVisible(true);
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        InterfazUtils.agregarTitulo("Agregar Solicitud", this);
        JButton boton1 = InterfazUtils.addBotonesConIcono("/botonPreferencias.png", this, 30);

        JButton boton2 = InterfazUtils.addBotonesConIcono("/botonSolicitudClases.png", this, 30);

        add(Box.createRigidArea(new Dimension(0, 50)));
        add(boton1);
        add(Box.createRigidArea(new Dimension(0, 40)));
        add(boton2);
        add(Box.createRigidArea(new Dimension(0, 60)));

        boton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Ventana.preferencias(estudiante);
            }
        });
        boton2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Ventana.seleccionarClase(estudiante);
            }
        });
    }
}