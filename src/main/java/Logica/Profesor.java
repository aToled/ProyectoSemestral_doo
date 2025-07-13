package Logica;

import Logica.Enums.Asignatura;
import java.util.Set;

public class Profesor extends Persona {
    private final Set<Long> tarifas;
    private final Set<Integer> capacidadesMaximasAlumnos;
    private final Set<Asignatura> materiasQueDicta;
    private final Set<BloqueHorario> disponibilidad;

    public Profesor(String nombre, String apellido, String correo, String id, Set<Integer> capacidadesMaximasAlumnos, Set<Long> tarifas, Set<Asignatura> materiasQueDicta, Set<BloqueHorario> disponibilidad) {
        super(nombre, apellido, correo, id);
        this.capacidadesMaximasAlumnos = capacidadesMaximasAlumnos;
        this.tarifas = tarifas;
        this.materiasQueDicta = materiasQueDicta;
        this.disponibilidad = disponibilidad;
    }

    public Set<Integer> getCapacidadesMaximasAlumnos(){
        return capacidadesMaximasAlumnos;
    }
    public Set<Asignatura> getMateriasQueDicta() {
        return materiasQueDicta;
    }
    public Set<BloqueHorario> getDisponibilidad() {
        return disponibilidad;
    }
    public Set<Long> getTarifas(){
        return tarifas;
    }
}
