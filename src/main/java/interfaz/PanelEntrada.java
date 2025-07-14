package interfaz;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PanelEntrada extends JPanel {
    public PanelEntrada(){
        this.setBackground(new Color(30, 30, 30));
        this.setVisible(true);
        this.setLayout(new FlowLayout());
        this.titulo();
        JButton boton1 = this.addBotones("/botonAdmin.png");
        JButton boton2 = this.addBotones("/botonEstudiante.png");
        this.repaint();
        this.revalidate();

        boton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Ventana.adminInicio();
            }
        });

        boton2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Ventana.estudiante();
            }
        });
    }

    private void titulo(){
        Font fuente = new Font("Arial", Font.BOLD, 70);
        JLabel title = new JLabel("Sistema de clases Particulares");
        title.setForeground(Color.white);
        title.setFont(fuente);
        this.add(title);
        title.setVisible(true);
    }

    private JButton addBotones(String ubicacion){
        JButton boton = new JButton();
        boton.setIcon(Redimencionador.red(ubicacion, 40));
        boton.setContentAreaFilled(false);
        boton.setBorderPainted(false);
        boton.setFocusPainted(false);
        boton.setOpaque(false);
        this.add(boton);
        boton.setVisible(true);
        this.revalidate();
        this.repaint();
        return boton;
    }
}
