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

class EstrategiaConMenosEstudiantesTest {
    private EstrategiaSolicitud estrategia;
    private Clase claseBuena, claseMala;
    private Solicitud solicitud;

    @BeforeEach
    void setUp() {
        BloqueHorario bloque = new BloqueHorario(Dia.LUNES, Horario.BLOQUE1);
        Estudiante e1 = new Estudiante("e1", "est", "e1@mail.com", "e1");
        e1.addMateriasInteres(Asignatura.MATEMATICA);
        Estudiante e2 = new Estudiante("e2", "est", "e2@mail.com", "e2");
        e2.addMateriasInteres(Asignatura.MATEMATICA);

        Profesor p1 = new Profesor("p1", "prof", "p1@mail.com", "p1", Set.of(1), Set.of(1000L), Set.of(Asignatura.MATEMATICA), Set.of(bloque));
        Profesor p2 = new Profesor("p2", "prof", "p2@mail.com", "p2", Set.of(2), Set.of(100000L), Set.of(Asignatura.MATEMATICA), Set.of(bloque));

        claseBuena = new Clase(p1, "C01", Asignatura.MATEMATICA, bloque, 1, 1000);
        claseMala = new Clase(p2, "C02", Asignatura.MATEMATICA, bloque, 2, 100000);
        claseMala.agregarEstudiante(e2);

        Calendario.getInstancia().clear();
        Calendario.getInstancia().addClaseToBloque(claseBuena);
        Calendario.getInstancia().addClaseToBloque(claseMala);

        e1.setPreferirClaseConMenosEstudiantes(true);
        solicitud = new Solicitud("s1", e1, Asignatura.MATEMATICA);
        estrategia = new EstrategiaConMenosEstudiantes();
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
        assertFalse(propuesta.contains(claseMala), "Falla porque esta clase no es la que menos estudiantes tiene.");
    }
}