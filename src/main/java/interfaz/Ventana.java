package interfaz;

import Logica.Identificables.Estudiante;
import javax.swing.*;
import java.util.Stack;

/**
 * Crea la ventana del programa y hace manejo de los paneles
 */
public class Ventana extends JFrame {
    private static JFrame instancia = null;
    private static JPanel panel;
    private static final Stack<JPanel> historial = new Stack<>();
    private static Estudiante estudianteActual = null;

    public Ventana(){
        instancia = this;
        setSize(1280,720);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        setResizable(false);
        setTitle("Administrador de Clases");

        irA(new PanelEntrada());
    }

    public static void irA(JPanel nuevoPanel) {
        if (panel != null) historial.push(panel);
        cambiarPanel(nuevoPanel);
    }

    private static void cambiarPanel(JPanel nuevoPanel) {
        if (panel != null) instancia.remove(panel);
        panel = nuevoPanel;
        instancia.add(panel);
        instancia.revalidate();
        instancia.repaint();
    }

    public static void volverAtras() {
        if (!historial.isEmpty()) {
            cambiarPanel(historial.pop());
        }
    }

    public static void cerrarSesion() {
        historial.clear();
        estudianteActual = null;
        cambiarPanel(new PanelEntrada());
    }

    public static void refrescar(){
        instancia.repaint();
        instancia.revalidate();
    }

    public static void setEstudianteActual(Estudiante e) {estudianteActual = e;}
    public static Estudiante getEstudianteActual() {return estudianteActual;}
}