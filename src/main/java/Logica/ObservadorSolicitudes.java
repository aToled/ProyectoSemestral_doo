package Logica;
import Logica.Identificables.Solicitud;

import java.util.Set;

/**
 * Interfaz para componentes que observan cambios en el conjunto de solicitudes.
 */
public interface ObservadorSolicitudes {
    /**
     * Recibe las solicitudes más recientes del gestor cada vez que se realiza alguna operación significativa.
     * @param nuevasSolicitudes: el Set de tales Solicitudes.
     */
    void actualizar(Set<Solicitud> nuevasSolicitudes);
}