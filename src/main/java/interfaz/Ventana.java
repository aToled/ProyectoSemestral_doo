package interfaz;

import Logica.Estudiante;

import javax.swing.*;

/**
 * Crea la ventana del programa y hace manejo de los paneles
 */
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

    /**
     * Crea o da la instancia, ya que se trabaja con un singleton
     * @return instancia del objeto
     */
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
    protected static void solicitudEstudiante(Estudiante estudiante){
        instancia.remove(panel);
        panel = new AgregarSolicitudEstudiante(estudiante);
        instancia.add(panel);
        instancia.repaint();
        instancia.revalidate();
    }
    protected static void calendario(){
        instancia.remove(panel);
        panel = new PanelCalendario();
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
    protected static void solicitudes() {
        instancia.remove(panel);
        panel = new PanelSolicitudesAdmin();
        instancia.add(panel);
        instancia.repaint();
        instancia.revalidate();
    }
    protected static void preferencias(Estudiante estudiante) {
        instancia.remove(panel);
        panel = new PanelPreferencias(estudiante);
        instancia.add(panel);
        instancia.repaint();
        instancia.revalidate();
    }
    protected static void seleccionarClase(Estudiante estudiante) {
        instancia.remove(panel);
        panel = new PanelEnviarSolicitudEstudiante(estudiante);
        instancia.add(panel);
        instancia.repaint();
        instancia.revalidate();
    }
    protected static void refrescar(){
        instancia.repaint();
        instancia.revalidate();
    }
}