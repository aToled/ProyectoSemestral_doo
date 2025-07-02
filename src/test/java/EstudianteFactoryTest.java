import Logica.Estudiante;
import Logica.EstudianteFactory;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class EstudianteFactoryTest {

    @Test
    void crearEstudianteValido() {
        assertDoesNotThrow(() ->
                EstudianteFactory.crearEstudiante("A", "B", "AB@gmail.com", "1"));
    }

    @Test
    public void testAgregarEstudiante() throws IOException {
        Estudiante e = EstudianteFactory.crearEstudiante("Estudiante", "Test", "ptest@gmail.com", "-1");
        EstudianteFactory.agregarEstudiante(e);
        List<Estudiante> lista = EstudianteFactory.cargarEstudiantes();
        assertTrue(lista.contains(e));
        EstudianteFactory.eliminarEstudiante("-1");
    }
}