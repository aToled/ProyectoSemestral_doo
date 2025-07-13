package Logica;

import org.junit.jupiter.api.*;
import Logica.Enums.*;
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
        profLen = new Profesor("Prof", "Len", "hola@gmail.com", "14", Set.of(3), Set.of(19999L), Set.of(Asignatura.LENGUAJE), Set.of(Jueves10));
        claseLen = new Clase(profLen, "1465", Asignatura.LENGUAJE, Jueves10, 3, 19999);
        eLen1 = new Estudiante("A", "B", "a@b.com", "1");
        eLen1.addMateriasInteres(Asignatura.LENGUAJE);
        eLen2 = new Estudiante("C", "D", "c@d.com", "2");
        eLen2.addMateriasInteres(Asignatura.LENGUAJE);
        eLen3 = new Estudiante("E", "F", "e@f.com", "3");
        eLen3.addMateriasInteres(Asignatura.LENGUAJE);
        eLen4 = new Estudiante("G", "H", "g@h.com", "4");
        eLen4.addMateriasInteres(Asignatura.LENGUAJE);
        eMat1 = new Estudiante("I", "J", "i@j.com", "5");
        eMat1.addMateriasInteres(Asignatura.MATEMATICA);
    }

    @DisplayName("Tests relacionados a manejo de estudiantes: ")
    @Test
    void testLimiteDeEstudiantes(){
        assertTrue(claseLen.agregarEstudiante(eLen1));
        assertTrue(claseLen.agregarEstudiante(eLen2));
        assertTrue(claseLen.agregarEstudiante(eLen3));
        assertFalse(claseLen.agregarEstudiante(eLen4), "El profesor que cursa esta clase acepta como máximo a 3 estudiantes");
    }

    @Test
    void testAsignaturaIncompatible(){
        assertFalse(claseLen.agregarEstudiante(eMat1), "Estudiante con tipo de Asignatura incompatible");
    }

    @Test
    void testCantidadDeEstudiantes(){
        assertEquals(0, claseLen.cantidadEstudiantes(), "Clase vacía");
        assertTrue(claseLen.agregarEstudiante(eLen1));
        assertEquals(1, claseLen.cantidadEstudiantes(), "Clase con 1 Estudiante");
        claseLen.eliminarEstudiante("1");
        assertEquals(0, claseLen.cantidadEstudiantes(), "Vacía de nuevo");
    }

    @Test
    void testAgregarEstudianteDuplicado(){
        assertTrue(claseLen.agregarEstudiante(eLen1));
        assertFalse(claseLen.agregarEstudiante(eLen1));
        assertEquals(1, claseLen.cantidadEstudiantes());
    }

    @Test
    void testEliminarEstudianteInexistente() {
        claseLen.eliminarEstudiante("-1");
        assertEquals(0, claseLen.cantidadEstudiantes());
    }

    @DisplayName("Tests relacionados a manejo de profesores: ")
    @Test
    void testProfesorNoDictaMateriaException(){
        assertThrows(IllegalArgumentException.class, ()-> new Clase(profLen, "1001", Asignatura.HISTORIA, Jueves10, 3, 19999), "El profesor no dicta historia");
    }

    @Test
    void testProfesorNoDisponibleException(){
        BloqueHorario Lunes16 = new BloqueHorario(Dia.LUNES, Horario.BLOQUE12);
        assertThrows(IllegalArgumentException.class, ()-> new Clase(profLen, "1123", Asignatura.LENGUAJE, Lunes16, 3, 19999), "El profesor no esta disponible en ese horario");
    }

    @Test
    void testProfesorNoAceptaCanitdadEstudiantesException(){
        assertThrows(IllegalArgumentException.class, ()-> new Clase(profLen, "1123", Asignatura.LENGUAJE, Jueves10, 10, 19999), "El profesor solo acepta hasta 3 Estudiantes");
    }
    @Test
    void testProfesorNoAceptaTarifaException(){
        assertThrows(IllegalArgumentException.class, ()-> new Clase(profLen, "1123", Asignatura.LENGUAJE, Jueves10, 3, 23332), "El profesor cobra 19999$ por clase");
    }
}