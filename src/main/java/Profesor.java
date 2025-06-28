public class Profesor extends Persona {
    private int cantidadAlumnos;
    private long tarifa;

    public Profesor(String nombre, String apellido, String correo, String id, Asignatura asignatura, int cantidadAlumnos, Horario horario, Dia dia) {
        super(nombre, apellido, correo, id, asignatura, horario, dia);
        this.cantidadAlumnos = cantidadAlumnos;
    }

    public int getCantidadEstudiantes(){
        return cantidadAlumnos;
    }

    public long getTarifa(){
        return tarifa;
    }

    public int getCantidadAlumnos(){
        return cantidadAlumnos;
    }
}
