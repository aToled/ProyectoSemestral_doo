public class Estudiante extends Persona{
    private int idClase;
    public Estudiante(String nombre, String apellido, String correo, String id, Asignatura asignatura,Horario horario ,Dia dia) {
        super(nombre, apellido, correo, id, asignatura, horario, dia);
    }

    public void setClase(Clase clase){
        idClase = clase.getId();
    }
}
