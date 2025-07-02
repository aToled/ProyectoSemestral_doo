package Logica;

import Logica.Enums.EstadoSolicitud;
import Logica.Estrategias.EstrategiaSolicitud;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class GestorSolicitudes {
    private static final String rutaArchivoSolicitudes = "src/main/resources/ListaSolicitudes.JSON";
    private static final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    private static GestorSolicitudes instancia;
    private final List<Solicitud> solicitudes;
    private final List<ObservadorSolicitudes> listeners = new ArrayList<>();

    /**
     * Carga las solicitudes del archivo JSON.
     */
    private GestorSolicitudes(){
        this.solicitudes = cargar();
    }

    /**
     * Creación de la instancia. (uso de singleton)
     * @return Retorna la instancia.
     */
    public static GestorSolicitudes getInstancia(){
        if (instancia == null){
            instancia = new GestorSolicitudes();
        }
        return instancia;
    }

    /**
     * Agrega una solicitud a la lista que usa la clase y la almacena en el JSON, además, notifica a los listeners.
     * @param s: Solicitud a agregar.
     */
    public void agregar(Solicitud s){
        solicitudes.add(s);
        guardar();
        notificar();
    }

    /**
     * Intenta resolver una solicitud utilizando distintas estrategias, se busca la solicitud según el ID proporcionado
     * y luego se recorren las estrategias para ver si alguna puede aplicarse.
     * Si una estrategia propone una clase válida (no nula), esta se asigna a la solicitud como clase sugerida,
     * después de asignarla, se guarda el estado y se notifica a los listeners, si fallan todas las estrategias se le asigna INCONCLUSO a la solicitud.
     * @param id: Id de la solicitud.
     * @param estrategias: Las estrategias disponibles.
     * @return True si es que se encontró al menos una clase válida, si no, false.
     */
    public boolean resolver(String id, EstrategiaSolicitud... estrategias){
        Solicitud sol = buscarSolicitud(id);
        for(EstrategiaSolicitud e : estrategias){
            if(e.puedeAplicar(sol)){
                Clase propuesta = e.proponerClase(sol);
                if(propuesta != null){
                    sol.setClaseSugerida(propuesta);
                    guardar();
                    notificar();
                    return true;
                }
            }
        }
        sol.setEstadoSolicitud(EstadoSolicitud.INCONCLUSO);
        return false;
    }

    /**
     * Acepta la solicitud con base en su ID, al agregarlo a su clase sugerida y cambiar el
     * estado de su solicitud a ACEPTADA, para después almacenar cambio en el JSON y notificar a los listeners.
     * @param id: id de la solicitud.
     */
    public void aceptar(String id){
        Solicitud s = buscarSolicitud(id);
        s.getClaseSugerida().agregarEstudiante(s.getEstudiante());
        s.setEstadoSolicitud(EstadoSolicitud.ACEPTADA);
        guardar();
        notificar();
    }

    /**
     * Rechaza la solicitud con base en su ID, al cambiar el
     * estado de su solicitud a RECHAZADA, para después almacenar el cambio en el JSON y notificar a los listeners.
     * @param id: id de la solicitud.
     */
    public void rechazar(String id){
        Solicitud s = buscarSolicitud(id);
        s.setEstadoSolicitud(EstadoSolicitud.RECHAZADA);
        guardar();
        notificar();
    }

    /**
     * Dado el ID busca entre la lista de solicitudes si hay alguna que coincida.
     * @param id: Id de la solicitud.
     * @return La referencia a la solicitud encontrada o null si no la encuentra.
     */
    private Solicitud buscarSolicitud(String id){
        for(Solicitud s : solicitudes){
            if(Objects.equals(s.getId(), id)){
                return s;
            }
        }
        throw new NoSuchElementException("Solicitud: " + id + " no encontrada");
    }

    /**
     * Método para notificar a los listeners, actualizando sus listas de solicitudes
     * cada vez que se modifica la lista de solicitudes en esta clase.
     */
    private void notificar(){
        for(ObservadorSolicitudes o : listeners){
            o.actualizar(solicitudes);
        }
    }

    /**
     * Agrega un listener a la lista interna de Listeners de la clase.
     * @param o: el listener u observador.
     */
    public void suscribir(ObservadorSolicitudes o){
        listeners.add(o);
    }

    /**
     * Elimina a un listener de la lista interna de Listeners de la clase.
     * @param o: el listener u observador.
     */
    public void deSuscribir(ObservadorSolicitudes o){
        listeners.remove(o);
    }

    /**
     * Carga las solicitudes que contiene el JSON como un List (y si por alguna razón está vacío u ocurre una excepción
     * devuelve un ArrayList vacío).
     * @return conjunto de-serializado de las solicitudes.
     */
    private List<Solicitud> cargar(){
        try(Reader reader = Files.newBufferedReader(Path.of(rutaArchivoSolicitudes))){
            Type listType = new TypeToken<List<Solicitud>>(){}.getType();
            List<Solicitud> lista = gson.fromJson(reader, listType);
            if (lista == null) {
                lista = new ArrayList<>();
            }
            return lista;
        }catch (IOException e){
            return new ArrayList<>();
        }
    }

    /**
     * Serializa la lista de solicitudes de esta clase, si ocurre una excepción se ignora.
     */
    private void guardar(){
        try(Writer writer = Files.newBufferedWriter(Path.of(rutaArchivoSolicitudes))){
            gson.toJson(solicitudes, writer);
        } catch (IOException _){}
    }

    /**
     * Elimina una Solicitud de la lista al identificarla por su ID, al quitarla, y guardarla
     * además notifica a los Listener este cambio.
     * @param id: Id de la solicitud a eliminar.
     */
    public void eliminar(String id){
        solicitudes.removeIf(s -> s.getId().equals(id));
        guardar();
        notificar();
    }

    /**
     * Mismo proceso que el método anterior, pero borra todas las solicitudes.
     */
    public void eliminarTodas() {
        solicitudes.clear();
        guardar();
        notificar();
    }

    /**
     * Devuelve la lista de solicitudes, pero no modificable. (para obligar a usar los métodos agregar, eliminar
     * y eliminarTodas, ya que podrían generarse incongruencias entre el JSON y la lista de solicitudes
     * si se modificase la lista interna sin usar esos métodos)
     * @return tal lista.
     */
    public List<Solicitud> getSolicitudesNoModificable() {
        return Collections.unmodifiableList(solicitudes);
    }
}
