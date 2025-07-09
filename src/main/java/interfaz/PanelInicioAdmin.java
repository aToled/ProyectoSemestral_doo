package interfaz;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PanelInicioAdmin extends JPanel {
    private final String contrasena = "admin123";
    private final String usuario = "user";
    private JPasswordField password;
    private JTextField field;
    private boolean incorrecto = false;

    public PanelInicioAdmin(){
        this.setBackground(Color.black);
        this.setVisible(true);
        this.setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
        this.titulo();
        add(Box.createRigidArea(new Dimension(0,100)));


        this.texto();
        add(Box.createRigidArea(new Dimension(0,160)));

        this.botones();

        add(Box.createRigidArea(new Dimension(0,10)));


        this.repaint();
        this.revalidate();
    }
    private void titulo(){
        Font fuente = new Font("Arial", Font.BOLD, 80);
        JLabel title = new JLabel("Ingresar Como Admin");
        title.setForeground(Color.white);
        title.setFont(fuente);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.add(title);
        title.setVisible(true);
    }
    private void texto(){
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(5,4));
        JLabel nombre = new JLabel("Usuario:");
        Font fuente = new Font("Arial", Font.BOLD, 20);
        nombre.setFont(fuente);
        JLabel apellido = new JLabel("Contraseña:");
        apellido.setFont(fuente);

        field = new JTextField();
        password = new JPasswordField();


        nombre.setForeground(Color.GRAY);
        apellido.setForeground(Color.GRAY);

        panel.add(Box.createRigidArea(new Dimension(350,50)));
        panel.add(Box.createRigidArea(new Dimension(350,50)));
        panel.add(Box.createRigidArea(new Dimension(350,50)));
        panel.add(Box.createRigidArea(new Dimension(350,50)));
        panel.add(nombre);
        panel.add(field);
        panel.add(Box.createRigidArea(new Dimension(350,50)));
        panel.add(Box.createRigidArea(new Dimension(350,50)));
        panel.add(Box.createRigidArea(new Dimension(350,50)));
        panel.add(Box.createRigidArea(new Dimension(350,50)));
        panel.add(apellido);
        panel.add(password);


        panel.setOpaque(false);

        panel.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.add(panel);
    }
    public void botones(){
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());
        panel.setOpaque(false);

        JButton boton1 = new JButton("Iniciar Sesion");
        boton1.setPreferredSize(new Dimension(250,40));
        panel.add(boton1);

        JButton botonSalida = new JButton("Salir");
        botonSalida.setPreferredSize(new Dimension(250,40));
        panel.add(botonSalida);

        add(panel);

        boton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                System.out.println(usuario.equals(field.getText()));
                System.out.println(contrasena.equals(password.getText()));

                if(usuario.equals(field.getText()) && contrasena.equals(password.getText())){
                    Ventana.admin();
                }
                else{
                    if(incorrecto == false) {
                        JLabel label = new JLabel("Campos Incorrectos");
                        Font fuente = new Font("Arial", Font.BOLD, 15);
                        label.setFont(fuente);
                        label.setForeground(Color.RED);
                        panel.add(label);
                        panel.repaint();
                        panel.revalidate();
                        Ventana.refrescar();
                        incorrecto = true;
                    }
                }
            }
        });
        botonSalida.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Ventana.principal();
            }
        });
    }
}
