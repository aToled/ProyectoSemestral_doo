public class Profesor extends Persona {
    private int cantidadAlumnos;

    public Profesor(String nombre, String apellido, Asignatura asignatura, int cantidadAlumnos) {
        super(nombre, apellido, asignatura);
        this.cantidadAlumnos = cantidadAlumnos;
    }
}
