package Logica.Estrategias;

import Logica.Calendario;
import Logica.Clase;
import Logica.Estudiante;
import Logica.Solicitud;
import java.util.LinkedHashSet;
import java.util.Set;

public class EstrategiaConMenosEstudiantes implements EstrategiaSolicitud{
    @Override
    public boolean puedeAplicar(Solicitud s) {
        return s.getEstudiante().isPreferirClaseConMenosEstudiantes();
    }

    @Override
    public Set<Clase> proponerClase(Solicitud s) {
        Estudiante e = s.getEstudiante();

        Clase mejorClase = null;
        int menorCantidadEstudiantes = Integer.MAX_VALUE;

        for(Clase clase : Calendario.getInstancia().getTodasLasClases()){
            if(!e.getMateriasInteres().contains(s.getAsignatura())) continue;
            if(clase.getAsignatura() != s.getAsignatura()) continue;
            if(clase.isLlena()) continue;
            int cantidadEstudiantes = clase.cantidadEstudiantes();
            if(cantidadEstudiantes < menorCantidadEstudiantes){
                menorCantidadEstudiantes = cantidadEstudiantes;
                mejorClase = clase;
            }
        }

        Set<Clase> candidatas = new LinkedHashSet<>();
        candidatas.add(mejorClase);
        return candidatas;
    }
}
