package interfaz;

import javax.swing.*;

public class Ventana extends JFrame {
    private static JFrame instancia = null;
    private Ventana(){
        this.setSize(1280,720);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        this.setResizable(false);
        this.setTitle("Administrador de Clases");
    }
    public static JFrame getInstancia(){
        if(instancia != null){
            return instancia;
        }
        else{
            instancia = new Ventana();
            return instancia;
        }
    }
}

