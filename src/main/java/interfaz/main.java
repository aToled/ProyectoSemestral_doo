package interfaz;

import javax.swing.*;

/**
 * Se agrega la Ventana,
 * todo el manejo se hace detras
 */
public class main {
    public static void main(String[] args){

        JFrame frame = Ventana.getInstancia();
        frame.repaint();
    }
}
