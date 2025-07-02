import Logica.BloqueHorario;
import Logica.Enums.Asignatura;
import Logica.Enums.Dia;
import Logica.Enums.Horario;
import Logica.Profesor;
import Logica.ProfesorFactory;
import org.junit.jupiter.api.Test;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class ProfesorFactoryTest {

    @Test
    void crearProfesorValido() {
        assertDoesNotThrow(() ->
               ProfesorFactory.crearProfesor("A", "B", "AB@gmail.com", "1", 2, 10000, Set.of(Asignatura.HISTORIA), Set.of(new BloqueHorario(Dia.LUNES, Horario.BLOQUE1))));
    }

    @Test
    void crearProfesorCapacidadCero() {
        assertThrows(IllegalArgumentException.class, () ->
                ProfesorFactory.crearProfesor("A", "B", "AB@gmail.com", "1", 0, 10000, Set.of(Asignatura.INGLES), Set.of(new BloqueHorario(Dia.MARTES, Horario.BLOQUE2))));

    }

    @Test
    void crearProfesorSinMaterias() {
        assertThrows(IllegalArgumentException.class, () ->
                ProfesorFactory.crearProfesor("A", "B", "AB@gmail.com", "1", 2, 10000, Set.of(), Set.of(new BloqueHorario(Dia.MIERCOLES, Horario.BLOQUE1))));

    }

    @Test
    void crearProfesorSinDisponibilidad() {
        assertThrows(IllegalArgumentException.class, () ->
                ProfesorFactory.crearProfesor("A", "B", "AB@gmail.com", "1", 2, 10000, Set.of(Asignatura.CIENCIAS), Set.of()));

    }
    @Test
    public void testAgregarProfesor() {
        Profesor p = ProfesorFactory.crearProfesor("Profesor", "Test", "ptest@gmail.com", "-1", 3, 25000, Set.of(Asignatura.CIENCIAS), Set.of(new BloqueHorario(Dia.VIERNES, Horario.BLOQUE1)));
        ProfesorFactory.agregarProfesor(p);
        Set<Profesor> lista = ProfesorFactory.cargarProfesores();
        assertTrue(lista.contains(p));
        ProfesorFactory.eliminarProfesor("-1");
    }
}