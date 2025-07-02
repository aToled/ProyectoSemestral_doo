package Logica.Estrategias;

import Logica.Calendario;
import Logica.Clase;
import Logica.Solicitud;
import java.util.LinkedHashSet;
import java.util.Set;

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
     * @param sol: tal asignatura.
     * @return tal clase.
     */
    @Override
    public Set<Clase> proponerClase(Solicitud sol) {
        Set<Clase> candidatas = new LinkedHashSet<>();
        for(Clase clase : Calendario.getInstancia().getTodasLasClases()){
            if(!sol.getEstudiante().getMateriasInteres().contains(sol.getAsignatura())) continue;
            if(clase.getAsignatura() != sol.getAsignatura()) continue;
            if(clase.isLlena()) continue;
            candidatas.add(clase);
        }
        return candidatas;
    }
}