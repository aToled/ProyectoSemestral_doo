package Logica;

import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.Set;

public class EstudianteFactory extends ManejoGenericoJSON<Estudiante> {
    private static final Type listType = new TypeToken<Set<Estudiante>>(){}.getType();
    private static final EstudianteFactory instancia = new EstudianteFactory();

    protected EstudianteFactory() {
        super("ListaEstudiantes", listType);
    }

    public static Estudiante crearEstudiante(String nombre, String apellido, String correo, String id) {
        return new Estudiante(nombre, apellido, correo, id); //agregar los atributos por el Json
    }

    /**
     * @see ManejoGenericoJSON
     */
    public static Set<Estudiante> cargarEstudiantes() {
        return instancia.cargar();
    }

    /**
     * @see ManejoGenericoJSON
     */
    public static void agregarEstudiante(Estudiante estudiante) {
        instancia.agregar(estudiante);
    }

    /**
     * @see ManejoGenericoJSON
     */
    public static void eliminarEstudiante(String id) {
        instancia.eliminar(id);
    }
}
