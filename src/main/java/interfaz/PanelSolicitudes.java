package interfaz;

import Logica.*;
import Logica.Enums.Asignatura;
import Logica.Enums.EstadoSolicitud;
import Logica.Estrategias.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Set;

/**
 * Panel utilizado por el administrador para gestionar solicitudes pendientes,
 * permite aceptar o rechazar solicitudes usando estrategias definidas.
 */
public class PanelSolicitudes extends JPanel implements ObservadorSolicitudes {
    private JComboBox<String> comboSolicitudes;
    private JButton btnAceptar;
    private JButton btnRechazar;
    private Profesor profesor;
    private JComboBox<Profesor> combo1;
    private JComboBox<Asignatura> combo2;
    private JComboBox<BloqueHorario> combo3;
    private JComboBox<Long> combo4;
    private JComboBox<Integer> combo5;
    private int id = 1000;

    public PanelSolicitudes(){
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBackground(new Color(30, 30, 30));

        InterfazUtils.agregarTitulo("Solicitudes", this);
        add(Box.createRigidArea(new Dimension(0, 30)));



        configurarPanelAdmin();
        add(Box.createRigidArea(new Dimension(0, 15)));
        InterfazUtils.agregarTitulo("Crear Clase", this);
        add(Box.createRigidArea(new Dimension(0, 20)));

        crearClase();

        GestorSolicitudes.getInstancia().suscribir(this);

        add(Box.createVerticalGlue());


    }

    /**
     * Configura el panel con controles de gestión (aceptar/rechazar).
     */
    private void configurarPanelAdmin() {
        JPanel panelControles = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        panelControles.setOpaque(false);

        comboSolicitudes = new JComboBox<>();
        comboSolicitudes.setPreferredSize(new Dimension(300, 30));
        panelControles.add(comboSolicitudes);

        btnAceptar = new JButton("Aceptar");
        btnRechazar = new JButton("Rechazar");

        panelControles.add(btnAceptar);
        panelControles.add(btnRechazar);

        add(panelControles);

        btnAceptar.addActionListener(_ -> manejarDecision(true));
        btnRechazar.addActionListener(_ -> manejarDecision(false));
    }

    /**
     * Lógica compartida para aceptar o rechazar solicitudes.
     * @param aceptar: true o false dependiendo si se busca aceptar o rechazar.
     */
    private void manejarDecision(boolean aceptar) {
        String item = (String) comboSolicitudes.getSelectedItem();
        if (item == null || item.isEmpty() || item.equals("No hay solicitudes pendientes")) return;

        String id = InterfazUtils.extractIdFromComboBoxItem(item);
        if (id == null) return;

        if (aceptar) aceptarSolicitud(id);
        else rechazarSolicitud(id);
    }

    /**
     * Acepta una solicitud usando las estrategias definidas.
     */
    private void aceptarSolicitud(String idSolicitud) {
        Solicitud solicitud = GestorSolicitudes.getInstancia().buscarObjeto(idSolicitud);
        boolean resolucionExitosa = GestorSolicitudes.getInstancia().resolver(idSolicitud,
                new EstrategiaMenorTarifa(),
                new EstrategiaConMenosEstudiantes(),
                new EstrategiaBloqueHorarioPreferido(),
                new EstrategiaDiaPreferido(),
                new EstrategiaHorarioPreferido(),
                new EstrategiaDefault());

        if (resolucionExitosa && solicitud.getClasesSugeridas() != null && !solicitud.getClasesSugeridas().isEmpty()) {
            Clase claseElegida = new ArrayList<>(solicitud.getClasesSugeridas()).getFirst();
            solicitud.setClaseElegida(claseElegida);
            GestorSolicitudes.getInstancia().aceptar(idSolicitud);
        } else {
            GestorSolicitudes.getInstancia().rechazar(idSolicitud);
        }
    }

    /**
     * Rechaza la solicitud directamente.
     */
    private void rechazarSolicitud(String idSolicitud) {
        GestorSolicitudes.getInstancia().rechazar(idSolicitud);
    }


