public abstract class Persona {
    private String nombre, apellido, correo, id;
    private Asignatura asignatura;
    public Persona(String nombre, String apellido, String correo, String id, Asignatura asignatura) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.asignatura = asignatura;
        this.correo = correo;
        this.id = id;
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
    public String getCorreo() {
        return correo;
    }
    public String getId() {
        return id;
    }

    @Override
    public String toString(){
        return "Nombre: "+nombre+", Apellido: "+apellido+", Correo: "+correo+", ID: "+id;
    }
}
