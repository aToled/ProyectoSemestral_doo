package Logica;

import org.junit.jupiter.api.Test;
import java.util.Set;
import static org.junit.jupiter.api.Assertions.*;

class EstudianteFactoryTest {

    @Test
    void crearEstudianteValido() {
        assertDoesNotThrow(() ->
                EstudianteFactory.crearEstudiante("A", "B", "AB@gmail.com", "1"));
    }

    @Test
    public void testAgregarEstudiante() {
        Estudiante e = EstudianteFactory.crearEstudiante("Estudiante", "Test", "ptest@gmail.com", "-1");
        EstudianteFactory.agregarEstudiante(e);
        Set<Estudiante> lista = EstudianteFactory.cargarEstudiantes();
        assertTrue(lista.contains(e));
        EstudianteFactory.eliminarEstudiante("-1");
    }
}