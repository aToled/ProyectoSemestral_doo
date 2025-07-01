import interfaz.Ventana;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class VentanaTest {
    @Test
    public void sigletonVentanatest(){
        Assertions.assertEquals(Ventana.getInstancia(), Ventana.getInstancia());
    }
}
