package Logica;

import Logica.Enums.Asignatura;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.Set;

/**
 * Clase que se encarga de la creación y gestión de los objetos tipo Profesor mediante persistencia en JSON,
 * se extiende de ManejoGenericoJSON para operar sobre un conjunto de profesores almacenados localmente.
 * @see ManejoGenericoJSON
 */
public class ProfesorFactory extends ManejoGenericoJSON<Profesor>{
    private static final ProfesorFactory instancia = new ProfesorFactory();

    private ProfesorFactory(){
        super("ListaProfesores", new TypeToken<Set<Profesor>>(){}.getType());
    }

    /**
     * Creación de la instancia. (uso de singleton)
     * @return Retorna la instancia.
     */
    public static ProfesorFactory getInstancia(){
        return instancia;
    }

    /**
     * Crea una instancia de Profesor validando sus atributos.
     * @return Un Profesor válido.
     * @throws IllegalArgumentException: si es que falla alguna validación.
     */
    public static Profesor crearProfesor(String nombre, String apellido, String correo, String id, Set<Integer> capacidadesMaximasAlumnos, Set<Long> tarifas, Set<Asignatura> materiasQueDicta, Set<BloqueHorario> disponibilidad) {
        for(Integer capacidad : capacidadesMaximasAlumnos)
            if(capacidad <=0)
                throw new IllegalArgumentException("La capacidad maxima debe ser mayor a 0");
        if(materiasQueDicta==null || materiasQueDicta.isEmpty())
            throw new IllegalArgumentException("El Profesor debe dictar como mínimo 1 asignatura");
        if(disponibilidad==null || disponibilidad.isEmpty())
            throw new IllegalArgumentException("El Profesor debe tener al menos un bloque disponible");
        return new Profesor(nombre, apellido, correo, id, capacidadesMaximasAlumnos, tarifas, materiasQueDicta, disponibilidad); //agregar los atributos por el Json
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