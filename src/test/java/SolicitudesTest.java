import Logica.*;
import Logica.Enums.Asignatura;
import Logica.Enums.Dia;
import Logica.Enums.EstadoSolicitud;
import Logica.Enums.Horario;
import Logica.Estrategias.EstrategiaDefault;
import Logica.Estrategias.EstrategiaSolicitud;
import org.junit.jupiter.api.*;

import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class SolicitudesTest {
    private Estudiante estudiante;
    private Clase clase;
    private GestorSolicitudes gestor;
    private EstrategiaSolicitud siempreFalla;
    private Solicitud solicitud;

    @BeforeEach
    void setUp() {
        BloqueHorario Lunes8AM = new BloqueHorario(Dia.LUNES, Horario.BLOQUE1);
        estudiante = new Estudiante("est", "Dante", "estudiante@gmail.com", "e1");
        estudiante.addMateriasInteres(Asignatura.MATEMATICA);
        Profesor profesor = new Profesor("prof", "lessor", "profesor@gmail.com", "p1", Set.of(2), Set.of(10000L), Set.of(Asignatura.MATEMATICA), Set.of(Lunes8AM));
        clase = new Clase(profesor, "C01", Asignatura.MATEMATICA, Lunes8AM, 2, 10000);

        Calendario calendario = Calendario.getInstancia();
        calendario.clear();
        calendario.addClaseToBloque(clase);

        gestor = GestorSolicitudes.getInstancia();
        gestor.eliminarTodas();
        solicitud = estudiante.crearSolicitud(Asignatura.MATEMATICA);

        siempreFalla = new EstrategiaSolicitud() {
            @Override
            public boolean puedeAplicar(Solicitud s) {return true;}
            @Override
            public Set<Clase> proponerClase(Solicitud s) {return null;}
        };
    }

    @AfterEach
    void tearDown() {
        gestor.eliminarTodas();
    }

    @DisplayName("Tests relacionados al Manejo y Resolución de Solicitudes: ")
    @Test
    void testCongruenciaEnviarSolicitud() {
        assertNotNull(solicitud);
        assertEquals(estudiante, solicitud.getEstudiante());
        assertEquals(Asignatura.MATEMATICA, solicitud.getAsignatura());
        assertEquals(EstadoSolicitud.PENDIENTE, solicitud.getEstadoSolicitud());
        assertTrue(gestor.getObjetosNoModificable().contains(solicitud));
    }

    @Test
    void testResolverSolicitudConEstrategiaDefault() {
        boolean resolucion = gestor.resolver(solicitud.getId(), new EstrategiaDefault());

        assertTrue(resolucion);
        assertNotNull(solicitud.getClasesSugeridas());
        Clase elegida = new ArrayList<>(solicitud.getClasesSugeridas()).getFirst();
        assertEquals(clase, elegida);
    }

    @Test
    void testResolverSinEstrategiasExitosas() {
        boolean resolucion = gestor.resolver(solicitud.getId(), siempreFalla);

        assertFalse(resolucion);
        assertTrue(solicitud.getClasesSugeridas().isEmpty());
        assertEquals(EstadoSolicitud.INCONCLUSO, solicitud.getEstadoSolicitud());
    }

    @Test
    void testResolverConMultiplesEstrategias() {
        boolean resolucion = gestor.resolver(solicitud.getId(), siempreFalla, new EstrategiaDefault());

        assertTrue(resolucion);
        assertNotNull(solicitud.getClasesSugeridas());
    }

    @Test
    void testAceptar() {
        gestor.resolver(solicitud.getId(), new EstrategiaDefault());
        Clase elegida = new ArrayList<>(solicitud.getClasesSugeridas()).getFirst();
        solicitud.setClaseElegida(elegida);
        gestor.aceptar(solicitud.getId());

        assertEquals(EstadoSolicitud.ACEPTADA, solicitud.getEstadoSolicitud());
        assertTrue(solicitud.getClaseElegida().getListaEstudiantes().containsValue(estudiante));
    }

    @Test
    void testAceptarSinResolucion(){
        // Este igual verifica que buscarSolicitud funciona correctamente, ya que no encuentra la solicitud "-1" y lanza esa excepción.
        assertThrows(NoSuchElementException.class, () -> gestor.aceptar("-1"));
    }

    @Test
    void testRechazar() {
        gestor.rechazar(solicitud.getId());

        assertEquals(EstadoSolicitud.RECHAZADA, solicitud.getEstadoSolicitud());
        assertTrue(solicitud.getClasesSugeridas().isEmpty());
    }

    @Test
    void testListaSolicitudesNoModificable() {
        Set<Solicitud> set = gestor.getObjetosNoModificable();
        assertThrows(UnsupportedOperationException.class, () -> set.add(solicitud));
    }
}