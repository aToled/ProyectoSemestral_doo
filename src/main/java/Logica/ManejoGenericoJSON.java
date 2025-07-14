package Logica;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

/**
 * Clase cuyo único propósito es proporcionar métodos para el Manejo de un Set de Objetos <T> dentro de un archivo JSON.
 * (Estudiantes, Profesores, Solicitudes)
 * @param <T>: El tipo de Objeto que con el que se Trabaja.
 */
public abstract class ManejoGenericoJSON <T extends Identificable> {
    private final Gson gson = new GsonBuilder().setPrettyPrinting().create();
    private String ruta = "src/main/resources/";
    private final Type listType;
    protected final Set<T> objetos;

    protected ManejoGenericoJSON(String nombre, Type listType){
        this.ruta += nombre + ".JSON";
        this.listType = listType;
        this.objetos = cargar();
    }

    /**
     * Carga los Objetos <T> que contiene el JSON como un Set (y si por alguna razón está vacío u ocurre una excepción
     * devuelve un HashSet vacío).
     * @return conjunto de-serializado de los Objetos.
     */
    protected Set<T> cargar(){
        try (Reader reader = Files.newBufferedReader(Path.of(ruta))){
            Set<T> set = gson.fromJson(reader, listType);
            if (set == null) {
                set = new HashSet<>();
            }
            return set;
        } catch (IOException e){
            return new HashSet<>();
        }
    }

    /**
     * Serializa el Set de Objetos <T> de esta clase, si ocurre una excepción se ignora.
     */
    protected void guardar(){
        try(Writer writer = Files.newBufferedWriter(Path.of(ruta))){
            gson.toJson(objetos, writer);
        } catch (IOException _) {}
    }

    /**
     * Agrega un Objeto de tipo <T> al Set que usa la clase y la almacena en el JSON.
     * @param objeto: Objeto a agregar.
     */
    protected void agregar(T objeto){
        objetos.add(objeto);
        guardar();
    }

    /**
     * Elimina un Objeto tipo <T> del Set interno al identificarlo por su ID para luego quitarlo
     * y volver a guardar el Set
     * @param id: Id del Objeto a eliminar.
     */
    protected void eliminar(String id){
        objetos.removeIf(T -> T.getId().equals(id));
        guardar();
    }

    /**
     * Mismo proceso que el método anterior, pero borra todos los Objetos.
     */
    protected void eliminarTodas(){
        objetos.clear();
        guardar();
    }

    /**
     * Devuelve el Set de objetos, pero no modificable. (para obligar a usar los métodos agregar(), eliminar()
     * y eliminarTodas(), ya que podrían generarse incongruencias entre el JSON y la lista de objetos
     * si se modificase la lista interna sin usar esos métodos)
     * @return tal lista.
     */
    public Set<T> getObjetosNoModificable() {
        return Collections.unmodifiableSet(objetos);
    }

    /**
     * @return la cantidad de elementos en el Set.
     */
    public int getCantidadObjetos() {
        return objetos.size();
    }

    /**
     * Dado el ID busca entre la lista de Objetos si hay alguno que coincida.
     * @param id: Id del objeto.
     * @return La referencia al objeto encontrado o null si no la encuentra.
     */
    public T buscarObjeto(String id){
        for(T o: objetos){
            if(Objects.equals(o.getId(), id)){
                return o;
            }
        }
        throw new NoSuchElementException("Objeto: " + id + " no encontrada");
    }
}