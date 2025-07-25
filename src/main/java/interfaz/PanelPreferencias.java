package interfaz;

import Logica.BloqueHorario;
import Logica.Enums.Asignatura;
import Logica.Enums.Dia;
import Logica.Enums.Horario;
import Logica.Estudiante;
import Logica.EstudianteFactory;

import javax.swing.*;
import java.awt.*;
import java.util.*;

/**
 * Panel que permite al estudiante configurar sus preferencias,
 * como menor tarifa, menor cantidad de alumnos, y horario/día preferido.
 */
public class PanelPreferencias extends JPanelConBotones {
    private boolean menorTarifa = false;
    private boolean menorCantidadEstudiantes = false;
    private final Set<Asignatura> materiasPreferidas = EnumSet.noneOf(Asignatura.class);
    private final Set<Dia> diasPreferidos = EnumSet.noneOf(Dia.class);
    private final Set<Horario> horasPreferidas = EnumSet.noneOf(Horario.class);
    private final Set<String> bloquesPreferidos = new java.util.HashSet<>();
    private final Map<BloqueHorario, JCheckBox> mapaCheckBoxes = new LinkedHashMap<>();

    private final Estudiante estudiante;

    /**Inicializa el panel con las opciones de preferencias y un botón de continuación.
     * @param estudiante el estudiante que realiza la solicitud
     */
    public PanelPreferencias(Estudiante estudiante){
        super();
        this.estudiante = estudiante;
        setBackground(new Color(33, 33, 33));
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        InterfazUtils.agregarTitulo("Preferencias", this);

        agregarOpciones();
        agregarBotonGuardarPreferencias();
    }

    private void mostrarSelectorDias(){
        diasPreferidos.clear();
        diasPreferidos.addAll(mostrarSelectorGenerico(Dia.class, "Seleccionar Días", diasPreferidos));
    }

    private void mostrarSelectorHoras(){
        horasPreferidas.clear();
        horasPreferidas.addAll(mostrarSelectorGenerico(Horario.class, "Seleccionar Horarios", horasPreferidas));
    }

    private void mostrarSelectorMaterias(){
        materiasPreferidas.clear();
        materiasPreferidas.addAll(mostrarSelectorGenerico(Asignatura.class, "Seleccionar Materias", materiasPreferidas));
    }

    private void mostrarSelectorBloques(){
        Dia[] dias = Dia.values();
        Horario[] horarios = Horario.values();

        JPanel panelPrincipal = new JPanel(new BorderLayout());
        JPanel grid = new JPanel(new GridLayout(horarios.length + 1, dias.length + 1, 5, 5));
        grid.setBackground(Color.WHITE);

        // Etiqueta vacía esquina
        grid.add(new JLabel(""));

        for (Dia dia : dias) {
            JLabel etiquetaDia = new JLabel(dia.name(), SwingConstants.CENTER);
            etiquetaDia.setFont(new Font("Arial", Font.BOLD, 12));
            grid.add(etiquetaDia);
        }
        mapaCheckBoxes.clear();

        for (Horario horario : horarios) {
            JLabel etiquetaHorario = new JLabel(horario.name(), SwingConstants.CENTER);
            etiquetaHorario.setFont(new Font("Arial", Font.PLAIN, 12));
            grid.add(etiquetaHorario);

            for (Dia dia : dias) {
                BloqueHorario bloque = new BloqueHorario(dia, horario);
                JCheckBox box = new JCheckBox(bloque.toString());
                box.setBackground(Color.LIGHT_GRAY);
                box.setSelected(bloquesPreferidos.contains(bloque.toString()));
                mapaCheckBoxes.put(bloque, box);
                grid.add(box);
            }
        }

        panelPrincipal.add(grid, BorderLayout.CENTER);
        int result = JOptionPane.showConfirmDialog(this, panelPrincipal, "Bloques Preferidos", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            bloquesPreferidos.clear();
            for (BloqueHorario b : mapaCheckBoxes.keySet()) {
                if (mapaCheckBoxes.get(b).isSelected()) bloquesPreferidos.add(b.toString());
            }
        }
    }

    private <E extends Enum<E>> Set<E> mostrarSelectorGenerico(Class<E> claseEnum, String titulo, Set<E> conjuntoActual){
        E[] elementos = claseEnum.getEnumConstants();
        JCheckBox[] boxes = new JCheckBox[elementos.length];
        JPanel panel = new JPanel(new GridLayout(elementos.length, 1));

        for (int i = 0; i < elementos.length; i++) {
            JCheckBox box = new JCheckBox(elementos[i].toString());
            box.setSelected(conjuntoActual.contains(elementos[i]));
            boxes[i] = box;
            panel.add(box);
        }

        int result = JOptionPane.showConfirmDialog(this, panel, titulo, JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            Set<E> seleccionados = EnumSet.noneOf(claseEnum);
            for (int i = 0; i < elementos.length; i++) {
                if (boxes[i].isSelected()) seleccionados.add(elementos[i]);
            }
            return seleccionados;
        }
        return conjuntoActual;
    }

    /**
     * Crea y agrega al panel los componentes de selección de preferencias (checkboxes y JButtons).
     */
    private void agregarOpciones(){
        JPanel panel = new JPanel(new GridLayout(5, 2, 10, 10));
        panel.setOpaque(false);
        Font fuente = new Font("Arial", Font.BOLD, 16);

        JCheckBox checkTarifa = InterfazUtils.crearCheckbox("Menor Tarifa Posible", fuente, b -> menorTarifa = b);
        JCheckBox checkEstudiantes = InterfazUtils.crearCheckbox("Menor Cantidad de Estudiantes", fuente, b -> menorCantidadEstudiantes = b);

        JButton btnMaterias = new JButton("Seleccionar Materias Preferidas");
        btnMaterias.addActionListener(_ -> mostrarSelectorMaterias());

        JButton btnDias = new JButton("Seleccionar Días Preferidos");
        btnDias.addActionListener(_ -> mostrarSelectorDias());

        JButton btnHoras = new JButton("Seleccionar Horarios Preferidos");
        btnHoras.addActionListener(_ -> mostrarSelectorHoras());

        JButton btnBloques = new JButton("Seleccionar Bloques Preferidos");
        btnBloques.addActionListener(_ -> mostrarSelectorBloques());

        panel.add(checkTarifa); panel.add(checkEstudiantes); panel.add(btnMaterias); panel.add(btnDias); panel.add(btnHoras); panel.add(btnBloques);
        add(panel);
    }

    private void agregarBotonGuardarPreferencias(){
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        panel.setOpaque(false);

        JButton botonGuardar = new JButton("Guardar preferencias");
        botonGuardar.addActionListener(_ -> {
            Set<BloqueHorario> seleccionados = new HashSet<>();
            for (Map.Entry<BloqueHorario, JCheckBox> entry : mapaCheckBoxes.entrySet()) {
                if (entry.getValue().isSelected()) {
                    seleccionados.add(entry.getKey());
                }
            }

            estudiante.setMateriasInteres(materiasPreferidas);
            estudiante.setDiasInteres(diasPreferidos);
            estudiante.setHorariosInteres(horasPreferidas);
            estudiante.setBloquesHorariosInteres(seleccionados);
            estudiante.setPreferirClaseConMenosEstudiantes(menorCantidadEstudiantes);
            estudiante.setPreferirMenorTarifa(menorTarifa);
            EstudianteFactory.guardarEstudiantes();

            JOptionPane.showMessageDialog(this, "Preferencias guardadas.");
        });

        panel.add(botonGuardar);
        add(Box.createRigidArea(new Dimension(0, 40)));
        add(panel);
    }
}