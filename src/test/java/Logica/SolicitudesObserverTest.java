package Logica;

import Logica.Enums.Asignatura;
import Logica.Enums.Dia;
import Logica.Enums.EstadoSolicitud;
import Logica.Enums.Horario;
import Logica.Estrategias.EstrategiaDefault;
import org.junit.jupiter.api.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class SolicitudesObserverTest {

    private GestorSolicitudes gestor;
    private ObservadorTest observadorTest;
    private Solicitud s;
    private Estudiante e;

    private static class ObservadorTest implements ObservadorSolicitudes{
        private Set<Solicitud> solicitudesRecibidas = new HashSet<>();

        @Override
        public void actualizar(Set<Solicitud> nuevasSolicitudes) {
            solicitudesRecibidas = new HashSet<>();
            solicitudesRecibidas.addAll(nuevasSolicitudes);
        }
        public Set<Solicitud> getSolicitudesRecibidas() {return solicitudesRecibidas;}
    }

    @BeforeEach
    void setUp() {
        observadorTest = new ObservadorTest();
        BloqueHorario Lunes8AM = new BloqueHorario(Dia.LUNES, Horario.BLOQUE1);
        Profesor profesor = new Profesor("prof", "lessor", "profesor@gmail.com", "p1", Set.of(2), Set.of(10000L), Set.of(Asignatura.MATEMATICA), Set.of(Lunes8AM));
        Clase clase = new Clase(profesor, "C01", Asignatura.MATEMATICA, Lunes8AM, 2, 10000);

        Calendario calendario = Calendario.getInstancia();
        calendario.clear();
        calendario.addClaseToBloque(clase);

        gestor = GestorSolicitudes.getInstancia();
        gestor.suscribir(observadorTest);
    }

    @AfterEach
    void tearDown() {
        gestor.eliminarTodas();
    }

    @Test
    void testNotificarAlAgregar() {
        e = new Estudiante("est", "Dante", "estudiante@gmail.com", "e1");
        e.addMateriasInteres(Asignatura.MATEMATICA);
        s = e.crearSolicitud(Asignatura.MATEMATICA);
        assertTrue(observadorTest.getSolicitudesRecibidas().contains(s));
    }

    @Test
    void testNotificarAlAceptar() {
        e = new Estudiante("est", "Dante", "estudiante@gmail.com", "e1");
        e.addMateriasInteres(Asignatura.MATEMATICA);
        s = e.crearSolicitud(Asignatura.MATEMATICA);
        gestor.resolver(s.getId(), new EstrategiaDefault());
        Clase elegida = new ArrayList<>(s.getClasesSugeridas()).getFirst();
        s.setClaseElegida(elegida);

        gestor.aceptar(s.getId());
        boolean encontrada = false;
        for (Solicitud sol : observadorTest.getSolicitudesRecibidas()) {
            if (sol.getId().equals(s.getId()) && (sol.getEstadoSolicitud() == EstadoSolicitud.ACEPTADA)) {
                encontrada = true;
                break;
            }
        }
        assertTrue(encontrada);
    }

    @Test
    void testDeSuscribirObservador() {
        gestor.deSuscribir(observadorTest);
        e = new Estudiante("est", "Dante", "estudiante@gmail.com", "e1");
        e.addMateriasInteres(Asignatura.MATEMATICA);
        e.crearSolicitud(Asignatura.MATEMATICA);
        assertTrue(observadorTest.getSolicitudesRecibidas().isEmpty());
    }

    @Test
    void testObservadorSuscritoDespuesRecibeEstadoActual() {
        Estudiante e2 = new Estudiante("E", "S", "e@s.com", "e2");
        e2.addMateriasInteres(Asignatura.MATEMATICA);
        Solicitud s2 = e2.crearSolicitud(Asignatura.MATEMATICA);

        ObservadorTest obs = new ObservadorTest();
        gestor.suscribir(obs);

        Set<Solicitud> recibidas = obs.getSolicitudesRecibidas();
        assertTrue(recibidas.contains(s2));
    }

    @Test
    void testMultiplesObservadoresRecibenActualizacion() {
        ObservadorTest obs1 = new ObservadorTest();
        ObservadorTest obs2 = new ObservadorTest();
        gestor.suscribir(obs1);
        gestor.suscribir(obs2);

        Estudiante e2 = new Estudiante("E2", "S2", "e2@s.com", "e3");
        e2.addMateriasInteres(Asignatura.MATEMATICA);
        Solicitud s2 = e2.crearSolicitud(Asignatura.MATEMATICA);

        assertTrue(obs1.getSolicitudesRecibidas().contains(s2));
        assertTrue(obs2.getSolicitudesRecibidas().contains(s2));
    }
}