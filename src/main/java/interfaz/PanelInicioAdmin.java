package interfaz;

import javax.swing.*;
import java.awt.*;

/**
 * Panel que permite al administrador iniciar sesión.
 */
public class PanelInicioAdmin extends JPanel {
    private static final String password = "a";
    private static final String usuario = "a";

    private JPasswordField campoPassword;
    private JTextField campoUsuario;
    private boolean mensajeErrorMostrado = false;

    /**
     * Inicializa el panel con los componentes necesarios para el inicio de sesión.
     */
    public PanelInicioAdmin(){
        setBackground(new Color(30, 30, 30));
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        InterfazUtils.agregarTitulo("Ingresar Como Admin", this);
        add(Box.createRigidArea(new Dimension(0, 100)));

        agregarTexto();
        add(Box.createRigidArea(new Dimension(0, 160)));

        agregarBotones();
        add(Box.createRigidArea(new Dimension(0, 10)));

        repaint();
        revalidate();
    }

    /**
     * Agrega los campos de texto para usuario y contraseña al panel.
     */
    private void agregarTexto(){
        JPanel panel = new JPanel(new GridLayout(5, 4));
        panel.setOpaque(false);

        Font fuente = new Font("Arial", Font.BOLD, 25);

        campoUsuario = new JTextField();
        campoPassword = new JPasswordField();

        panel.add(Box.createRigidArea(new Dimension(350, 50)));
        panel.add(Box.createRigidArea(new Dimension(350, 50)));
        panel.add(Box.createRigidArea(new Dimension(350, 50)));
        panel.add(Box.createRigidArea(new Dimension(350, 50)));

        panel.add(InterfazUtils.label("Usuario:", fuente));
        panel.add(campoUsuario);

        panel.add(Box.createRigidArea(new Dimension(350, 50)));
        panel.add(Box.createRigidArea(new Dimension(350, 50)));
        panel.add(Box.createRigidArea(new Dimension(350, 50)));
        panel.add(Box.createRigidArea(new Dimension(350, 50)));

        panel.add(InterfazUtils.label("Contraseña:", fuente));
        panel.add(campoPassword);

        panel.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(panel);
    }

    /**
     * Agrega los botones de iniciar sesión y salir.
     */
    private void agregarBotones(){
        JPanel panelBotones = new JPanel(new FlowLayout());
        panelBotones.setOpaque(false);

        JButton botonIniciar = new JButton("Iniciar Sesión");
        botonIniciar.setPreferredSize(new Dimension(250, 40));
        panelBotones.add(botonIniciar);

        JButton botonSalida = new JButton("Salir");
        botonSalida.setPreferredSize(new Dimension(250, 40));
        panelBotones.add(botonSalida);

        add(panelBotones);
        botonIniciar.addActionListener(_ -> verificarCredenciales(panelBotones));
        botonSalida.addActionListener(_ -> Ventana.principal());
    }

    /**
     * Verifica las credenciales ingresadas por el usuario, si son correctas, redirige al panel de administrador.
     * Si no, muestra un mensaje de error una sola vez.
     * @param panelBotones El panel donde se agrega el mensaje de error.
     */
    private void verificarCredenciales(JPanel panelBotones) {
        String usuarioIngresado = campoUsuario.getText();
        String contrasenaIngresada = campoPassword.getText();

        if (usuario.equals(usuarioIngresado) && password.equals(contrasenaIngresada)) {
            Ventana.admin();
        } else if (!mensajeErrorMostrado) {
            JLabel errorLabel = InterfazUtils.label("Campos Incorrectos", new Font("Arial", Font.BOLD, 15));
            errorLabel.setForeground(Color.RED);
            panelBotones.add(errorLabel);
            panelBotones.repaint();
            panelBotones.revalidate();
            Ventana.refrescar();
            mensajeErrorMostrado = true;
        }
    }
}