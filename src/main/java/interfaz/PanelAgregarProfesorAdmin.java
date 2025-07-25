package interfaz;

import Logica.*;
import Logica.Enums.Asignatura;
import Logica.Enums.Dia;
import Logica.Enums.Horario;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

/**
 * Panel que permite al administrador registrar a un nuevo Profesor.
 */
public class PanelAgregarProfesorAdmin extends JPanelConBotones {

    private JTextField campoNombre, campoApellido, campoCorreo;
    private JTextField campoCapacidades, campoTarifas;
    private JList<Asignatura> listaAsignaturas;
    private JList<Dia> listaDias;
    private JList<Horario> listaHorarios;

    /**
     * Inicializa el panel con los componentes necesarios para el registro.
     */
    public PanelAgregarProfesorAdmin(){
        super();
        setBackground(new Color(30, 30, 30));
        setVisible(true);
        setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));

        InterfazUtils.agregarTitulo("Agregar Profesor", this);
        add(Box.createRigidArea(new Dimension(0, 40)));
        texto();
        add(Box.createRigidArea(new Dimension(0, 30)));
        botones();
        add(Box.createRigidArea(new Dimension(0, 10)));
        repaint();
        revalidate();
    }

    /**
     * Agrega los campos de entrada para los datos del Profesor, incluyendo nombre, correo, asignaturas, etc.
     */
    private void texto(){
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(10, 2, 5, 5));
        panel.setOpaque(false);
        Font fuente = new Font("Arial", Font.BOLD, 20);

        campoNombre = new JTextField();
        campoApellido = new JTextField();
        campoCorreo = new JTextField();
        campoCapacidades = new JTextField();
        campoTarifas = new JTextField();

        listaAsignaturas = new JList<>(Asignatura.values());
        listaAsignaturas.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);

        listaDias = new JList<>(Dia.values());
        listaDias.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);

        listaHorarios = new JList<>(Horario.values());
        listaHorarios.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);

        panel.add(InterfazUtils.label("Nombre:", fuente)); panel.add(campoNombre);
        panel.add(InterfazUtils.label("Apellido:", fuente)); panel.add(campoApellido);
        panel.add(InterfazUtils.label("Correo electrónico:", fuente)); panel.add(campoCorreo);
        panel.add(InterfazUtils.label("Capacidad Máxima de Alumnos (separado por comas):", fuente)); panel.add(campoCapacidades);
        panel.add(InterfazUtils.label("Tarifas (separado por comas):", fuente)); panel.add(campoTarifas);
        panel.add(InterfazUtils.label("Asignaturas (Seleccionar Multiples con CTRL):", fuente)); panel.add(new JScrollPane(listaAsignaturas));
        panel.add(InterfazUtils.label("Días disponibles (Seleccionar Multiples con CTRL):", fuente)); panel.add(new JScrollPane(listaDias));
        panel.add(InterfazUtils.label("Horarios disponibles (Seleccionar Multiples con CTRL):", fuente)); panel.add(new JScrollPane(listaHorarios));

        panel.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.add(panel);
    }

    /**
     * Agrega los botones para registrar al Profesor o volver al menú principal, adémas, el botón para registrar al Profesor
     * hace todas las validaciones necesarias para asegurarse que la información que se ingresa es correcta.
     */
    private void botones() {
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());
        panel.setOpaque(false);

        JButton botonRegistrar = new JButton("Registrar");
        botonRegistrar.setPreferredSize(new Dimension(250, 40));

        panel.add(botonRegistrar);
        add(panel);

        botonRegistrar.addActionListener((ActionEvent _) -> {
            String nombre = campoNombre.getText();
            String apellido = campoApellido.getText();
            String correo = campoCorreo.getText();
            String id = "p" + UUID.randomUUID();

            if (nombre.isEmpty() || apellido.isEmpty() || correo.isEmpty()){JOptionPane.showMessageDialog(null, "Nombre, Apellido y Correo no pueden estar vacíos.", "Error!", JOptionPane.ERROR_MESSAGE);return;}

            if (listaAsignaturas.getSelectedValuesList().isEmpty()){JOptionPane.showMessageDialog(null, "seleccione al menos una asignatura.", "Error!", JOptionPane.ERROR_MESSAGE);return;}
            if (listaDias.getSelectedValuesList().isEmpty()){JOptionPane.showMessageDialog(null, "seleccione al menos un día.", "Error!", JOptionPane.ERROR_MESSAGE);return;}
            if (listaHorarios.getSelectedValuesList().isEmpty()){JOptionPane.showMessageDialog(null, "seleccione al menos un horario.", "Error!", JOptionPane.ERROR_MESSAGE); return;}

            Set<Asignatura> asignaturas = new HashSet<>(listaAsignaturas.getSelectedValuesList());
            Set<Dia> dias = new HashSet<>(listaDias.getSelectedValuesList());
            Set<Horario> horarios = new HashSet<>(listaHorarios.getSelectedValuesList());

            Set<BloqueHorario> disponibilidad = new HashSet<>();
            for (Dia dia : dias) {
                for (Horario horario : horarios) {
                    disponibilidad.add(new BloqueHorario(dia, horario));
                }
            }

            Set<Integer> capacidades = new HashSet<>();
            try {
                String[] partes = campoCapacidades.getText().split(",");
                for (String parte : partes) {
                    capacidades.add(Integer.parseInt(parte.trim()));
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Las capacidades máximas deben ser números enteros separados por comas.", "Error!", JOptionPane.ERROR_MESSAGE);
                return;
            }

            Set<Long> tarifas = new HashSet<>();
            try {
                String[] partes = campoTarifas.getText().split(",");
                for (String parte : partes) {
                    tarifas.add(Long.parseLong(parte.trim()));
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Las tarifas deben ser números enteros separados por comas.", "Error!", JOptionPane.ERROR_MESSAGE);
                return;
            }

            Profesor nuevo = new Profesor(nombre, apellido, correo, id, capacidades, tarifas, asignaturas, disponibilidad);
            ProfesorFactory.agregarProfesor(nuevo);

            JOptionPane.showMessageDialog(null, "Profesor registrado con éxito.", "Registro exitoso", JOptionPane.INFORMATION_MESSAGE);
            Ventana.irA(new PanelCrearEliminarPerfil());
        });
    }
}