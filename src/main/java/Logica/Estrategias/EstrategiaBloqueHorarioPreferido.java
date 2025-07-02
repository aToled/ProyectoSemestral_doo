package Logica.Estrategias;

import Logica.Calendario;
import Logica.Clase;
import Logica.Estudiante;
import Logica.Solicitud;

public class EstrategiaBloqueHorarioPreferido implements EstrategiaSolicitud{
    @Override
    public boolean puedeAplicar(Solicitud s) {
        return !s.getEstudiante().getBloquesHorariosInteres().isEmpty();
    }

    /**
     * TODO, pronto lo cambiare pero por ahora devuelve la primera clase
     * TODO que cumpla con la preferencia del estudiante
     * TODO (a futuro devolvera una lista con todas las que cumplan)
     * TODO (esto ultimo aplica para las otras estrategia tambien)
     * TODO hacer UnitTest
     * @param s: La solicitud.
     * @return .
     */
    @Override
    public Clase proponerClase(Solicitud s) {
        Estudiante e = s.getEstudiante();

        for(Clase clase : Calendario.getInstancia().getTodasLasClases()){
            if(!e.getMateriasInteres().contains(s.getAsignatura())) continue;
            if(clase.getAsignatura() != s.getAsignatura()) continue;
            if(clase.isLlena()) continue;
            if(e.getBloquesHorariosInteres().contains(clase.getBloqueHorario())){
                return clase;
            }
        }
        return null;
    }
}
