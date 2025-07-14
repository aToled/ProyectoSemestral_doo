package Logica;

import Logica.Enums.Asignatura;
import Logica.Enums.EstadoSolicitud;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * Representa una solicitud creada por un Estudiante para alguna asignatura específica que desee cursar, la cual es gestionada y almacenada
 * exclusivamente por el GestorSolicitudes, además, esta es Identificable.
 * @see IdentificableAbstracta
 */
public class Solicitud extends IdentificableAbstracta {
    private final Estudiante estudiante;
    private final Asignatura asignatura;
    private EstadoSolicitud estadoSolicitud;
    private Set<Clase> clasesSugeridas;
    private Clase claseElegida;


    public Solicitud(String id, Estudiante estudiante, Asignatura asignatura){
        super(id);
        this.estudiante = estudiante;
        this.asignatura = asignatura;
        this.estadoSolicitud = EstadoSolicitud.PENDIENTE;
        this.clasesSugeridas = new LinkedHashSet<>();
    }

    // getters:
    public Estudiante getEstudiante() {return estudiante;}
    public Asignatura getAsignatura() {return asignatura;}
    public EstadoSolicitud getEstadoSolicitud() {return estadoSolicitud;}
    public Set<Clase> getClasesSugeridas() {return clasesSugeridas;}
    public Clase getClaseElegida() {return claseElegida;}

    // setters:
    public void setEstadoSolicitud(EstadoSolicitud estadoSolicitud) {this.estadoSolicitud = estadoSolicitud;}
    public void setClasesSugeridas(Set<Clase> clasesSugeridas) {this.clasesSugeridas = clasesSugeridas;}

    /**
     * Asigna la Clase elegida por el Estudiante del conjunto de clases sugeridas validando que esta pertenezca al
     * Set mencionado anteriormente, limpia las clases sugeridas y finalmente notifica a los observadores.
     * @param claseElegida: la Clase elegida por el Estudiante.
     * @throws IllegalArgumentException en caso de que la clase no pertenezca a las clasesSugeridas.
     */
    public void setClaseElegida(Clase claseElegida){
        if(!clasesSugeridas.contains(claseElegida)){
            throw new IllegalArgumentException("Clase elegida no Valida.");
        }else{
            this.claseElegida = claseElegida;
            clasesSugeridas.clear();
            GestorSolicitudes.actualizar();
        }
    }
}