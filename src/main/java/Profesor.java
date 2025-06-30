import java.util.HashSet;
import java.util.Set;

public class Profesor extends Persona {
    private final int cantidadMaximaAlumnos;
    private Set<Integer> idsClases;
    private long tarifa;

    public Profesor(String nombre, String apellido, String correo, String id, Asignatura asignatura, int cantidadMaximaAlumnos, Horario horario, Dia dia) {
        super(nombre, apellido, correo, id, asignatura, horario, dia);
        this.cantidadMaximaAlumnos = cantidadMaximaAlumnos;
        this.idsClases = new HashSet<>();
    }

    public int getCantidadMaximaAlumnos(){
        return cantidadMaximaAlumnos;
    }

    public long getTarifa(){
        return tarifa;
    }

    public void addClase(int id){
        idsClases.add(id);
    }

    public void removeClase(int id){
        idsClases.remove(id);
    }

    public Set<Integer> getIdsClases() {
        return idsClases;
    }
}
