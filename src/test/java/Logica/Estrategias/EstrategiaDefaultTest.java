package Logica.Estrategias;

import Logica.*;
import Logica.Enums.Asignatura;
import Logica.Enums.Dia;
import Logica.Enums.Horario;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class EstrategiaDefaultTest {

    private EstrategiaSolicitud estrategia;
    private Clase claseBuena1, claseBuena2, claseMala;
    private Solicitud solicitud;

    @BeforeEach
    void setUp() {
        Estudiante e1 = new Estudiante("e1", "est", "e1@mail.com", "e1");
        e1.addMateriasInteres(Asignatura.MATEMATICA);
        Estudiante e2 = new Estudiante("a","a","a","a");
        e2.addMateriasInteres(Asignatura.MATEMATICA);

        Profesor p1 = new Profesor("p1", "prof", "p1@mail.com", "p1", Set.of(2), Set.of(10000L), Set.of(Asignatura.MATEMATICA), Set.of(new BloqueHorario(Dia.LUNES, Horario.BLOQUE6), new BloqueHorario(Dia.MARTES, Horario.BLOQUE8)));
        Profesor p2 = new Profesor("p2", "prof", "p2@mail.com", "p2", Set.of(1), Set.of(10000L), Set.of(Asignatura.MATEMATICA), Set.of(new BloqueHorario(Dia.JUEVES, Horario.BLOQUE10)));

        claseBuena1 = new Clase(p1, "C01", Asignatura.MATEMATICA, new BloqueHorario(Dia.LUNES, Horario.BLOQUE6), 2, 10000);
        claseBuena2 = new Clase(p1, "C02", Asignatura.MATEMATICA, new BloqueHorario(Dia.MARTES, Horario.BLOQUE8), 2, 10000);
        claseMala = new Clase(p2, "C03", Asignatura.MATEMATICA, new BloqueHorario(Dia.JUEVES, Horario.BLOQUE10), 1, 10000);
        claseMala.agregarEstudiante(e2);

        Calendario.getInstancia().clear();
        Calendario.getInstancia().addClaseToBloque(claseBuena1);
        Calendario.getInstancia().addClaseToBloque(claseBuena2);
        Calendario.getInstancia().addClaseToBloque(claseMala);

        solicitud = new Solicitud("s1", e1, Asignatura.MATEMATICA);
        estrategia = new EstrategiaDefault();
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
        assertTrue(propuesta.contains(claseBuena1));
        assertTrue(propuesta.contains(claseBuena2));
        assertFalse(propuesta.contains(claseMala), "Falla porque esta clase esta llena.");
    }
}