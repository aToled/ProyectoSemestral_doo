import Enums.Asignatura;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class EstudianteFactory{
    private static final String nombreArchivo = "ListaEstudiantes.JSON";
    private static final Gson gson = new Gson();

    public static Estudiante crearEstudiante(String nombre, String apellido, String correo, String id, Set<Asignatura> materiasInteres) {
        return new Estudiante(nombre, apellido, correo, id, materiasInteres); //agregar los atributos por el Json
    }

    /**
     * Carga los estudiantes que contiene el JSON como un List (y si por alguna razon está vacio devuelve un ArrayList vacio).
     * @return conjunto deserializado de los estudiantes.
     * @throws IOException : si es que ocurre un error en la lectura del Archivo.
     */
    public static List<Estudiante> cargarEstudiantes() throws IOException {
        try(Reader reader = Files.newBufferedReader(Path.of(nombreArchivo))){
            Type listType = new TypeToken<List<Estudiante>>(){}.getType();
            List<Estudiante> lista = gson.fromJson(reader, listType);
            if (lista == null) {
                lista = new ArrayList<>();
            }
            return lista;
        }
    }

    /**
     * Serializa la lista de Estudiantes indicada en los parametros.
     * @param estudiantes: lista a serializar.
     * @throws IOException: si es que ocurre un error en la escritura del Archivo.
     */
    public static void guardarEstudiantes(List<Estudiante> estudiantes) throws IOException{
        try(Writer writer = Files.newBufferedWriter(Path.of(nombreArchivo))){
            gson.toJson(estudiantes, writer);
        }
    }

    /**
     * Agrega un Estudiante a la lista de estudiantes que contiene el JSON al
     * deserealizar la lista, añadiendolo y volviendola a serializar.
     * @param estudiante: tal estudiante
     * @throws IOException si es que ocurre un error en la escritura del Archivo.
     */
    public static void agregarProfesor(Estudiante estudiante) throws IOException{
        List<Estudiante> estudiantes = cargarEstudiantes();
        estudiantes.add(estudiante);
        guardarEstudiantes(estudiantes);
    }
}
