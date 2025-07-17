package interfaz;

import Logica.Estudiante;

import javax.swing.*;
import java.awt.*;

/**
 * Panel que permite al estudiante configurar sus preferencias de solicitud,
 * como menor tarifa, menor cantidad de alumnos, y horario/día preferido.
 */
public class PanelPreferencias extends JPanel {
    protected static boolean menorTarifa = false;
    protected static boolean menorCantidadEstudiantes = false;
    protected static boolean diaPreferido = false;
    protected static boolean horaPreferido = false;
    protected static boolean diaHoraPreferido = false;
    private final Estudiante estudiante;

    /**Inicializa el panel con las opciones de preferencias y un botón de continuación.
     * @param estudiante el estudiante que realiza la solicitud
     */
    public PanelPreferencias(Estudiante estudiante){
        this.estudiante = estudiante;
        setBackground(new Color(30, 30, 30));
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        InterfazUtils.agregarTitulo("Preferencias de Solicitud", this);

        opciones();
        botonContinuar();
    }

    /**
     * Crea y agrega al panel los componentes de selección de preferencias (checkboxes y radio buttons).
     */
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

     radioDia.addActionListener(e -> {
         JRadioButton boton = (JRadioButton) e.getSource();
         diaPreferido = boton.isSelected();
     });

     radioHorario.addActionListener(e -> {
         JRadioButton boton = (JRadioButton) e.getSource();
         horaPreferido = boton.isSelected();
     });

     radioDia.addActionListener(e -> {
         JRadioButton boton = (JRadioButton) e.getSource();
         diaHoraPreferido = boton.isSelected();
     });

     checkTarifa.addActionListener(e -> {
         JCheckBox source = (JCheckBox) e.getSource();
         menorTarifa = source.isSelected();
     });

     checkEstudiantes.addActionListener(e -> {
         JCheckBox source = (JCheckBox) e.getSource();
         menorCantidadEstudiantes = source.isSelected();
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

    /**
     * Agrega el botón "CONTINUAR", que redirige a la solicitud para el estudiante.
     */
    private void botonContinuar(){
        JPanel panel = new JPanel();
        panel.setOpaque(false);
        panel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        add(Box.createRigidArea(new Dimension(0, 40)));


        JButton botonContinuar = new JButton("CONTINUAR");
        panel.add(botonContinuar);

        add(panel);
        panel.add(Box.createRigidArea(new Dimension(0, 20)));

        botonContinuar.addActionListener(_ -> Ventana.solicitudEstudiante(estudiante));
    }
}