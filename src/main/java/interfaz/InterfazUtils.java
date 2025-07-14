package interfaz;

import javax.swing.*;
import java.awt.*;

public class InterfazUtils {

    /**
     * Agrega el título principal al panel.
     */
    public static void agregarTitulo(String titulo, JPanel panel){
        Font fuente = new Font("Arial", Font.BOLD, 80);
        JLabel title = new JLabel(titulo);
        title.setForeground(Color.white);
        title.setFont(fuente);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(title);
    }

    /**
     * Crea un JLabel dado el texto a mostrar y su fuente.
     * @return el JLabel.
     */
    public static JLabel label(String texto, Font fuente) {
        JLabel l = new JLabel(texto);
        l.setFont(fuente);
        l.setForeground(Color.GRAY);
        return l;
    }

    /**
     * Añade botones con icono al panel especificado.
     * @param ubicacion ruta de la imagen del botón.
     * @param panel panel donde se añade el botón.
     * @param porciento porcentaje para determinar el tamaño.
     * @return el botón.
     */
    public static JButton addBotonesConIcono(String ubicacion, JPanel panel, int porciento){
        JButton boton = new JButton();
        boton.setIcon(Redimencionador.red(ubicacion, porciento));
        boton.setAlignmentX(Component.CENTER_ALIGNMENT);
        boton.setContentAreaFilled(false);
        boton.setBorderPainted(false);
        boton.setFocusPainted(false);
        boton.setOpaque(false);
        panel.add(boton);
        return boton;
    }
}