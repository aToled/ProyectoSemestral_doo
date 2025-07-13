package interfaz;

import javax.swing.*;

public class Ventana extends JFrame {
    private static JFrame instancia = null;
    private static JPanel panel;
    private Ventana(){
        this.setSize(1280,720);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        this.setResizable(false);
        this.setTitle("Administrador de Clases");
        panel = new PanelEntrada();
        this.add(panel);
        panel.setVisible(true);
        this.revalidate();
        this.repaint();
    }
    public static JFrame getInstancia(){
        if (instancia == null) {
            instancia = new Ventana();
        }
        return instancia;
    }
    protected static void admin(){
        instancia.remove(panel);
        panel = new PanelAdmin();
        instancia.add(panel);
        instancia.repaint();
        instancia.revalidate();
    }

    protected static void estudiante(){
        instancia.remove(panel);
        panel = new PanelEstudiante();
        instancia.add(panel);
        instancia.repaint();
        instancia.revalidate();
    }

    protected static void estudianteRegistro(){
        instancia.remove(panel);
        panel = new PanelRegistroEstudiante();
        instancia.add(panel);
        instancia.repaint();
        instancia.revalidate();
    }
    protected static void estudianteInicio(){
        instancia.remove(panel);
        panel = new PanelInicioEstudiante();
        instancia.add(panel);
        instancia.repaint();
        instancia.revalidate();
    }
    protected static void principal(){
        instancia.remove(panel);
        panel = new PanelEntrada();
        instancia.add(panel);
        instancia.repaint();
        instancia.revalidate();
    }
    protected static void modificacioEstudiante(){
        instancia.remove(panel);
        panel = new PanelSolicitudesEstudiante();
        instancia.add(panel);
        instancia.repaint();
        instancia.revalidate();
    }
    protected static void solicitudEstudiante(){
        instancia.remove(panel);
        panel = new AgregarSolicitudEstudiante();
        instancia.add(panel);
        instancia.repaint();
        instancia.revalidate();
    }
    protected static void adminInicio(){
        instancia.remove(panel);
        panel = new PanelInicioAdmin();
        instancia.add(panel);
        instancia.repaint();
        instancia.revalidate();
    }
    protected static void perfil(){
        instancia.remove(panel);
        panel = new PanelPerfil();
        instancia.add(panel);
        instancia.repaint();
        instancia.revalidate();
    }
    protected static void agregarEstudiante() {
        instancia.remove(panel);
        panel = new PanelAgregarEstudianteAdmin();
        instancia.add(panel);
        instancia.repaint();
        instancia.revalidate();
    }
    protected static void agregarProfesor() {
        instancia.remove(panel);
        panel = new PanelAgregarProfesorAdmin();
        instancia.add(panel);
        instancia.repaint();
        instancia.revalidate();
    }
    protected static void refrescar(){
        instancia.repaint();
        instancia.revalidate();
    }

}

