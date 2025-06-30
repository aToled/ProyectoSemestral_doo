import Enums.Asignatura;
import java.util.Set;

public class Estudiante extends Persona {
    private Set<Asignatura> materiasInteres;

    public Estudiante(String nombre, String apellido, String correo, String id, Set<Asignatura> materiasInteres) {
        super(nombre, apellido, correo, id);
        this.materiasInteres = materiasInteres;
    }

    public Set<Asignatura> getMateriasInteres() {
        return materiasInteres;
    }
}
