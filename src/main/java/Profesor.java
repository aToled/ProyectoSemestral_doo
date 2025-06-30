import Enums.Asignatura;
import java.util.Set;

public class Profesor extends Persona {
    private final int capacidadMaximaAlumnos;
    private final long tarifa;
    private final Set<Asignatura> materiasQueDicta;
    private final Set<BloqueHorario> disponibilidad;

    public Profesor(String nombre, String apellido, String correo, String id, Set<Asignatura> materiasQueDicta, int capacidadMaximaAlumnos, long tarifa, Set<BloqueHorario> disponibilidad) {
        super(nombre, apellido, correo, id);
        this.capacidadMaximaAlumnos = capacidadMaximaAlumnos;
        this.materiasQueDicta = materiasQueDicta;
        this.disponibilidad = disponibilidad;
        this.tarifa = tarifa;
    }

    public int getCapacidadMaximaAlumnos(){
        return capacidadMaximaAlumnos;
    }
    public long getTarifa(){
        return tarifa;
    }
    public Set<Asignatura> getMateriasQueDicta() {
        return materiasQueDicta;
    }
    public Set<BloqueHorario> getDisponibilidad() {
        return disponibilidad;
    }
}
