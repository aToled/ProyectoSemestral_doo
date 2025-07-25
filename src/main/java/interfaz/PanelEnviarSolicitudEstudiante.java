package interfaz;

import Logica.*;
import Logica.Enums.Asignatura;
import Logica.Enums.EstadoSolicitud;
import Logica.Estrategias.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.Set;

/**
 * Panel de creación de solicitudes de estudiantes.
 */
public class PanelEnviarSolicitudEstudiante extends JPanelConBotones {
    private JComboBox<Asignatura> listaAsignaturas;
    private JComboBox<Clase> listaClases;
    private final Estudiante estudiante;
    private Solicitud solicitud = null;
    private JButton elegirClase;

    public PanelEnviarSolicitudEstudiante(Estudiante estudiante){
        super();
        this.estudiante = estudiante;
        JPanel contenidoPrincipal = new JPanel(new GridLayout(3,2,5,5));
        contenidoPrincipal.setBackground(new Color(33, 33, 33));

        InterfazUtils.agregarTitulo(" Crear Solicitud", contenidoPrincipal);
        InterfazUtils.agregarTitulo("      Elegir Clase", contenidoPrincipal);

        contenidoPrincipal.add(seleccionarAsignatura());
        contenidoPrincipal.add(seleccionarClase());
        contenidoPrincipal.add(botonCrearSolicitud());

        add(contenidoPrincipal, BorderLayout.CENTER);
    }

    private JPanel seleccionarAsignatura() {
        JPanel panel = new JPanel(new FlowLayout());
        panel.setOpaque(false);

        listaAsignaturas = new JComboBox<>(Asignatura.values());
        panel.add(InterfazUtils.label("Asignatura:", new Font("Arial", Font.BOLD, 20)));
        panel.add(listaAsignaturas);

        return panel;
    }

    /**
     * Se crean el botón para enviar solicitud y se le asignan las funcionalidades.
     */
    private JPanel botonCrearSolicitud() {
        JPanel panel = new JPanel(new FlowLayout());
        panel.setOpaque(false);

        JButton botonCrearSolicitud = new JButton("crear solicitud y obtener clases posibles");
        botonCrearSolicitud.setPreferredSize(new Dimension(300, 40));

        panel.add(botonCrearSolicitud);

        botonCrearSolicitud.addActionListener((ActionEvent _) -> {
            Asignatura asignaturaSeleccionada = (Asignatura) listaAsignaturas.getSelectedItem();
            if (asignaturaSeleccionada == null) {
                JOptionPane.showMessageDialog(this, "Seleccione una asignatura.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            solicitud = estudiante.crearSolicitud(asignaturaSeleccionada);
            GestorSolicitudes.getInstancia().resolver(solicitud.getId(), new EstrategiaMenorTarifa(), new EstrategiaConMenosEstudiantes(), new EstrategiaBloqueHorarioPreferido(), new EstrategiaDiaPreferido(), new EstrategiaHorarioPreferido(), new EstrategiaDefault());
            agregarClasesAlCombo();

            if(solicitud.getEstadoSolicitud() != EstadoSolicitud.INCONCLUSO){
                JOptionPane.showMessageDialog(this, "Solicitud creada y resuelta.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            } else {
                GestorSolicitudes.getInstancia().eliminar(solicitud.getId());
            }
        });
        return panel;
    }

    /**
     * Creación de los componentes para seleccionar clases dependiendo de las sugerencias.
     */
    public JPanel seleccionarClase(){
        JPanel panel = new JPanel(new FlowLayout());
        panel.setOpaque(false);

        listaClases = new JComboBox<>();
        panel.add(InterfazUtils.label("Clases Sugeridas:", new Font("Arial", Font.BOLD, 20)));
        panel.add(listaClases);

        elegirClase = new JButton("Elegir clase y confirmar solicitud");
        elegirClase.setEnabled(false);
        panel.add(elegirClase);

        elegirClase.addActionListener((ActionEvent _) -> {
            Clase claseSeleccionada = (Clase) listaClases.getSelectedItem();
            if (claseSeleccionada != null && solicitud != null) {
                solicitud.setClaseElegida(claseSeleccionada);
                GestorSolicitudes.getInstancia().agregar(solicitud);
                solicitud.clearClasesSugeridas();
                listaClases.removeAllItems();
                JOptionPane.showMessageDialog(this, "Clase asignada a la solicitud.", "Clase Asignada", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "Seleccione una clase válida.", "Error", JOptionPane.WARNING_MESSAGE);
            }
        });
        return panel;
    }

    /**
     * Agrega las clases al JComboBox, sirve para mantenerlo actualizado.
     */
    private void agregarClasesAlCombo(){
        listaClases.removeAllItems();
        Set<Clase> clasesSugeridas = solicitud.getClasesSugeridas();

        if (clasesSugeridas != null && !clasesSugeridas.isEmpty()){
            elegirClase.setEnabled(true);
            for(Clase clase : clasesSugeridas){
                listaClases.addItem(clase);
            }
            listaClases.setSelectedIndex(0);
        } else {
            elegirClase.setEnabled(false);
            JOptionPane.showMessageDialog(this, "No se encontraron clases disponibles.", "Sin Sugerencias", JOptionPane.WARNING_MESSAGE);
        }
    }
}