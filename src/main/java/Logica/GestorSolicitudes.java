package Logica;

import Logica.Enums.EstadoSolicitud;
import Logica.Estrategias.EstrategiaSolicitud;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.*;

public class GestorSolicitudes extends ManejoGenericoJSON<Solicitud> {
    private static final Type listType = new TypeToken<Set<Solicitud>>(){}.getType();

    private static GestorSolicitudes instancia;
    private final List<ObservadorSolicitudes> listeners = new ArrayList<>();

    /**
     * Carga las solicitudes del archivo JSON.
     */
    private GestorSolicitudes(){
        super("ListaSolicitudes", listType);
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
     * @see ManejoGenericoJSON, además, notifica a los listeners.
     * @param s: Solicitud a agregar.
     */
    public void agregar(Solicitud s){
        super.agregar(s);
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
        for(Solicitud s : super.objetos){
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
            o.actualizar(super.objetos);
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
     * @see ManejoGenericoJSON, ademas, notifica a los Listener.
     * @param id: Id de la solicitud a eliminar.
     */
    public void eliminar(String id){
        super.eliminar(id);
        notificar();
    }

    /**
     * @see ManejoGenericoJSON, ademas, notifica a los Listener.
     */
    public void eliminarTodas() {
        super.eliminarTodas();
        notificar();
    }

    /**
     * Devuelve el Set de solicitudes, pero no modificable. (para obligar a usar los métodos agregar, eliminar
     * y eliminarTodas, ya que podrían generarse incongruencias entre el JSON y la lista de solicitudes
     * si se modificase la lista interna sin usar esos métodos)
     * @return tal lista.
     */
    public Set<Solicitud> getSolicitudesNoModificable() {
        return Collections.unmodifiableSet(super.objetos);
    }
}
