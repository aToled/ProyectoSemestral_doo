package interfaz;

import javax.swing.*;
import java.awt.*;
import java.io.File;

public class PanelEntrada extends JPanel {
    public PanelEntrada(){
        this.setVisible(true);
        this.addBotones();
    }

    private void addBotones(){
        JButton boton = new JButton();
        boton.setIcon(Redimencionador.red("/botonAdmin.png", 20));
        this.addBotones();


    }

    @Override
    protected void paintComponent(Graphics g) {

        super.paintComponent(g);
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, getWidth(), getHeight());
    }
}
