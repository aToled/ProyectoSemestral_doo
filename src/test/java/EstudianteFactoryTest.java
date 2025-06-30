import Logica.Enums.Asignatura;
import Logica.Estudiante;
import Logica.EstudianteFactory;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class EstudianteFactoryTest {

    @Test
    void crearEstudianteValido() {
        assertDoesNotThrow(() ->
                EstudianteFactory.crearEstudiante("A", "B", "AB@gmail.com", "1", Set.of(Asignatura.LENGUAJE, Asignatura.MATEMATICA, Asignatura.CIENCIAS)));
    }


    @Test
    void crearEstudianteSinMaterias() {
        assertThrows(IllegalArgumentException.class, () ->
                EstudianteFactory.crearEstudiante("A", "B", "AB@gmail.com", "1", Set.of()));
    }

    @Test
    public void testAgregarEstudiante() throws IOException {
        Estudiante e = EstudianteFactory.crearEstudiante("Estudiante", "Test", "ptest@gmail.com", "-1", Set.of(Asignatura.CIENCIAS));
        EstudianteFactory.agregarEstudiante(e);
        List<Estudiante> lista = EstudianteFactory.cargarEstudiantes();
        assertTrue(lista.contains(e));
        EstudianteFactory.eliminarEstudiante("-1");
    }
}