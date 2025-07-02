package Logica.Estrategias;

import Logica.Calendario;
import Logica.Clase;
import Logica.Enums.Asignatura;
import Logica.Estudiante;
import Logica.Solicitud;

public class EstrategiaDefault implements EstrategiaSolicitud{

    /**
     * Como es la estrategia por defecto, se puede aplicar a todas las solicitudes.
     * (Idealmente, es la última que se ejecuta)
     * @param s: la solicitud a evaluar.
     * @return .
     */
    @Override
    public boolean puedeAplicar(Solicitud s) {
        return true;
    }

    /**
     * Devuelve la primera clase con un cupo disponible, tomando el estudiante
     * y la asignatura asociadas a la solicitud, si no hay ninguna disponible devuelve null.
     * @param s: tal asignatura.
     * @return tal clase.
     */
    @Override
    public Clase proponerClase(Solicitud s) {
        Estudiante estudiante = s.getEstudiante();
        Asignatura asignatura = s.getAsignatura();
        Calendario c = Calendario.getInstancia();

        for(Clase clase : c.getTodasLasClases()){
            if(!estudiante.getMateriasInteres().contains(asignatura)) continue;
            if(clase.getAsignatura() != asignatura) continue;
            if(clase.isLlena()) continue;
            return clase;
        }
        return null;
    }
}
