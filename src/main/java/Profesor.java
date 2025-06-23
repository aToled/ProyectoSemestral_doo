public class Profesor extends Persona {
    private int cantidadAlumnos;
    private long tarifa;

    public Profesor(String nombre, String apellido, String correo, String id, Asignatura asignatura, int cantidadAlumnos) {
        super(nombre, apellido, correo, id, asignatura);
        this.cantidadAlumnos = cantidadAlumnos;
    }

    public int getCantidadAlumnos(){
        return cantidadAlumnos;
    }

    public long getTarifa(){
        return tarifa;
    }
}
