package interfaz;

import Logica.Estudiante;
import Logica.EstudianteFactory;
import Logica.Profesor;
import Logica.ProfesorFactory;

import javax.swing.*;
import java.awt.*;
import java.util.Set;

public class PanelPerfil extends JPanel {
    private final JComboBox<String> comboEstudiantes = new JComboBox<>();
    private final JComboBox<String> comboProfesores = new JComboBox<>();

    public PanelPerfil(){
        setBackground(Color.black);
        setLayout(new GridLayout(1, 2));

        JPanel panelEliminar = new JPanel();
        panelEliminar.setLayout(new BoxLayout(panelEliminar, BoxLayout.Y_AXIS));
        panelEliminar.setOpaque(false);
        panelEliminar.add(crearTitulo("Eliminar"));
        panelEliminar.add(Box.createRigidArea(new Dimension(0, 30)));
        panelEliminar.add(crearSeccionEliminarEstudiante());
        panelEliminar.add(Box.createRigidArea(new Dimension(0, 20)));
        panelEliminar.add(crearSeccionEliminarProfesor());

        JPanel panelAgregar = new JPanel();
        panelAgregar.setLayout(new BoxLayout(panelAgregar, BoxLayout.Y_AXIS));
        panelAgregar.setOpaque(false);
        panelAgregar.add(crearTitulo("Agregar"));
        panelAgregar.add(Box.createRigidArea(new Dimension(0, 30)));
        panelAgregar.add(crearSeccionAgregar());

        add(panelEliminar);
        add(panelAgregar);
    }

    private JLabel crearTitulo(String texto){
        JLabel title = new JLabel(texto);
        title.setFont(new Font("Arial", Font.BOLD, 60));
        title.setForeground(Color.white);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        return title;
    }

    private JPanel crearSeccionEliminarEstudiante(){
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panel.setOpaque(false);

        comboEstudiantes.setPreferredSize(new Dimension(250,30));
        panel.add(comboEstudiantes);
        cargarEstudiantesEnComboBox();

        JButton boton = new JButton("Eliminar Estudiante");
        boton.addActionListener(_ -> eliminarEstudiante());
        panel.add(boton);

        return panel;
    }

    private void eliminarEstudiante(){
        String item = (String) comboEstudiantes.getSelectedItem();

        if (item == null || item.isEmpty() || item.equals("No hay estudiantes para eliminar")) {
            JOptionPane.showMessageDialog(this, "No hay estudiantes seleccionados o la lista está vacía.", "Advertencia", JOptionPane.WARNING_MESSAGE);
            return;
        }

        int inicio = item.indexOf("(ID: ") + 5;
        int fin = item.indexOf(")", inicio);
        if (fin == -1) {
            JOptionPane.showMessageDialog(this, "No se pudo extraer el ID del estudiante seleccionado.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String id = item.substring(inicio, fin);
        int confirm = JOptionPane.showConfirmDialog(this, "¿Confirmas que deseas eliminar a " + item + "?", "Confirmar Eliminación", JOptionPane.YES_NO_OPTION);

        if (confirm == JOptionPane.YES_OPTION) {
            EstudianteFactory.eliminarEstudiante(id);
            JOptionPane.showMessageDialog(this, "Estudiante eliminado exitosamente.", "Error", JOptionPane.ERROR_MESSAGE);
            cargarEstudiantesEnComboBox(); // Recargar el JComboBox para actualizar la lista
        }
    }

    private void cargarEstudiantesEnComboBox() {
        comboEstudiantes.removeAllItems(); // Limpiar ítems existentes
        Set<Estudiante> estudiantes = EstudianteFactory.cargarEstudiantes();

        if (estudiantes != null && !estudiantes.isEmpty()) {
            for (Estudiante est : estudiantes) {
                comboEstudiantes.addItem(est.getNombre() + " " + est.getApellido() + " (ID: " + est.getId() + ")");
            }
        } else {
            comboEstudiantes.addItem("No hay estudiantes para eliminar");
        }
    }

    private JPanel crearSeccionEliminarProfesor() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panel.setOpaque(false);

        comboProfesores.setPreferredSize(new Dimension(250, 30));
        panel.add(comboProfesores);
        cargarProfesoresEnComboBox();

        JButton boton = new JButton("Eliminar Profesor");
        boton.addActionListener(_ -> eliminarProfesor());
        panel.add(boton);

        return panel;
    }

    private void eliminarProfesor() {
        String item = (String) comboProfesores.getSelectedItem();

        if (item == null || item.isEmpty() || item.equals("No hay profesores para eliminar")) {
            JOptionPane.showMessageDialog(this, "No hay profesores seleccionados o la lista está vacía.", "Advertencia", JOptionPane.WARNING_MESSAGE);
            return;
        }

        int inicio = item.indexOf("(ID: ") + 5;
        int fin = item.indexOf(")", inicio);
        if (fin == -1) {
            JOptionPane.showMessageDialog(this, "No se pudo extraer el ID del profesor seleccionado.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String id = item.substring(inicio, fin);
        int confirm = JOptionPane.showConfirmDialog(this, "¿Confirmas que deseas eliminar a " + item + "?", "Confirmar Eliminación", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            ProfesorFactory.eliminarProfesor(id);
            JOptionPane.showMessageDialog(this, "Profesor eliminado exitosamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            cargarProfesoresEnComboBox();
        }
    }

    private void cargarProfesoresEnComboBox() {
        comboProfesores.removeAllItems();
        Set<Profesor> profesores = ProfesorFactory.cargarProfesores();

        if (profesores != null && !profesores.isEmpty()) {
            for (Profesor p : profesores) {
                comboProfesores.addItem(p.getNombre() + " " + p.getApellido() + " (ID: " + p.getId() + ")");
            }
        } else {
            comboProfesores.addItem("No hay profesores para eliminar");
        }
    }

    private JPanel crearSeccionAgregar() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panel.setOpaque(false);

        Font fuente = new Font("Arial", Font.BOLD, 20);

        JRadioButton radioProfesor = new JRadioButton("Profesor");
        radioProfesor.setFont(fuente);
        radioProfesor.setForeground(Color.white);
        radioProfesor.setOpaque(false);
        radioProfesor.addItemListener(_ -> {
            if (radioProfesor.isSelected()) {
                Ventana.agregarProfesor();
            }
        });

        JRadioButton radioEstudiante = new JRadioButton("Estudiante");
        radioEstudiante.setFont(fuente);
        radioEstudiante.setForeground(Color.white);
        radioEstudiante.setOpaque(false);
        radioEstudiante.addItemListener(_ -> {
            if (radioEstudiante.isSelected()) {
                Ventana.agregarEstudiante();
            }
        });
        panel.add(radioProfesor);
        panel.add(radioEstudiante);
        return panel;
    }
}