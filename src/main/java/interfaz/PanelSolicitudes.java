package interfaz;

import Logica.Clase;
import Logica.Enums.EstadoSolicitud;
import Logica.Estrategias.*;
import Logica.GestorSolicitudes;
import Logica.Solicitud;
import Logica.ObservadorSolicitudes;

import javax.swing.*;
import java.awt.*;
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

    public PanelSolicitudes(){
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBackground(new Color(30, 30, 30));

        InterfazUtils.agregarTitulo("Solicitudes", this);
        add(Box.createRigidArea(new Dimension(0, 30)));

        configurarPanelAdmin();

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
        JButton btnSalir = new JButton("Salir");

        panelControles.add(btnAceptar);
        panelControles.add(btnRechazar);
        panelControles.add(btnSalir);

        add(panelControles);

        btnAceptar.addActionListener(_ -> manejarDecision(true));
        btnRechazar.addActionListener(_ -> manejarDecision(false));
        btnSalir.addActionListener(_ -> Ventana.principal());
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
}