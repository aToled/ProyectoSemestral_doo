package interfaz;

import Logica.*;
import Logica.Enums.Asignatura;
import Logica.Enums.EstadoSolicitud;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;
import java.util.Set;

/**
 * Panel utilizado por el administrador para gestionar solicitudes pendientes,
 * permite aceptar o rechazar solicitudes usando estrategias definidas.
 */
public class PanelSolicitudesAdmin extends JPanel {
    private JComboBox<String> comboSolicitudes;
    private JButton btnAceptar;
    private JButton btnRechazar;
    private Profesor profesor;
    private JComboBox<Profesor> comboProfesores;
    private JComboBox<Asignatura> comboAsignaturas;
    private JComboBox<BloqueHorario> comboBloquesHorarios;
    private JComboBox<Long> comboTarifas;
    private JComboBox<Integer> comboCapacidades;

    /**
     * Inicializa el panel de gestión de solicitudes y creación de clases.
     */
    public PanelSolicitudesAdmin() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBackground(new Color(30, 30, 30));

        InterfazUtils.agregarTitulo("Solicitudes", this);
        add(Box.createRigidArea(new Dimension(0, 30)));

        configurarGestionSolicitudes();
        add(Box.createRigidArea(new Dimension(0, 15)));

        InterfazUtils.agregarTitulo("Crear Clase", this);
        add(Box.createRigidArea(new Dimension(0, 20)));

        configurarCreacionClases();
        add(Box.createVerticalGlue());
    }

    /**
     * Configura los controles para aceptar o rechazar solicitudes.
     */
    private void configurarGestionSolicitudes() {
        JPanel panelControles = crearPanelFlow();

        comboSolicitudes = new JComboBox<>();
        comboSolicitudes.setPreferredSize(new Dimension(300, 30));

        btnAceptar = new JButton("Aceptar");
        btnRechazar = new JButton("Rechazar");

        panelControles.add(comboSolicitudes);
        panelControles.add(btnAceptar);
        panelControles.add(btnRechazar);
        add(panelControles);

        btnAceptar.addActionListener(_ -> procesarSolicitud(true));
        btnRechazar.addActionListener(_ -> procesarSolicitud(false));

        actualizarSolicitudes();
    }

    /**
     * Procesa la solicitud seleccionada según la acción (aceptar o rechazar).
     * @param aceptar indica si se acepta la solicitud (true) o se rechaza (false)
     */
    private void procesarSolicitud(boolean aceptar) {
        String item = (String) comboSolicitudes.getSelectedItem();
        if (item == null || item.isEmpty() || item.equals("No hay solicitudes pendientes")) return;

        String id = InterfazUtils.extractIdFromComboBoxItem(item);
        if (id == null) return;

        if (aceptar) {
            GestorSolicitudes.getInstancia().aceptar(id);
        } else {
            GestorSolicitudes.getInstancia().rechazar(id);
        }

        actualizarSolicitudes();
    }

    /**
     * Actualiza el combo de solicitudes con las pendientes disponibles.
     */
    private void actualizarSolicitudes() {
        comboSolicitudes.removeAllItems();
        Set<Solicitud> solicitudes = GestorSolicitudes.getInstancia().getObjetosNoModificable();
        boolean hayPendientes = false;

        for (Solicitud sol : solicitudes) {
            if (sol.getEstadoSolicitud() == EstadoSolicitud.ESPERANDO) {
                comboSolicitudes.addItem(sol.getEstudiante().getNombre() + " - " + sol.getAsignatura() + " (ID: " + sol.getId() + ")");
                hayPendientes = true;
            }
        }
        if (!hayPendientes) {
            comboSolicitudes.addItem("No hay solicitudes pendientes");
            btnAceptar.setEnabled(false);
            btnRechazar.setEnabled(false);
        } else {
            btnAceptar.setEnabled(true);
            btnRechazar.setEnabled(true);
        }
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

        JButton btnSalir = new JButton("Salir");
        btnSalir.setPreferredSize(new Dimension(150, 30));
        btnSalir.addActionListener(_ -> Ventana.principal());

        JPanel panelCrear = crearPanelFlow(); panelCrear.add(btnCrear); panelFormulario.add(panelCrear);
        JPanel panelSalir = crearPanelFlow(); panelSalir.add(btnSalir); panelFormulario.add(panelSalir);
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
        Clase clase = new Clase((Profesor) Objects.requireNonNull(comboProfesores.getSelectedItem()), Integer.toString(Calendario.getInstancia().getCantidadObjetos()), (Asignatura) comboAsignaturas.getSelectedItem(), (BloqueHorario) comboBloquesHorarios.getSelectedItem(), capacidad, tarifa);
        Calendario.getInstancia().addClaseToBloque(clase);
        JOptionPane.showMessageDialog(this, "Clase creada exitosamente.", "Clase Creada", JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * Crea un panel con layout tipo Flow centrado.
     * @return el panel creado
     */
    private JPanel crearPanelFlow() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panel.setOpaque(false);
        return panel;
    }
}