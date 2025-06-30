package Logica;

import Logica.Enums.Asignatura;
import Logica.Enums.EstadoSolicitud;

public class Solicitud {
    private String id;
    private Estudiante estudiante;
    private Asignatura asignatura;
    private EstadoSolicitud estadoSolicitud;
    private Clase claseSugerida;

    public Solicitud(){

    }

    public EstadoSolicitud getEstadoSolicitud() {
        return estadoSolicitud;
    }
    public Clase getClaseSugerida() {
        return claseSugerida;
    }
}
