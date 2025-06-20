public abstract class Persona {
    private String nombre, apellido;
    private Asignatura asignatura;
    public Profesor(String nombre, String apellido, Asignatura asignatura ) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.asignatura = asignatura;
        //falta agragar el horario

    }
    public String getNombre() {
        return nombre;
    }
    public String getApellido(){
        return apellido;
    }
    public Asignatura getAsignatura() {
        return asignatura;
    }
}
