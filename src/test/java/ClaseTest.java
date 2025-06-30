import org.junit.jupiter.api.*;
import Excepciones.*;
import Enums.*;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class ClaseTest {
    private Profesor profLen;
    private Clase claseLen;
    private Estudiante eLen1, eLen2, eLen3, eLen4, eMat1;
    private BloqueHorario Jueves10;

    @BeforeEach
    void setUp(){
        Jueves10 = new BloqueHorario(Dia.JUEVES, Horario.BLOQUE10);
        profLen = new Profesor("Prof", "Leng", "hola@gmail.com", "14", 3, 19999, Set.of(Asignatura.LENGUAJE), Set.of(Jueves10));
        claseLen = new Clase(profLen, "1465", Asignatura.LENGUAJE, Jueves10);
        eLen1 = new Estudiante("A", "B", "a@b.com", "1", Set.of(Asignatura.LENGUAJE));
        eLen2 = new Estudiante("C", "D", "c@d.com", "2", Set.of(Asignatura.LENGUAJE));
        eLen3 = new Estudiante("E", "F", "e@f.com", "3", Set.of(Asignatura.LENGUAJE));
        eLen4 = new Estudiante("G", "H", "g@h.com", "4", Set.of(Asignatura.LENGUAJE));
        eMat1 = new Estudiante("I", "J", "i@j.com", "5", Set.of(Asignatura.MATEMATICA));
    }

    @DisplayName("Tests relacionados a manejo de estudiantes: ")
    @Test
    void testLimiteDeEstudiantes(){
        assertTrue(claseLen.agregarEstudiante(eLen1));
        assertTrue(claseLen.agregarEstudiante(eLen2));
        assertTrue(claseLen.agregarEstudiante(eLen3));
        assertFalse(claseLen.agregarEstudiante(eLen4), "El profesor que cursa esta clase acepta como maximo a 3 estudiantes");
    }

    @Test
    void testAsignaturaIncompatible(){
        assertFalse(claseLen.agregarEstudiante(eMat1), "Estudiante con tipo de Asignatura incompatible");
    }

    @Test
    void testCantidadDeEstudiantes(){
        assertEquals(0, claseLen.cantidadEstudiantes(), "Clase vacia");
        assertTrue(claseLen.agregarEstudiante(eLen1));
        assertEquals(1, claseLen.cantidadEstudiantes(), "Clase con 1 Estudiante");
        claseLen.eliminarEstudiante("1");
        assertEquals(0, claseLen.cantidadEstudiantes(), "Vacia de nuevo");
    }

    @DisplayName("Tests relacionados a manejo de profesores: ")
    @Test
    void testProfesorNoDictaMateriaException(){
        assertThrows(ProfesorNoDictaMateriaException.class, ()-> new Clase(profLen, "1001", Asignatura.HISTORIA, Jueves10), "El profesor no dicta historia");
    }

    @Test
    void testProfesorNoDisponibleException(){
        BloqueHorario Lunes16 = new BloqueHorario(Dia.LUNES, Horario.BLOQUE12);
        assertThrows(ProfesorNoDisponibleException.class, ()-> new Clase(profLen, "1123", Asignatura.LENGUAJE, Lunes16), "El profesor no esta disponible en ese horario");
    }
}
