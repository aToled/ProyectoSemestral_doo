import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.Set;

public class ProfesorFactory{

    /**
     * Clase usada para crear nuvas instancias dentro de sacarArchivo
     * @param nombre:
     * @param apellido:
     * @param correo:
     * @param id:
     * @param tarifa:
     * @return
     */
    public Profesor crearProfesor(String nombre, String apellido, String correo, String id, Set<Asignatura> materiasQueDicta, int capacidadMaximaAlumnos, long tarifa, Set<BloqueHorario> disponibilidad) {
        return new Profesor(nombre, apellido, correo, id, materiasQueDicta, capacidadMaximaAlumnos, tarifa, disponibilidad); //agregar los atributos por el Json
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
