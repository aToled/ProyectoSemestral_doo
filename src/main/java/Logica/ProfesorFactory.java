package Logica;

import Logica.Enums.Asignatura;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.Set;

public class ProfesorFactory extends ManejoGenericoJSON<Profesor>{
    private static final Type listType = new TypeToken<Set<Profesor>>(){}.getType();
    private static final ProfesorFactory instancia = new ProfesorFactory();

    private ProfesorFactory(){
        super("ListaProfesores", listType);
    }

    /**
     * Clase usada para crear nuvas instancias dentro de sacarArchivo
     * @param nombre:
     * @param apellido:
     * @param correo:
     * @param id:
     * @param capacidadMaximaAlumnos:
     * @param tarifa:
     * @param materiasQueDicta:
     * @param disponibilidad:
     * @return .
     */
    public static Profesor crearProfesor(String nombre, String apellido, String correo, String id, int capacidadMaximaAlumnos, long tarifa, Set<Asignatura> materiasQueDicta, Set<BloqueHorario> disponibilidad) {
        if(capacidadMaximaAlumnos<=0)
            throw new IllegalArgumentException("La capacidad maxima debe ser mayor a 0");
        if(materiasQueDicta==null || materiasQueDicta.isEmpty())
            throw new IllegalArgumentException("El Profesor debe dictar como minimo 1 asignatura");
        if(disponibilidad==null || disponibilidad.isEmpty())
            throw new IllegalArgumentException("El Profesor debe tener al menos un bloque disponible");
        return new Profesor(nombre, apellido, correo, id, capacidadMaximaAlumnos, tarifa, materiasQueDicta, disponibilidad); //agregar los atributos por el Json
    }

    /**
     * @see ManejoGenericoJSON
     */
    public static Set<Profesor> cargarProfesores() {
        return instancia.cargar();
    }

    /**
     * @see ManejoGenericoJSON
     */
    public static void agregarProfesor(Profesor profesor){
        instancia.agregar(profesor);
    }

    /**
     * @see ManejoGenericoJSON
     */
    public static void eliminarProfesor(String id){
        instancia.eliminar(id);
    }
}