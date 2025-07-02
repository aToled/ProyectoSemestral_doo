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

class EstrategiaMenorTarifaTest {
    private EstrategiaSolicitud estrategia;
    private Clase claseBuena, claseMala;
    private Solicitud solicitud;

    @BeforeEach
    void setUp() {
        BloqueHorario bloque = new BloqueHorario(Dia.LUNES, Horario.BLOQUE1);
        Estudiante e1 = new Estudiante("e1", "est", "e1@mail.com", "e1");
        e1.addMateriasInteres(Asignatura.MATEMATICA);
        Profesor p1 = new Profesor("p1", "prof", "p1@mail.com", "p1", 3, 1000, Set.of(Asignatura.MATEMATICA), Set.of(bloque));
        Profesor p2 = new Profesor("p2", "prof", "p2@mail.com", "p2", 3, 100000, Set.of(Asignatura.MATEMATICA), Set.of(bloque));

        claseBuena = new Clase(p1, "C01", Asignatura.MATEMATICA, bloque);
        claseMala = new Clase(p2, "C02", Asignatura.MATEMATICA, bloque);

        Calendario.getInstancia().clear();
        Calendario.getInstancia().addClaseToBloque(claseBuena);
        Calendario.getInstancia().addClaseToBloque(claseMala);

        e1.setPreferirMenorTarifa(true);
        solicitud = new Solicitud("s1", e1, Asignatura.MATEMATICA);
        estrategia = new EstrategiaMenorTarifa();
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
        assertFalse(propuesta.contains(claseMala), "Falla porque esta clase no es la mas barata.");
    }
}