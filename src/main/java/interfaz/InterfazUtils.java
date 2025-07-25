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

    /**
     * Extrae el ID desde un ítem con formato "(ID: XXXXX)".
     * @param item texto del ítem
     * @return el ID como cadena o null si no se encuentra.
     */
    public static String extractIdFromComboBoxItem(String item) {
        int startIndex = item.indexOf("(ID: ") + 4;
        int endIndex = item.indexOf(")", startIndex);
        if (endIndex != -1 && endIndex > startIndex) {
            return item.substring(startIndex, endIndex);
        }
        return null;
    }

    public static JCheckBox crearCheckbox(String texto, Font fuente, java.util.function.Consumer<Boolean> onToggle) {
        JCheckBox check = new JCheckBox(texto);
        check.setFont(fuente);
        check.setForeground(Color.WHITE);
        check.setOpaque(false);
        check.addActionListener(_ -> onToggle.accept(check.isSelected()));
        return check;
    }
}