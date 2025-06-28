public class EstudianteFactory{

    public static Persona crearEstud(String nombre, String apellido, String correo,String id, Asignatura asignatura, Horario horario, Dia dia) {
        return new Estudiante(nombre,apellido,correo,id,asignatura,horario,dia); //agregar los atributos por el Json
    }
}
