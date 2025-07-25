package interfaz;

import Logica.*;
import Logica.Enums.Asignatura;
import javax.swing.*;
import java.awt.*;
import java.util.Objects;
import java.util.UUID;

/**
 * Panel para que el administrador cree nuevas clases usando datos del profesor.
 */
public class PanelCrearClases extends JPanelConBotones {
    private Profesor profesor;
    private JComboBox<Profesor> comboProfesores;
    private JComboBox<Asignatura> comboAsignaturas;
    private JComboBox<BloqueHorario> comboBloquesHorarios;
    private JComboBox<Long> comboTarifas;
    private JComboBox<Integer> comboCapacidades;

    public PanelCrearClases() {
        super();
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBackground(new Color(30, 30, 30));

        InterfazUtils.agregarTitulo("Crear Clase", this);
        add(Box.createRigidArea(new Dimension(0, 20)));

        configurarCreacionClases();
        add(Box.createVerticalGlue());
    }

    /**
     * Configura el formulario para crear una clase nueva.
     */
    private void configurarCreacionClases() {
        Font fuente = new Font("Arial", Font.BOLD, 20);
        JPanel panelFormulario = new JPanel(new GridLayout(6, 2, 5, 5));
        panelFormulario.setOpaque(false);

        comboProfesores = new JComboBox<>();
        for (Profesor p : ProfesorFactory.cargarProfesores()) comboProfesores.addItem(p);
        profesor = (Profesor) comboProfesores.getSelectedItem();

        comboAsignaturas = new JComboBox<>();
        comboBloquesHorarios = new JComboBox<>();
        comboTarifas = new JComboBox<>();
        comboCapacidades = new JComboBox<>();

        comboProfesores.addActionListener(_ -> {
            profesor = (Profesor) comboProfesores.getSelectedItem();
            actualizarDatosProfesor();
        });

        panelFormulario.add(InterfazUtils.label("Profesor:", fuente)); panelFormulario.add(comboProfesores);
        panelFormulario.add(InterfazUtils.label("Asignatura:", fuente)); panelFormulario.add(comboAsignaturas);
        panelFormulario.add(InterfazUtils.label("Disponibilidad:", fuente)); panelFormulario.add(comboBloquesHorarios);
        panelFormulario.add(InterfazUtils.label("Tarifa:", fuente)); panelFormulario.add(comboTarifas);
        panelFormulario.add(InterfazUtils.label("Capacidad Máxima:", fuente)); panelFormulario.add(comboCapacidades);

        JButton btnCrear = new JButton("Crear");
        btnCrear.setPreferredSize(new Dimension(150, 30));
        btnCrear.addActionListener(_ -> crearClase());

        JPanel panelCrear = new JPanel(new FlowLayout(FlowLayout.CENTER)); panelCrear.setOpaque(false); panelCrear.add(btnCrear); panelFormulario.add(panelCrear);
        panelFormulario.setAlignmentX(Component.CENTER_ALIGNMENT); add(panelFormulario);

        actualizarDatosProfesor();
    }

    /**
     * Actualiza los combos del formulario según el profesor seleccionado.
     */
    private void actualizarDatosProfesor() {
        if (profesor == null) return;

        comboAsignaturas.removeAllItems();
        for (Asignatura a : profesor.getMateriasQueDicta()) comboAsignaturas.addItem(a);

        comboBloquesHorarios.removeAllItems();
        for (BloqueHorario h : profesor.getDisponibilidad()) comboBloquesHorarios.addItem(h);

        comboTarifas.removeAllItems();
        for (Long t : profesor.getTarifas()) comboTarifas.addItem(t);

        comboCapacidades.removeAllItems();
        for (Integer c : profesor.getCapacidadesMaximasAlumnos()) comboCapacidades.addItem(c);
    }

    /**
     * Crea una clase nueva con los datos seleccionados en el formulario.
     */
    private void crearClase() {
        Integer capacidad = (Integer) comboCapacidades.getSelectedItem();
        Long tarifa = (Long) comboTarifas.getSelectedItem();
        if (capacidad == null || tarifa == null) {
            JOptionPane.showMessageDialog(null, "Por favor selecciona una capacidad y tarifa válida.");
            return;
        }
        Clase clase = new Clase((Profesor) Objects.requireNonNull(comboProfesores.getSelectedItem()), "c" + UUID.randomUUID(), (Asignatura) comboAsignaturas.getSelectedItem(), (BloqueHorario) comboBloquesHorarios.getSelectedItem(), capacidad, tarifa);
        Calendario.getInstancia().addClaseToBloque(clase);
        JOptionPane.showMessageDialog(this, "Clase creada exitosamente.", "Clase Creada", JOptionPane.INFORMATION_MESSAGE);
    }
}