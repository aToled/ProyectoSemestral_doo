package Logica;

import Logica.Enums.Asignatura;
import java.util.HashMap;
import java.util.Map;

/**
 * Representa una Clase de alguna Asignatura en un bloque específico del Horario, son creadas por el administrador
 * basándose en los atributos del Profesor que desee asignarle a la clase, es Identificable.
 * @see IdentificableAbstracta
 */
public class Clase extends IdentificableAbstracta{

    private final Profesor profesor;
    private final Map<String,Estudiante> listaEstudiantes;
    private final Asignatura asignatura;
    private final BloqueHorario bloqueHorario;
    private final int capacidadMaximaAlumnos;
    private final long tarifa;

    /**
     * Crea una instancia de Clase validando sus atributos basándose en el Profesor asignado.
     * @throws IllegalArgumentException: si es que falla alguna validación.
     */
    public Clase(Profesor profesor, String id, Asignatura asignatura, BloqueHorario bloque, int capacidadMaximaAlumnos, long tarifa) {
        super(id);
        if(!profesor.getMateriasQueDicta().contains(asignatura)){
            throw new IllegalArgumentException("El Profesor no dicta: "+asignatura);
        }
        if(!profesor.getDisponibilidad().contains(bloque)){
            throw new IllegalArgumentException("El Profesor no esta disponible en: "+ bloque);
        }
        if(!profesor.getCapacidadesMaximasAlumnos().contains(capacidadMaximaAlumnos)){
            throw new IllegalArgumentException("El Profesor no acepta esta cantidad de alumnos: "+ capacidadMaximaAlumnos);
        }
        if(!profesor.getTarifas().contains(tarifa)){
            throw new IllegalArgumentException("El Profesor no acepta esta tarifa: "+ tarifa);
        }
        listaEstudiantes = new HashMap<>();
        this.profesor = profesor;
        this.asignatura = asignatura;
        this.bloqueHorario = bloque;
        this.capacidadMaximaAlumnos = capacidadMaximaAlumnos;
        this.tarifa = tarifa;
    }

    /**
     * Agrega un Estudiante a la clase tras hacer las verificaciones necesarias.
     * (si este está interesado en la asignatura, si es que no se encuentra ya en la lista de clases, y si no está llena la clase)
     * @param e: tal Estudiante
     * @return Booleano si es que se pudo realizar la operación
     */
    public boolean agregarEstudiante(Estudiante e) {
        if (!e.getMateriasInteres().contains(asignatura)) return false;
        if(listaEstudiantes.containsKey(e.getId())) return false;
        if(listaEstudiantes.size() >= capacidadMaximaAlumnos) return false;

        listaEstudiantes.put(e.getId(), e);
        return true;
    }

    /**
     * Verifica si la Clase esta llena o no.
     * @return True si la lista es mayor o igual a la cantidad maxima permitida por el profesor.
     */
    public boolean isLlena(){
        return listaEstudiantes.size() >= capacidadMaximaAlumnos;
    }

    /**
     * Elimina a un Estudiante de la Clase al identificarlo por su ID.
     * @param idEstudiante: la ID del Estudiante.
     */
    public void eliminarEstudiante(String idEstudiante) {listaEstudiantes.remove(idEstudiante);}

    /**
     * Retorna el total de estudiantes que posee la Clase.
     * @return la cantidad entera de estudiantes.
     */
    public int cantidadEstudiantes(){return listaEstudiantes.size();}

    // getters:
    public Map<String, Estudiante> getListaEstudiantes() {return listaEstudiantes;}
    public BloqueHorario getBloqueHorario() {return bloqueHorario;}
    public Asignatura getAsignatura() {return asignatura;}
    public Profesor getProfesor() {return profesor;}
    public int getCapacidadMaximaAlumnos() {return capacidadMaximaAlumnos;}
    public long getTarifa() {return tarifa;}
}