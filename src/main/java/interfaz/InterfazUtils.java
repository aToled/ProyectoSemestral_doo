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
}