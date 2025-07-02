package Logica.Estrategias;

import Logica.Calendario;
import Logica.Clase;
import Logica.Estudiante;
import Logica.Solicitud;

public class EstrategiaHorarioPreferido implements EstrategiaSolicitud{
    @Override
    public boolean puedeAplicar(Solicitud s) {
        return !s.getEstudiante().getHorariosInteres().isEmpty();
    }

    @Override
    public Clase proponerClase(Solicitud s) {
        Estudiante e = s.getEstudiante();

        for(Clase clase : Calendario.getInstancia().getTodasLasClases()){
            if(!e.getMateriasInteres().contains(s.getAsignatura())) continue;
            if(clase.getAsignatura() != s.getAsignatura()) continue;
            if(clase.isLlena()) continue;
            if(e.getHorariosInteres().contains(clase.getBloqueHorario().horario())){
                return clase;
            }
        }
        return null;
    }
}