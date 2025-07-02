package Logica;

import Logica.Enums.Asignatura;
import Logica.Excepciones.*;
import java.util.HashMap;
import java.util.Map;

public class Clase implements Identificable{

    private final Profesor profesor;
    private final Map<String,Estudiante> listaEstudiantes;
    private final Asignatura asignatura;
    private final String id;
    private final BloqueHorario bloqueHorario;

    public Clase(Profesor profesor, String id, Asignatura asignatura, BloqueHorario bloque) throws ProfesorNoDictaMateriaException {
        if(!profesor.getMateriasQueDicta().contains(asignatura)){
            throw new ProfesorNoDictaMateriaException("El Profesor no dicta: "+asignatura);
        }
        if(!profesor.getDisponibilidad().contains(bloque)){
            throw new ProfesorNoDisponibleException("Profesor no disponible en "+ bloque);
        }
        listaEstudiantes = new HashMap<>();
        this.profesor = profesor;
        this.id = id;
        this.asignatura = asignatura;
        this.bloqueHorario = bloque;
    }

    /**
     * Agrega un Estudiante a la clase tras hacer las verificaciones necesarias.
     * @param e: tal Estudiante
     * @return Booleano si es que se pudo realizar la operación
     */
    public boolean agregarEstudiante(Estudiante e) {
        if (e.getMateriasInteres().contains(asignatura)) {
            if (listaEstudiantes.size() < profesor.getCapacidadMaximaAlumnos()) {
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
        return listaEstudiantes.size() >= profesor.getCapacidadMaximaAlumnos();
    }

    /**
     * Método utilitario que válida si un estudiante pertenece o no a la clase.
     * @param e: dicho estudiante.
     * @return valor booleano que depende de si el estudiante está en el Map.
     */
    public boolean estudianteEnClase(Estudiante e){
        return listaEstudiantes.containsValue(e);
    }

    public void eliminarEstudiante(String idEstudiante) {
        listaEstudiantes.remove(idEstudiante);
    }

    public int cantidadEstudiantes(){
        return listaEstudiantes.size();
    }

    public Map<String, Estudiante> getListaEstudiantes() {
        return listaEstudiantes;
    }
    public BloqueHorario getBloqueHorario() {
        return bloqueHorario;
    }
    public Asignatura getAsignatura() {
        return asignatura;
    }
    public Profesor getProfesor() {
        return profesor;
    }
    public String getId(){
        return id;
    }

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
