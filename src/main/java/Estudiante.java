import java.util.HashSet;
import java.util.Set;

public class Estudiante extends Persona{
    private Set<Integer> idsClases;
    public Estudiante(String nombre, String apellido, String correo, String id, Asignatura asignatura,Horario horario ,Dia dia) {
        super(nombre, apellido, correo, id, asignatura, horario, dia);
        this.idsClases = new HashSet<>();
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
