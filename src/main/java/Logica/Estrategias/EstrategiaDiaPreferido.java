package Logica.Estrategias;

import Logica.Calendario;
import Logica.Clase;
import Logica.Estudiante;
import Logica.Solicitud;

public class EstrategiaDiaPreferido implements EstrategiaSolicitud{
    @Override
    public boolean puedeAplicar(Solicitud s) {
        return !s.getEstudiante().getDiasInteres().isEmpty();
    }

    @Override
    public Clase proponerClase(Solicitud s) {
        Estudiante e = s.getEstudiante();

        for(Clase clase : Calendario.getInstancia().getTodasLasClases()){
            if(!e.getMateriasInteres().contains(s.getAsignatura())) continue;
            if(clase.getAsignatura() != s.getAsignatura()) continue;
            if(clase.isLlena()) continue;
            if(e.getDiasInteres().contains(clase.getBloqueHorario().dia())){
                return clase;
            }
        }
        return null;
    }
}