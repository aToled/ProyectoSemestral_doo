package interfaz.PanelesAdmin;

import Logica.BloqueHorario;
import Logica.Calendario;
import Logica.Enums.Dia;
import Logica.Enums.Horario;
import Logica.Identificables.Clase;
import interfaz.Utils.InterfazUtils;
import interfaz.Utils.JPanelConBotones;

import javax.swing.*;
import java.awt.*;
import java.util.List;

/**
 * Crea el calendario que visualizara el Admin.
 */
public class PanelCalendario extends JPanelConBotones {

    public PanelCalendario(){
        super();
        setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
        setBackground(new Color(33, 33, 33));
        InterfazUtils.agregarTitulo("Calendario", this);
        add(Box.createRigidArea(new Dimension(0,70)));

        cargarCuerpo();
        add(Box.createRigidArea(new Dimension(0,40)));
    }

    /**
     * Crea la tabla de etiquetas y botones para los días y horarios, con colores según cantidad de clases.
     */
    private void cargarCuerpo() {
        Dia[] dias = Dia.values();
        Horario[] horarios = Horario.values();

        JPanel tabla = new JPanel(new GridLayout(horarios.length + 1, dias.length + 1, 5, 5));
        tabla.setBackground(Color.WHITE);

        // Etiqueta vacía esquina
        tabla.add(new JLabel(""));

        for (Dia dia : dias) {
            JLabel etiquetaDia = new JLabel(dia.name(), SwingConstants.CENTER);
            etiquetaDia.setFont(new Font("Arial", Font.BOLD, 14));
            tabla.add(etiquetaDia);
        }

        for (Horario horario : horarios) {
            JLabel etiquetaHorario = new JLabel(horario.name(), SwingConstants.CENTER);
            etiquetaHorario.setFont(new Font("Arial", Font.BOLD, 13));
            tabla.add(etiquetaHorario);

            for (Dia dia : dias) {
                BloqueHorario bloque = new BloqueHorario(dia, horario);
                List<Clase> clases = Calendario.getInstancia().getClasesEnBloque(dia, horario);

                JButton boton = getBotonColoreado(bloque, clases);
                tabla.add(boton);
            }
        }
        add(tabla);
    }

    private JButton getBotonColoreado(BloqueHorario bloque, List<Clase> clases) {
        JButton boton = new JButton();
        boton.setOpaque(true);
        boton.setBorderPainted(true);
        boton.setToolTipText(bloque.toString());

        int n;
        if (clases != null) {
            n = Math.min(clases.size(), 10);
        } else {
            n = 0;
        }

        Color color;
        if (n == 0) {
            color = Color.WHITE;
            boton.setEnabled(false);
        } else {  // saque esta formula de internet, obviamente.
            int r = Math.round(197 - (n - 1) * (197 - 46) / 9f);
            int g = Math.round(232 - (n - 1) * (232 - 182) / 9f);
            int b = Math.round(183 - (n - 1) * (183 - 44) / 9f);
            color = new Color(r, g, b);
            boton.addActionListener(_ -> mostrarClasesBloque(clases, bloque));
        }

        boton.setBackground(color);
        boton.setForeground(Color.BLACK);
        return boton;
    }

    private void mostrarClasesBloque(List<Clase> clases, BloqueHorario bloque) {
        StringBuilder mensaje = new StringBuilder("Clases en " + bloque + ":\n\n");

        if (clases == null || clases.isEmpty()) {
            mensaje.append("No hay clases en este bloque.");
        } else {
            for (Clase clase : clases) {
                mensaje.append("- ").append(clase.getProfesor().getNombre()).append(" ").append(clase.getProfesor().getApellido()).append(" | ").append(clase.getAsignatura()).append(" | Máx Estudiantes: ").append(clase.getCapacidadMaximaAlumnos()).append(" | Tarifa: $").append(clase.getTarifa()).append("\n\n");
            }
        }

        JOptionPane.showMessageDialog(this, mensaje.toString(), "Clases", JOptionPane.INFORMATION_MESSAGE);
    }
}