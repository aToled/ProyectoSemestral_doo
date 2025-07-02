package Logica.Estrategias;

import Logica.Calendario;
import Logica.Clase;
import Logica.Estudiante;
import Logica.Solicitud;
import java.util.LinkedHashSet;
import java.util.Set;

public class EstrategiaHorarioPreferido implements EstrategiaSolicitud{
    @Override
    public boolean puedeAplicar(Solicitud s) {
        return !s.getEstudiante().getHorariosInteres().isEmpty();
    }

    @Override
    public Set<Clase> proponerClase(Solicitud s) {
        Estudiante e = s.getEstudiante();
        Set<Clase> candidatas = new LinkedHashSet<>();

        for(Clase clase : Calendario.getInstancia().getTodasLasClases()){
            if(!e.getMateriasInteres().contains(s.getAsignatura())) continue;
            if(clase.getAsignatura() != s.getAsignatura()) continue;
            if(clase.isLlena()) continue;
            if(e.getHorariosInteres().contains(clase.getBloqueHorario().horario())){
                candidatas.add(clase);
            }
        }
        return candidatas;
    }
}