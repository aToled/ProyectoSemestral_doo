import Enums.Asignatura;
import java.util.Set;

public class Profesor extends Persona {
    private final long tarifa;
    private final int capacidadMaximaAlumnos;
    private final Set<Asignatura> materiasQueDicta;
    private final Set<BloqueHorario> disponibilidad;

    public Profesor(String nombre, String apellido, String correo, String id, int capacidadMaximaAlumnos, long tarifa, Set<Asignatura> materiasQueDicta, Set<BloqueHorario> disponibilidad) {
        super(nombre, apellido, correo, id);
        this.capacidadMaximaAlumnos = capacidadMaximaAlumnos;
        this.tarifa = tarifa;
        this.materiasQueDicta = materiasQueDicta;
        this.disponibilidad = disponibilidad;
    }

    public int getCapacidadMaximaAlumnos(){
        return capacidadMaximaAlumnos;
    }
    public Set<Asignatura> getMateriasQueDicta() {
        return materiasQueDicta;
    }
    public Set<BloqueHorario> getDisponibilidad() {
        return disponibilidad;
    }
    public long getTarifa(){
        return tarifa;
    }
}
