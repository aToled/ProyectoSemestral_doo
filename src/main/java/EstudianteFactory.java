import Enums.Asignatura;
import java.util.Set;

public class EstudianteFactory{

    public static Estudiante crearEstudiante(String nombre, String apellido, String correo, String id, Set<Asignatura> materiasInteres) {
        return new Estudiante(nombre, apellido, correo, id, materiasInteres); //agregar los atributos por el Json
    }
}
