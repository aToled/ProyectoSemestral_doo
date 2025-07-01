package Logica;

import Logica.Enums.Asignatura;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import java.io.*;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class ProfesorFactory{
    private static final String rutaArchivoProfesor = "src/main/resources/ListaProfesores.JSON";
    private static final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    /**
     * Clase usada para crear nuvas instancias dentro de sacarArchivo
     * @param nombre:
     * @param apellido:
     * @param correo:
     * @param id:
     * @param capacidadMaximaAlumnos:
     * @param tarifa:
     * @param materiasQueDicta:
     * @param disponibilidad:
     * @return .
     */
    public static Profesor crearProfesor(String nombre, String apellido, String correo, String id, int capacidadMaximaAlumnos, long tarifa, Set<Asignatura> materiasQueDicta, Set<BloqueHorario> disponibilidad) {
        if(capacidadMaximaAlumnos<=0)
            throw new IllegalArgumentException("La capacidad maxima debe ser mayor a 0");
        if(materiasQueDicta==null || materiasQueDicta.isEmpty())
            throw new IllegalArgumentException("El Profesor debe dictar como minimo 1 asignatura");
        if(disponibilidad==null || disponibilidad.isEmpty())
            throw new IllegalArgumentException("El Profesor debe tener al menos un bloque disponible");
        return new Profesor(nombre, apellido, correo, id, capacidadMaximaAlumnos, tarifa, materiasQueDicta, disponibilidad); //agregar los atributos por el Json
    }

    /**
     * Carga los profesores que contiene el JSON como un List (y si por alguna razon está vacio devuelve un ArrayList vacio).
     * (<a href="https://stackoverflow.com/questions/5554217/deserialize-a-listt-object-with-gson">...</a>)
     * @return conjunto deserializado de los profesores.
     * @throws IOException: si es que ocurre un error en la lectura del Archivo.
     */
    public static List<Profesor> cargarProfesores() throws IOException {
        try(Reader reader = Files.newBufferedReader(Path.of(rutaArchivoProfesor))){
            Type listType = new TypeToken<List<Profesor>>(){}.getType();
            List<Profesor> lista = gson.fromJson(reader, listType);
            if (lista == null) {
                lista = new ArrayList<>();
            }
            return lista;
        }
    }

    /**
     * Serializa la lista de profesores indicada en los parametros.
     * @param profesores: lista a serializar.
     * @throws IOException: si es que ocurre un error en la escritura del Archivo.
     */
    public static void guardarProfesores(List<Profesor> profesores) throws IOException{
        try(Writer writer = Files.newBufferedWriter(Path.of(rutaArchivoProfesor))){
            gson.toJson(profesores, writer);
        }
    }

    /**
     * Agrega un Profesor a la lista de profesores que contiene el JSON al
     * deserealizar la lista, añadiendolo y volviendola a serializar.
     * @param profesor: tal profesor
     * @throws IOException si es que ocurre un error en la escritura del Archivo.
     */
    public static void agregarProfesor(Profesor profesor) throws IOException{
        List<Profesor> profesores = cargarProfesores();
        profesores.add(profesor);
        guardarProfesores(profesores);
    }

    /**
     * Elimina un Profesor de la lista al identificarlo por su ID, sigue el mismo
     * proceso que el metodo anterior.
     * @param id: Id del profesor a eliminar.
     * @throws IOException si es que ocurre un error en la escritura del Archivo.
     */
    public static void eliminarProfesor(String id) throws IOException{
        List<Profesor> profesores = cargarProfesores();
        profesores.removeIf(p -> p.getId().equals(id));
        guardarProfesores(profesores);
    }
}
