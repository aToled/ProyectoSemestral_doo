public abstract class Persona {
    private String nombre, apellido, correo, id;
    private Asignatura asignatura;
    private Horario horario;
    private Dia dia;
    public Persona(String nombre, String apellido, String correo, String id, Asignatura asignatura, Horario horario, Dia dia) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.asignatura = asignatura;
        this.correo = correo;
        this.id = id;
        this.dia = dia;
        this.horario = horario;
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
    public int getIdInt(){return Integer.valueOf(id);}
    public Dia getDia() {return dia;}
    public Horario getHorario(){return horario;}

    @Override
    public String toString(){
        return "Nombre: "+nombre+", Apellido: "+apellido+", Correo: "+correo+", ID: "+id;
    }
}
