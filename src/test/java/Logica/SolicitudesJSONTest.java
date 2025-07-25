package Logica;

import Logica.Enums.Asignatura;
import Logica.Identificables.Estudiante;
import Logica.Identificables.Solicitud;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Set;
import static org.junit.jupiter.api.Assertions.*;

class SolicitudesJSONTest {
    private Solicitud solicitud;

    @BeforeEach
    void setUp() {
        Estudiante e = new Estudiante("t", "est", "t@mail.com", "1");
        solicitud = new Solicitud("1", e, Asignatura.MATEMATICA);

    }

    @AfterAll
    static void cleanUp() {
        GestorSolicitudes.getInstancia().eliminarTodas();
    }

    @Test
    void testGuardarYRecuperarSolicitud() {
        GestorSolicitudes gestor = GestorSolicitudes.getInstancia();
        gestor.eliminarTodas();

        gestor.agregar(solicitud);
        Set<Solicitud> solicitudes = gestor.getObjetosNoModificable();

        assertTrue(solicitudes.contains(solicitud), "La Solicitud guardada debe existir en el JSON");
    }

    @Test
    void testEliminarSolicitud() {
        GestorSolicitudes gestor = GestorSolicitudes.getInstancia();
        gestor.eliminarTodas();

        gestor.agregar(solicitud);
        gestor.eliminar(solicitud.getId());

        Set<Solicitud> solicitudes = gestor.getObjetosNoModificable();
        assertFalse(solicitudes.contains(solicitud), "La Solicitud debe ser eliminada del JSON");
    }

    @Test
    void testBuscarSolicitudPorId() {
        GestorSolicitudes gestor = GestorSolicitudes.getInstancia();
        gestor.eliminarTodas();

        gestor.agregar(solicitud);
        Solicitud encontrada = gestor.buscarObjeto(solicitud.getId());

        assertNotNull(encontrada, "Debe recuperar la Solicitud por ID");
        assertEquals(solicitud.getId(), encontrada.getId(), "El ID debe coincidir");
    }
}