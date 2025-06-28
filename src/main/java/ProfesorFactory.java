import java.io.FileReader;
import java.util.ArrayList;

public class ProfesorFactory{

    /**
     * Clase usada para crear nuvas instancias dentro de sacarArchivo
     * @param nombre
     * @param apellido
     * @param correo
     * @param id
     * @param asignatura
     * @param cantidadEstudiantes
     * @param horario
     * @param dia
     * @return
     */
    private Persona crearProfe(String nombre, String apellido, String correo,String id, Asignatura asignatura, int cantidadEstudiantes, Horario horario, Dia dia) {
        return new Profesor(nombre,apellido,correo,id,asignatura,cantidadEstudiantes,horario,dia); //agregar los atributos por el Json
    }
    public static ArrayList<Profesor> sacarArchivo(){
        String nombreArchivo = "ListaProfesores.JSON";
        //Gson gson = new Gson();
        //falta agregar gson al proyecto
        ArrayList<Profesor> lista = new ArrayList<>();
        //FileReader lector = new FileReader();

        return  lista;
    }

    /**
     * Crea una nueva instancia y la agrega al archivo json
     */
    public static void nuevoProfe(){}
}
