package interfaz;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

/**
 * Ayuda a redimensionar imágenes para usarlas después.
 */
public class Redimencionador {
    public static ImageIcon red(String ruta, int porciento) {
        URL url = Redimencionador.class.getResource(ruta);
        assert url != null;
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
