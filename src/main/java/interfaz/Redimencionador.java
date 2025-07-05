package interfaz;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.net.URL;

/**
 * Ayuda a redimencionar imagenes para usarlas despues
 */
public class Redimencionador {

    public Redimencionador(){};

    public static ImageIcon red(String ruta, int porciento) {
        URL url = Redimencionador.class.getResource(ruta);
        ImageIcon imagne = new ImageIcon(url);
        Image imagen = imagne.getImage();
        int y = imagen.getHeight(null);
        int x = imagen.getWidth(null);
        double factorEscala = (double) porciento / 100.0;

        int nuevoAncho = (int) (x * factorEscala);
        int nuevoAlto = (int) (y * factorEscala);

        Image imagenFinal = imagen.getScaledInstance(nuevoAncho, nuevoAlto, Image.SCALE_SMOOTH);
        ImageIcon retorna = new ImageIcon(imagenFinal);
        System.out.println(retorna);
        return retorna;
    }
}
