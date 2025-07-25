package Logica.Estrategias;

import Logica.Calendario;
import Logica.Identificables.Clase;
import Logica.Identificables.Estudiante;
import Logica.Identificables.Solicitud;
import java.util.LinkedHashSet;
import java.util.Set;

public class EstrategiaBloqueHorarioPreferido implements EstrategiaSolicitud{
    @Override
    public boolean puedeAplicar(Solicitud s) {
        return !s.getEstudiante().getBloquesHorariosInteres().isEmpty();
    }

    @Override
    public Set<Clase> proponerClase(Solicitud s) {
        Estudiante e = s.getEstudiante();
        Set<Clase> candidatas = new LinkedHashSet<>();

        for(Clase clase : Calendario.getInstancia().getTodasLasClases()){
            if(!e.getMateriasInteres().contains(s.getAsignatura())) continue;
            if(clase.getAsignatura() != s.getAsignatura()) continue;
            if(clase.isLlena()) continue;
            if(e.getBloquesHorariosInteres().contains(clase.getBloqueHorario())){
                candidatas.add(clase);
            }
        }
        return candidatas;
    }
}