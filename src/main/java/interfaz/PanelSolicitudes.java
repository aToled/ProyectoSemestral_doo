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

public class PanelSolicitudes extends JPanel implements ObservadorSolicitudes {
    private JComboBox<String> comboSolicitudes;
    private JButton btnAceptar;
    private JButton btnRechazar;
    private JButton btnSalir;

    public PanelSolicitudes(){
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setBackground(Color.black);

        titulo();
        add(Box.createRigidArea(new Dimension(0, 30)));

        configurarPanelAdmin();

        GestorSolicitudes.getInstancia().suscribir(this);

        add(Box.createVerticalGlue());
    }

    private void titulo(){
        Font fuente = new Font("Arial", Font.BOLD, 90);
        JLabel title = new JLabel("Solicitudes");
        title.setForeground(Color.white);
        title.setFont(fuente);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.add(title);
        title.setVisible(true);
    }

    private void configurarPanelAdmin() {
        JPanel panelControles = new JPanel();
        panelControles.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 10));
        panelControles.setOpaque(false);

        comboSolicitudes = new JComboBox<>();
        comboSolicitudes.setPreferredSize(new Dimension(300, 30));
        panelControles.add(comboSolicitudes);

        btnAceptar = new JButton("Aceptar");
        btnRechazar = new JButton("Rechazar");
        btnSalir = new JButton("Salir");

        panelControles.add(btnAceptar);
        panelControles.add(btnRechazar);
        panelControles.add(btnSalir);

        this.add(panelControles);

        btnAceptar.addActionListener(_ -> {
            String selectedItem = (String) comboSolicitudes.getSelectedItem();
            if (selectedItem != null && !selectedItem.isEmpty() && !selectedItem.equals("No hay solicitudes pendientes")) {
                String idSolicitud = extractIdFromComboBoxItem(selectedItem);
                if (idSolicitud != null) {
                    aceptarSolicitud(idSolicitud);
                }
            }
        });

        btnRechazar.addActionListener(_ -> {
            String selectedItem = (String) comboSolicitudes.getSelectedItem();
            if (selectedItem != null && !selectedItem.isEmpty() && !selectedItem.equals("No hay solicitudes pendientes")) {
                String idSolicitud = extractIdFromComboBoxItem(selectedItem);
                if (idSolicitud != null) {
                    rechazarSolicitud(idSolicitud);
                }
            }
        });

        btnSalir.addActionListener(_ -> Ventana.principal());
    }

    @Override
    public void actualizar(Set<Solicitud> nuevasSolicitudes) {
        comboSolicitudes.removeAllItems();

        boolean pendiente = false;
        if (nuevasSolicitudes != null && !nuevasSolicitudes.isEmpty()) {
            for (Solicitud sol : nuevasSolicitudes) {
                if (sol.getEstadoSolicitud() == EstadoSolicitud.PENDIENTE) {
                    comboSolicitudes.addItem(sol.getEstudiante().getNombre() + " - " + sol.getAsignatura() + " (ID: " + sol.getId() + ")");
                    pendiente = true;
                }
            }
        }

        if (!pendiente) {
            comboSolicitudes.addItem("No hay solicitudes pendientes");
            btnAceptar.setEnabled(false);
            btnRechazar.setEnabled(false);
        } else {
            btnAceptar.setEnabled(true);
            btnRechazar.setEnabled(true);
        }
    }

    private String extractIdFromComboBoxItem(String item) {
        int startIndex = item.indexOf("(ID: ") + 4;
        int endIndex = item.indexOf(")", startIndex);
        if (endIndex != -1 && endIndex > startIndex) {
            return item.substring(startIndex, endIndex);
        }
        return null;
    }

    private void aceptarSolicitud(String idSolicitud) {
        Solicitud solicitud = GestorSolicitudes.getInstancia().buscarSolicitud(idSolicitud);

        boolean resolucionExitosa = GestorSolicitudes.getInstancia().resolver(idSolicitud, new EstrategiaMenorTarifa(), new EstrategiaConMenosEstudiantes(), new EstrategiaBloqueHorarioPreferido(), new EstrategiaDiaPreferido(), new EstrategiaHorarioPreferido(), new EstrategiaDefault());

        if (resolucionExitosa && solicitud.getClasesSugeridas() != null && !solicitud.getClasesSugeridas().isEmpty()) {
            Clase claseElegida = new ArrayList<>(solicitud.getClasesSugeridas()).getFirst();
            solicitud.setClaseElegida(claseElegida);

            GestorSolicitudes.getInstancia().aceptar(idSolicitud);
        } else {
            GestorSolicitudes.getInstancia().rechazar(idSolicitud);
        }
    }

    private void rechazarSolicitud(String idSolicitud) {
        GestorSolicitudes.getInstancia().rechazar(idSolicitud);
    }
}