import Logica.Calendario;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertSame;

class CalendarioTest {
    @Test
    @DisplayName("Verificar que ambas instancias de Calendario sean 'iguales'(que apunten al mismo lugar en memoria)")
    void testSingleton(){
        Calendario c1 = Calendario.getInstancia();
        Calendario c2 = Calendario.getInstancia();
        assertSame(c1, c2);
    }
}
