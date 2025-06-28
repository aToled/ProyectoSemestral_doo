import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CalendarioTest {
    @Test
    public void testSigleton(){
        Assertions.assertNotNull(Calendario.getInstancia());
        Assertions.assertEquals(Calendario.getInstancia(),Calendario.getInstancia());
    }
}
