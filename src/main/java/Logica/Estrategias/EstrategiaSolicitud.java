package Logica.Estrategias;

import Logica.Clase;
import Logica.Solicitud;
import java.util.Set;

public interface EstrategiaSolicitud {

    /**
     * Verifica si la estrategia se puede aplicar a la Solicitud dada,
     * para poder definir restricciones tales como alguna preferencia del Estudiante.
     * @param s: la solicitud a evaluar.
     * @return true si es posible aplicar la estrategia de lo contrario false.
     */
    boolean puedeAplicar(Solicitud s);

    /**
     * Propone una coleccion de clases concretas del calendario a las que el Estudiante se puede unir,
     * con base en las condiciones de la estrategia.
     * @param s: La solicitud.
     * @return Las clases si es que se encontraron válidas, si no, null.
     */
    Set<Clase> proponerClase(Solicitud s);
}