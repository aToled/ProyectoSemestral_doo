package Logica.Estrategias;

import Logica.*;
import Logica.Enums.Asignatura;
import Logica.Enums.Dia;
import Logica.Enums.Horario;
import Logica.Identificables.Clase;
import Logica.Identificables.Estudiante;
import Logica.Identificables.Profesor;
import Logica.Identificables.Solicitud;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class EstrategiaBloqueHorarioPreferidoTest {
    private EstrategiaSolicitud estrategia;
    private Clase claseBuena, claseMala;
    private Solicitud solicitud;

    @BeforeEach
    void setUp() {
        BloqueHorario bloquePref = new BloqueHorario(Dia.LUNES, Horario.BLOQUE1);
        BloqueHorario bloqueNoPref = new BloqueHorario(Dia.VIERNES, Horario.BLOQUE1);
        Estudiante e1 = new Estudiante("e1", "est", "e1@mail.com", "e1");
        e1.addMateriasInteres(Asignatura.MATEMATICA);
        Profesor p1 = new Profesor("p1", "prof", "p1@mail.com", "p1", Set.of(3), Set.of(1000L), Set.of(Asignatura.MATEMATICA), Set.of(bloquePref));
        Profesor p2 = new Profesor("p2", "prof", "p2@mail.com", "p2", Set.of(3), Set.of(1000L), Set.of(Asignatura.MATEMATICA), Set.of(bloqueNoPref));

        claseBuena = new Clase(p1, "CBuena", Asignatura.MATEMATICA, bloquePref, 3, 1000);
        claseMala = new Clase(p2, "CMala", Asignatura.MATEMATICA, bloqueNoPref, 3, 1000);

        Calendario.getInstancia().clear();
        Calendario.getInstancia().addClaseToBloque(claseBuena);
        Calendario.getInstancia().addClaseToBloque(claseMala);

        e1.addBloquesHorariosInteres(bloquePref);
        solicitud = new Solicitud("s1", e1, Asignatura.MATEMATICA);
        estrategia = new EstrategiaBloqueHorarioPreferido();
    }

    @AfterEach
    void tearDown() {
        Calendario.getInstancia().clear();
    }

    @Test
    void testPuedeAplicar() {
        assertTrue(estrategia.puedeAplicar(solicitud));
    }

    @Test
    void testProponerClaseFiltraCorrectamente() {
        Set<Clase> propuesta = estrategia.proponerClase(solicitud);
        assertTrue(propuesta.contains(claseBuena));
        assertFalse(propuesta.contains(claseMala), "Falla porque el estudiante no agrego el bloque de esta Clase a sus bloques de interés.");
    }
}