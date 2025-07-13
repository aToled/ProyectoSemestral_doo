package Logica;

import Logica.Enums.Asignatura;
import java.util.HashMap;
import java.util.Map;

public class Clase implements Identificable{

    private final Profesor profesor;
    private final Map<String,Estudiante> listaEstudiantes;
    private final Asignatura asignatura;
    private final String id;
    private final BloqueHorario bloqueHorario;
    private final int capacidadMaximaAlumnos;
    private final long tarifa;

    public Clase(Profesor profesor, String id, Asignatura asignatura, BloqueHorario bloque, int capacidadMaximaAlumnos, long tarifa) {
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
        this.id = id;
        this.asignatura = asignatura;
        this.bloqueHorario = bloque;
        this.capacidadMaximaAlumnos = capacidadMaximaAlumnos;
        this.tarifa = tarifa;
    }

    /**
     * Agrega un Estudiante a la clase tras hacer las verificaciones necesarias.
     * @param e: tal Estudiante
     * @return Booleano si es que se pudo realizar la operación
     */
    public boolean agregarEstudiante(Estudiante e) {
        if (e.getMateriasInteres().contains(asignatura)) {
            if (listaEstudiantes.size() < capacidadMaximaAlumnos) {
                listaEstudiantes.putIfAbsent(e.getId(), e);
                System.out.println(e.getNombre()+" "+ e.getApellido()+" Se agrego con éxito");
                return true;
            }else{
                System.out.println(e.getNombre()+" "+ e.getApellido()+" No se pudo agregar");
                return false;
            }
        }else{
            System.out.println(e.getNombre()+" "+ e.getApellido()+" No se pudo agregar");
            return false;
        }
    }

    /**
     * Verifica si la Clase esta llena o no.
     * @return True si la lista es mayor o igual a la cantidad maxima permitida por el profesor.
     */
    public boolean isLlena(){
        return listaEstudiantes.size() >= capacidadMaximaAlumnos;
    }

    public void eliminarEstudiante(String idEstudiante) {
        listaEstudiantes.remove(idEstudiante);
    }

    public int cantidadEstudiantes(){
        return listaEstudiantes.size();
    }

    public Map<String, Estudiante> getListaEstudiantes() {return listaEstudiantes;}
    public BloqueHorario getBloqueHorario() {return bloqueHorario;}
    public Asignatura getAsignatura() {return asignatura;}
    public Profesor getProfesor() {return profesor;}
    public String getId(){return id;}
    public int getCapacidadMaximaAlumnos() {return capacidadMaximaAlumnos;}
    public long getTarifa() {return tarifa;}

    @Override
    public boolean equals(Object o){
        if(o == this) return true;
        if(!(o instanceof Persona otro)) return false;
        return getId().equals(otro.getId());
    }

    @Override
    public final int hashCode() {
        if(id != null){
            return id.hashCode();
        } else {
            return 0;
        }
    }
}
