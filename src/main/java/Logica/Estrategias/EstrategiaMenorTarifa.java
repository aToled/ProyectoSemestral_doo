package Logica.Estrategias;

import Logica.Calendario;
import Logica.Clase;
import Logica.Enums.Asignatura;
import Logica.Estudiante;
import Logica.Solicitud;

public class EstrategiaMenorTarifa implements EstrategiaSolicitud{
    @Override
    public boolean puedeAplicar(Solicitud s) {
        return s.getEstudiante().isPreferirMenorTarifa();
    }

    @Override
    public Clase proponerClase(Solicitud s) {
        Estudiante e = s.getEstudiante();

        Clase mejorClase = null;
        long menorTarifa = Long.MAX_VALUE;

        for(Clase clase : Calendario.getInstancia().getTodasLasClases()){
            if(!e.getMateriasInteres().contains(s.getAsignatura())) continue;
            if(clase.getAsignatura() != s.getAsignatura()) continue;
            if(clase.isLlena()) continue;
            long tarifa = clase.getProfesor().getTarifa();
            if(tarifa < menorTarifa){
                menorTarifa = tarifa;
                mejorClase = clase;
            }
        }
        return mejorClase;
    }
}