package Logica;

import Logica.Enums.Asignatura;
import Logica.Enums.EstadoSolicitud;
import java.util.LinkedHashSet;
import java.util.Set;

public class Solicitud implements Identificable {
    private final String id;
    private final Estudiante estudiante;
    private final Asignatura asignatura;
    private EstadoSolicitud estadoSolicitud;
    private Set<Clase> clasesSugeridas;
    private Clase claseElegida;

    public Solicitud(String id, Estudiante estudiante, Asignatura asignatura){
        this.id = id;
        this.estudiante = estudiante;
        this.asignatura = asignatura;
        this.estadoSolicitud = EstadoSolicitud.PENDIENTE;
        this.clasesSugeridas = new LinkedHashSet<>();
    }

    public String getId() {return id;}
    public Estudiante getEstudiante() {return estudiante;}
    public Asignatura getAsignatura() {return asignatura;}
    public EstadoSolicitud getEstadoSolicitud() {return estadoSolicitud;}
    public Set<Clase> getClasesSugeridas() {return clasesSugeridas;}
    public Clase getClaseElegida() {return claseElegida;}

    public void setEstadoSolicitud(EstadoSolicitud estadoSolicitud) {this.estadoSolicitud = estadoSolicitud;}
    public void setClasesSugeridas(Set<Clase> clasesSugeridas) {this.clasesSugeridas = clasesSugeridas;}

    public void setClaseElegida(Clase claseElegida){
        if(!clasesSugeridas.contains(claseElegida)){
            throw new IllegalArgumentException("Clase elegida no Valida.");
        }else{
            this.claseElegida = claseElegida;
            clasesSugeridas.clear();
            GestorSolicitudes.actualizar();
        }
    }

    @Override
    public boolean equals(Object o){
        if(o == this) return true;
        if(!(o instanceof Solicitud otro)) return false;
        return getId().equals(otro.getId());
    }

    @Override
    public final int hashCode() {
        if(id != null){
            return id.hashCode();
        } else {
            return 0;
        }
    }
}