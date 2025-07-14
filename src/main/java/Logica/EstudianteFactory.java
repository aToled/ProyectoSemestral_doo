package Logica;

import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.Set;

/**
 * Clase que se encarga de la creación y gestión de los objetos tipo Estudiante mediante persistencia en JSON,
 * se extiende de ManejoGenericoJSON para operar sobre un conjunto de estudiantes almacenados localmente.
 */
public class EstudianteFactory extends ManejoGenericoJSON<Estudiante> {
    private static final Type listType = new TypeToken<Set<Estudiante>>(){}.getType();
    private static final EstudianteFactory instancia = new EstudianteFactory();

    private EstudianteFactory() {
        super("ListaEstudiantes", listType);
    }

    /**
     * Creación de la instancia. (uso de singleton)
     * @return Retorna la instancia.
     */
    public static EstudianteFactory getInstancia(){
        return instancia;
    }

    /**
     * Crea una instancia de Estudiante validando sus atributos.
     * @return Un Estudiante válido.
     * @throws IllegalArgumentException: si es que falla alguna validación.
     */
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

    public static int getCantidadEstudiantes() {
        Set<Estudiante> estudiantes = instancia.cargar();
        return estudiantes.size();
    }
}