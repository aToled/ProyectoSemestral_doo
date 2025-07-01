package interfaz;

import javax.swing.*;

public class main {
    public static void main(String[] args){

        JFrame frame = Ventana.getInstancia();
        PanelEntrada panel = new PanelEntrada();
        frame.add(panel);
        frame.repaint();
    }
}
