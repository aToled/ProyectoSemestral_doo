package Logica.Estrategias;

import Logica.Calendario;
import Logica.Clase;
import Logica.Solicitud;
import java.util.LinkedHashSet;
import java.util.Set;

public class EstrategiaMenorTarifa implements EstrategiaSolicitud{
    @Override
    public boolean puedeAplicar(Solicitud s) {
        return s.getEstudiante().isPreferirMenorTarifa();
    }

    @Override
    public Set<Clase> proponerClase(Solicitud sol) {
        Clase mejorClase = null;
        long menorTarifa = Long.MAX_VALUE;

        for(Clase clase : Calendario.getInstancia().getTodasLasClases()){
            if(!sol.getEstudiante().getMateriasInteres().contains(sol.getAsignatura())) continue;
            if(clase.getAsignatura() != sol.getAsignatura()) continue;
            if(clase.isLlena()) continue;
            long tarifa = clase.getTarifa();
            if(tarifa < menorTarifa){
                menorTarifa = tarifa;
                mejorClase = clase;
            }
        }

        Set<Clase> candidatas = new LinkedHashSet<>();
        candidatas.add(mejorClase);
        return candidatas;
    }
}