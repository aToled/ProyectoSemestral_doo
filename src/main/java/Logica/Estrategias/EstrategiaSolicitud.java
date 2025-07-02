package Logica.Estrategias;

import Logica.Clase;
import Logica.Solicitud;

public interface EstrategiaSolicitud {

    /**
     * Verifica si la estrategia se puede aplicar a la Solicitud dada,
     * para poder definir restricciones tales como alguna preferencia del Estudiante.
     * @param s: la solicitud a evaluar.
     * @return true si es posible aplicar la estrategia de lo contrario false.
     */
    boolean puedeAplicar(Solicitud s);

    /**
     * Propone una clase concreta del calendario a la que el Estudiante se puede unir,
     * con base en las condiciones de la estrategia.
     * @param s: La solicitud.
     * @return La clase si es que se encontró alguna, si no, null.
     */
    Clase proponerClase(Solicitud s);
}