    /**
     * Actualiza el JComboBox con las solicitudes actuales.
     */
    @Override
    public void actualizar(Set<Solicitud> nuevasSolicitudes) {
        comboSolicitudes.removeAllItems();
        boolean hayPendientes = false;

        if (nuevasSolicitudes != null && !nuevasSolicitudes.isEmpty()) {
            for (Solicitud sol : nuevasSolicitudes) {
                if (sol.getEstadoSolicitud() == EstadoSolicitud.PENDIENTE) {
                    comboSolicitudes.addItem(sol.getEstudiante().getNombre() + " - " + sol.getAsignatura() + " (ID: " + sol.getId() + ")");
                    hayPendientes = true;
                }
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

    private void crearClase(){

        Font fuente = new Font("Arial", Font.BOLD, 20);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(6,2, 5, 5));
        panel.setOpaque(false);

        combo1 = new JComboBox<Profesor>();
        for(Profesor profe: ProfesorFactory.cargarProfesores()){
            combo1.addItem(profe);
        }

        profesor = (Profesor) combo1.getSelectedItem();

        combo2 = new JComboBox<>();
        combo3 = new JComboBox<>();
        combo4 = new JComboBox<>();
        combo5 = new JComboBox<>();

        actualizar();

        combo1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                profesor = (Profesor) combo1.getSelectedItem();
                actualizar();
            }
        });

        panel.add(InterfazUtils.label("Profesor: ", fuente));
        panel.add(combo1);

        panel.add(InterfazUtils.label("Asignatura: ", fuente));
        panel.add(combo2);

        panel.add(InterfazUtils.label("Disponibilidad: ", fuente));
        panel.add(combo3);

        panel.add(InterfazUtils.label("Tarifa: ", fuente));
        panel.add(combo4);

        panel.add(InterfazUtils.label("Cantidad Maxima Estudiantes: ", fuente));
        panel.add(combo5);

        JPanel panelBoton = new JPanel();
        panelBoton.setLayout(new FlowLayout(FlowLayout.CENTER));
        panelBoton.setOpaque(false);
        JButton crear = new JButton("Crear");
        crear.setPreferredSize(new Dimension(150,30));
        panelBoton.add(crear);
        panel.add(panelBoton);


        JPanel panelSalir = new JPanel();
        panelSalir.setLayout(new FlowLayout(FlowLayout.CENTER));
        panelSalir.setOpaque(false);
        JButton salir = new JButton("Salir");
        crear.setPreferredSize(new Dimension(150,30));
        panelSalir.add(salir);
        panel.add(panelSalir);

        id++;
        panel.setAlignmentX(Component.CENTER_ALIGNMENT);

        add(panel);

        crear.addActionListener(_ -> {
            Clase clase = new Clase((Profesor) combo1.getSelectedItem(),String.valueOf(id++), (Asignatura) combo2.getSelectedItem(), (BloqueHorario) combo3.getSelectedItem(), (Integer) combo5.getSelectedItem(), (Long) combo4.getSelectedItem());
            JOptionPane.showMessageDialog(this, "Clase creada exitosamente.", "Clase Creada", JOptionPane.INFORMATION_MESSAGE);
        });

        salir.addActionListener(_ -> {
            Ventana.principal();
        });
    }

    private void actualizar() {
        if (profesor != null) {
            combo2.removeAllItems();
            for (Asignatura asignatura : profesor.getMateriasQueDicta()) {
                combo2.addItem(asignatura);
            }

            combo3.removeAllItems();

            for (BloqueHorario bloque : profesor.getDisponibilidad()) {
                combo3.addItem(bloque);
            }

            combo4.removeAllItems();
            for (Long precio : profesor.getTarifas()) {
                combo4.addItem(precio);
            }
            combo5.removeAllItems();
            for (Integer cantidad : profesor.getCapacidadesMaximasAlumnos()) {
                combo5.addItem(cantidad);
            }
        } else {
            combo2.removeAllItems();
            combo3.removeAllItems();
            combo4.removeAllItems();
            combo5.removeAllItems();
        }
    }
}