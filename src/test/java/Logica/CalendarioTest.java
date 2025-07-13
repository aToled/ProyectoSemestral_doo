package Logica;

import Logica.Enums.Asignatura;
import Logica.Enums.Dia;
import Logica.Enums.Horario;
import Logica.Excepciones.ConflictoHorarioException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class CalendarioTest {
    private Calendario calendario;
    private Clase clase1, clase2, clase3;

    @BeforeEach
    void setUp() {
        calendario = Calendario.getInstancia();
        calendario.clear();

        BloqueHorario b1 = new BloqueHorario(Dia.LUNES, Horario.BLOQUE1);
        BloqueHorario b2 = new BloqueHorario(Dia.MARTES, Horario.BLOQUE2);
        BloqueHorario b3 = new BloqueHorario(Dia.LUNES, Horario.BLOQUE3);

        Profesor p = new Profesor("p", "1", "p@correo.com", "p1", Set.of(5), Set.of(1000L), Set.of(Asignatura.MATEMATICA), Set.of(b1, b2, b3));

        clase1 = new Clase(p, "C1", Asignatura.MATEMATICA, b1, 5, 1000);
        clase2 = new Clase(p, "C2", Asignatura.MATEMATICA, b2, 5, 1000);
        clase3 = new Clase(p, "C3", Asignatura.MATEMATICA, b3, 5, 1000);

        calendario.addClaseToBloque(clase1);
        calendario.addClaseToBloque(clase2);
    }

    @Test
    @DisplayName("Verificar que ambas instancias de Calendario sean 'iguales'(que apunten al mismo lugar en memoria)")
    void testSingleton(){
        Calendario c2 = Calendario.getInstancia();
        assertSame(calendario, c2);
    }

    @DisplayName("Tests relacionados al manejo de Clases en el Calendario")
    @Test
    void testAgregarClaseAlBloque() {
        List<Clase> clases = calendario.getClasesEnBloque(Dia.LUNES, Horario.BLOQUE1);
        assertNotNull(clases);
        assertTrue(clases.contains(clase1));
    }

    @Test
    void testConflictoHorarioException() {
        assertThrows(ConflictoHorarioException.class, () -> calendario.addClaseToBloque(clase1));
    }

    @Test
    void testGetTodasLasClases() {
        List<Clase> todasLasClases = calendario.getTodasLasClases();
        assertEquals(2, todasLasClases.size());
        assertTrue(todasLasClases.contains(clase1));
        assertTrue(todasLasClases.contains(clase2));
    }

    @Test
    void testGetClasesEnDia() {
        calendario.addClaseToBloque(clase3);

        List<Clase> clasesLunes = calendario.getClasesEnDia(Dia.LUNES);
        assertEquals(2, clasesLunes.size());
        assertTrue(clasesLunes.contains(clase1));
        assertTrue(clasesLunes.contains(clase3));
    }
}