package interfaz;

import Logica.Enums.Asignatura;
import Logica.Enums.Horario;
import Logica.Estudiante;
import Logica.EstudianteFactory;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Set;

public class PanelPerfil extends JPanel {
    JPanel panel;
    int valor = 0;
    private JComboBox<String> comboEstudiantes;

    private final String dias[] = {"LUNES","MARTES","MIERCOLES","JUEVES","VIERNES"};

    public PanelPerfil(){

        this.setBackground(Color.black);
        this.setVisible(true);
        this.setLayout(new GridLayout(0,2));
        this.titulo("Eliminar");
        this.titulo("Agregar");

        this.eliminar();
        this.cualAgregar();

        this.repaint();
        this.revalidate();

    }

    private void titulo(String texto){
        Font fuente = new Font("Arial", Font.BOLD, 60);
        JLabel title = new JLabel(texto);
        title.setForeground(Color.white);
        title.setFont(fuente);
        this.add(title);
        title.setVisible(true);
    }

    private void eliminar(){
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panel.setOpaque(false);
        comboEstudiantes = new JComboBox<String>();
        comboEstudiantes.setPreferredSize(new Dimension(250,30));
        panel.add(comboEstudiantes);

        cargarEstudiantesEnComboBox();

        JButton boton = new JButton("Eliminar");
        panel.add(boton);
        add(panel);

        boton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // --- Solo esta parte la necesitas agregar o reemplazar en tu ActionListener ---
                String itemSeleccionado = (String) comboEstudiantes.getSelectedItem();

                if (itemSeleccionado != null && !itemSeleccionado.isEmpty() && !itemSeleccionado.equals("No hay estudiantes para eliminar")) {
                    String idAEliminar = null;
                    int inicio = itemSeleccionado.indexOf("(ID: ") + 4;
                    int termino = itemSeleccionado.indexOf(")", inicio);

                    if (inicio != -1 && termino != -1) {
                        idAEliminar = itemSeleccionado.substring(inicio, termino);
                    } else {
                        JOptionPane.showMessageDialog(PanelPerfil.this,
                                "No se pudo extraer el ID del estudiante seleccionado.",
                                "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }

                    int confirm = JOptionPane.showConfirmDialog(PanelPerfil.this,
                            "¿Confirmas que deseas eliminar a " + itemSeleccionado + "?",
                            "Confirmar Eliminación", JOptionPane.YES_NO_OPTION);

                    if (confirm == JOptionPane.YES_OPTION) {
                        EstudianteFactory.eliminarEstudiante(idAEliminar);
                        JOptionPane.showMessageDialog(PanelPerfil.this,
                                "Estudiante eliminado exitosamente.", "Exito", JOptionPane.INFORMATION_MESSAGE);
                        cargarEstudiantesEnComboBox(); // Recargar el JComboBox para actualizar la lista
                    }
                } else {
                    JOptionPane.showMessageDialog(PanelPerfil.this,
                            "No hay estudiantes seleccionados o la lista esta vacia.",
                            "Advertencia", JOptionPane.WARNING_MESSAGE);
                }
            }
        });
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

    private void cualAgregar(){
        panel = new JPanel();
        panel.setLayout(new FlowLayout());
        panel.setOpaque(false);

        Font fuente = new Font("Arial", Font.BOLD, 20);

        JRadioButton radio1=new JRadioButton("Profesor");
        radio1.setFont(fuente);
        radio1.setForeground(Color.white);
        radio1.setOpaque(false);
        radio1.addItemListener(e -> {
            if (radio1.isSelected()) {
                Ventana.agregarProfesor();
            }
        });

        JRadioButton radio2 =new JRadioButton("Estudiante");
        radio2.setFont(fuente);
        radio2.setForeground(Color.white);
        radio2.setOpaque(false);
        radio2.addItemListener(e -> {
            if (radio2.isSelected()) {
                Ventana.agregarEstudiante();
            }
        });
        panel.add(radio1);
        panel.add(radio2);
        add(panel);
    }
}