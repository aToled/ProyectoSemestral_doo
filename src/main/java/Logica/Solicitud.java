package Logica;

import Logica.Enums.Asignatura;
import Logica.Enums.EstadoSolicitud;

public class Solicitud {
    private final String id;
    private final Estudiante estudiante;
    private final Asignatura asignatura;
    private EstadoSolicitud estadoSolicitud;
    private Clase claseSugerida;

    public Solicitud(String id, Estudiante estudiante, Asignatura asignatura){
        this.id = id;
        this.estudiante = estudiante;
        this.asignatura = asignatura;
        this.estadoSolicitud = EstadoSolicitud.PENDIENTE;
    }

    public String getId() {
        return id;
    }
    public Estudiante getEstudiante() {
        return estudiante;
    }
    public Asignatura getAsignatura() {
        return asignatura;
    }
    public EstadoSolicitud getEstadoSolicitud() {
        return estadoSolicitud;
    }
    public Clase getClaseSugerida() {
        return claseSugerida;
    }

    public void setEstadoSolicitud(EstadoSolicitud estadoSolicitud) {
        this.estadoSolicitud = estadoSolicitud;
    }
    public void setClaseSugerida(Clase claseSugerida) {
        this.claseSugerida = claseSugerida;
    }
}
