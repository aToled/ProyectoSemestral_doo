package interfaz;

import Logica.*;
import Logica.Enums.EstadoSolicitud;

import javax.swing.*;
import java.awt.*;
import java.util.Set;

/**
 * Panel utilizado por el administrador para gestionar solicitudes pendientes,
 * permite aceptar o rechazar solicitudes usando estrategias definidas.
 */
public class PanelSolicitudesAdmin extends JPanelConBotones {
    private JComboBox<String> comboSolicitudes;
    private JButton btnAceptar;
    private JButton btnRechazar;

    /**
     * Inicializa el panel de gestión de solicitudes y creación de clases.
     */
    public PanelSolicitudesAdmin() {
        super();
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBackground(new Color(30, 30, 30));

        InterfazUtils.agregarTitulo("Solicitudes", this);
        add(Box.createRigidArea(new Dimension(0, 30)));

        configurarGestionSolicitudes();
        add(Box.createVerticalGlue());
    }

    /**
     * Configura los controles para aceptar o rechazar solicitudes.
     */
    private void configurarGestionSolicitudes() {
        JPanel panelControles = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panelControles.setOpaque(false);

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
}