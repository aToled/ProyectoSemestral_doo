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

    private Profesor profesor;
    private final Map<String,Estudiante> listaEstudiantes;
    private Asignatura asignatura;
    private BloqueHorario bloqueHorario;
    private int capacidadMaximaAlumnos;
    private long tarifa;

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

    /**
     * Este método simula lo que sería la cancelación de una Clase al limpiar sus atributos
     * y quitar la clase del calendario.
     */
    public void cancelarClase(){
        profesor = null;
        listaEstudiantes.clear();
        asignatura = null;
        bloqueHorario = null;
        capacidadMaximaAlumnos = 0;
        tarifa = 0;
        Calendario.getInstancia().removeClase(this.getId());
    }

    // getters:
    public Map<String, Estudiante> getListaEstudiantes() {return listaEstudiantes;}
    public BloqueHorario getBloqueHorario() {return bloqueHorario;}
    public Asignatura getAsignatura() {return asignatura;}
    public Profesor getProfesor() {return profesor;}
    public int getCapacidadMaximaAlumnos() {return capacidadMaximaAlumnos;}
    public long getTarifa() {return tarifa;}

    //setters

    /**
     * Asigna un nuevo profesor a la clase tras verificar que el profesor cumpla con la
     * asignatura, horario, tarifa y capacidad requeridos para poder asignarlo a la Clase.
     * @param profesor: tal profesor.
     */
    public void setProfesor(Profesor profesor) {
        if(!profesor.getMateriasQueDicta().contains(asignatura)){throw new IllegalArgumentException("El Profesor no dicta: "+asignatura);}
        if(!profesor.getDisponibilidad().contains(bloqueHorario)){throw new IllegalArgumentException("El Profesor no esta disponible en: "+ bloqueHorario);}
        if(!profesor.getTarifas().contains(tarifa)){throw new IllegalArgumentException("El Profesor no acepta esta tarifa: "+ tarifa);}
        boolean aceptado = false;
        for (Integer capacidadPermitida : profesor.getCapacidadesMaximasAlumnos()) {
            if (capacidadPermitida >= capacidadMaximaAlumnos) {
                aceptado = true;
                break;
            }
        }
        if (!aceptado) {throw new IllegalArgumentException("El Profesor no acepta esta cantidad de alumnos: " + capacidadMaximaAlumnos);}
        this.profesor = profesor;
    }

    /**
     * Asigna una nueva asignatura a la clase tras hacer validaciones.
     * @param asignatura: tal asignatura.
     */
    public void setAsignatura(Asignatura asignatura) {
        if(!profesor.getMateriasQueDicta().contains(asignatura)){throw new IllegalArgumentException("El Profesor no dicta: "+asignatura);}
        this.asignatura = asignatura;
    }

    /**
     * Asigna un nuevo bloque a la clase tras hacer validaciones.
     * @param bloqueHorario: tal bloque.
     */
    public void setBloqueHorario(BloqueHorario bloqueHorario) {
        if(!profesor.getDisponibilidad().contains(bloqueHorario)){throw new IllegalArgumentException("El Profesor no esta disponible en: "+ bloqueHorario);}
        this.bloqueHorario = bloqueHorario;
    }

    /**
     * Asigna una nueva capacidad maxima de alumnos a la clase tras validar que el profesor
     * acepta esa cantidad de alumnos o más.
     * @param capacidadMaximaAlumnos: tal capacidad.
     */
    public void setCapacidadMaximaAlumnos(int capacidadMaximaAlumnos) {
        boolean aceptado = false;
        for (Integer capacidadPermitida : profesor.getCapacidadesMaximasAlumnos()) {
            if (capacidadPermitida >= capacidadMaximaAlumnos) {
                aceptado = true;
                break;
            }
        }
        if (!aceptado) {throw new IllegalArgumentException("El Profesor no acepta esta cantidad de alumnos: " + capacidadMaximaAlumnos);}
    }

    /**
     * Asigna una nueva tarifa a la clase tras hacer validaciones.
     * @param tarifa: tal tarifa.
     */
    public void setTarifa(long tarifa) {
        if(!profesor.getTarifas().contains(tarifa)){throw new IllegalArgumentException("El Profesor no acepta esta tarifa: "+ tarifa);}
        this.tarifa = tarifa;
    }
}