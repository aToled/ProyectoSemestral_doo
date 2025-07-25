package Logica;

import Logica.Enums.Asignatura;
import Logica.Enums.Dia;
import Logica.Enums.Horario;
import Logica.Identificables.Clase;
import Logica.Identificables.Profesor;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class ManejoClasesJSONTest {
    private Clase clase;

    @BeforeEach
    void setUp() {
        Profesor profe = new Profesor("Prof", "1", "p@mail.cl", "p1", Set.of(10), Set.of(123L), Set.of(Asignatura.MATEMATICA), Set.of(new BloqueHorario(Dia.LUNES, Horario.BLOQUE9)));
        clase = new Clase(profe, "TEST001", Asignatura.MATEMATICA, new BloqueHorario(Dia.LUNES, Horario.BLOQUE9), 10, 123L);
    }

    @AfterAll
    static void cleanUp() {
        ManejoClasesJSON.getInstancia().eliminarTodas();
    }

    @Test
    void testGuardarYRecuperarClase() {
        ManejoClasesJSON manejo = ManejoClasesJSON.getInstancia();
        manejo.eliminarTodas();

        manejo.agregar(clase);
        Set<Clase> clasesRecuperadas = manejo.getObjetosNoModificable();

        assertTrue(clasesRecuperadas.contains(clase), "La Clase guardada debe existir en el JSON");
    }

    @Test
    void testEliminarClasePorId() {
        ManejoClasesJSON manejo = ManejoClasesJSON.getInstancia();
        manejo.eliminarTodas();

        manejo.agregar(clase);
        manejo.eliminar(clase.getId());

        Set<Clase> clases = manejo.getObjetosNoModificable();
        assertFalse(clases.contains(clase), "La Clase debe ser eliminada del JSON");
    }

    @Test
    void testRecuperarClasePorId() {
        ManejoClasesJSON manejo = ManejoClasesJSON.getInstancia();
        manejo.eliminarTodas();

        manejo.agregar(clase);
        Clase claseEncontrada = manejo.buscarObjeto(clase.getId());

        assertNotNull(claseEncontrada, "Debe recuperar la Clase por ID");
        assertEquals(clase.getId(), claseEncontrada.getId(), "El ID debe coincidir");
    }
}