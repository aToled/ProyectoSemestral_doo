package interfaz;

import Logica.Calendario;
import Logica.Clase;
import Logica.Enums.Dia;
import Logica.Enums.Horario;

import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.util.List;

/**
 * Crea el calendario que visualizara el Admin.
 */
public class PanelCalendario extends JPanel {

    private final Dia[] dias = Dia.values();
    private final Map<Dia, List<String>> clasesPorDia = new EnumMap<>(Dia.class);
    private final JButton[] botones=new JButton[5];

    public PanelCalendario(){
        setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
        setBackground(new Color(30, 30, 30));
        InterfazUtils.agregarTitulo("Calendario", this);
        add(Box.createRigidArea(new Dimension(0,70)));

        cargarClases();
        cuerpo();
        add(Box.createRigidArea(new Dimension(0,40)));
        agregarBotonSalir();
    }

    /**
     * Carga las clases por día desde el calendario y crea botones representativos con color dinámico.
     */
    private void cargarClases() {
        for (Dia dia : dias) {
            List<String> clases = new ArrayList<>();
            int intensidadColor = 0;

            for (Horario horario : Horario.values()) {
                List<Clase> clasesBloque = Calendario.getInstancia().getClasesEnBloque(dia, horario);
                if (clasesBloque != null) {
                    for (Clase clase : clasesBloque) {
                        if (clase != null) {
                            clases.add(clase.getProfesor().getNombre() + " " + clase.getProfesor().getApellido() + "  " + clase.getAsignatura() + "   Cantidad Máxima de Estudiantes: " + clase.getCapacidadMaximaAlumnos() + "  Valor: $" + clase.getTarifa());
                            intensidadColor += 10;
                        }
                    }
                }
            }

            clasesPorDia.put(dia, clases);
            Color color = new Color(255, Math.max(0, 255 - intensidadColor), Math.max(0, 255 - intensidadColor));

            JButton boton = new JButton("");
            boton.setForeground(color);
            boton.addActionListener(_ -> mostrarClases(dia));
            botones[dia.ordinal()] = boton;
        }
    }


    /**
     * Muestra en un diálogo las clases programadas para un día específico.
     * @param dia el día para el que se consultan las clases.
     */
    private void mostrarClases(Dia dia) {
        List<String> clases = clasesPorDia.getOrDefault(dia, Collections.emptyList());
        StringBuilder mensaje = new StringBuilder();

        if (clases.isEmpty()) {
            mensaje.append("No hay clases programadas para el ").append(dia.name().toLowerCase(Locale.ROOT));
        } else {
            for (String info : clases) {
                mensaje.append(info).append("\n\n");
            }
        }

        JOptionPane.showMessageDialog(this, mensaje.toString(), "Clases del " + dia.name(), JOptionPane.INFORMATION_MESSAGE);
    }


    /**
     * Crea la tabla de etiquetas y botones para los días de la semana, y la agrega al panel.
     */
    private void cuerpo() {
        JPanel tabla = new JPanel(new GridLayout(2, 5, 5, 5));

        for (Dia dia : dias) {
            JLabel etiqueta = new JLabel(dia.name().charAt(0) + dia.name().substring(1).toLowerCase());
            etiqueta.setFont(new Font("Arial", Font.BOLD, 20));
            tabla.add(etiqueta);
        }

        for(JButton boton: botones){
            tabla.add(boton);
        }
        add(tabla);
    }

    /**
     * Agrega un botón de salida que redirige a la ventana principal.
     */
    private void agregarBotonSalir() {
        JPanel panelBotonSalir = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        panelBotonSalir.setOpaque(false);

        JButton btnSalir = new JButton("Salir");
        btnSalir.addActionListener(_ -> Ventana.principal());
        panelBotonSalir.add(btnSalir);
        add(panelBotonSalir);
    }
}