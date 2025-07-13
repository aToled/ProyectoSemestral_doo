package interfaz;

import Logica.Enums.Dia;

import javax.swing.*;
import java.awt.*;

public class PanelCalendario extends JPanel {

    String[] dias = {"Lunes","Martes","Miercoles","Jueves","Viernes"};

    public PanelCalendario(){
        setLayout(new FlowLayout());
        titulo();
        cuerpo();
    }

    private void cuerpo() {
        //JTable = new JTable(null, dias);
    }

    private void titulo(){
        Font fuente = new Font("Arial", Font.BOLD, 70);
        JLabel title = new JLabel("Sistema de clases Particulares");
        title.setForeground(Color.white);
        title.setFont(fuente);
        this.add(title);
        title.setVisible(true);
    }
}
