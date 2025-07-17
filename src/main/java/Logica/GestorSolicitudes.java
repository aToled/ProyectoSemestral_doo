package Logica;

import Logica.Enums.EstadoSolicitud;
import Logica.Estrategias.EstrategiaSolicitud;
import com.google.gson.reflect.TypeToken;
import java.util.*;

/**
 * Clase encargada de gestionar solicitudes de estudiantes, esto siendo su: creación, resolución y notificación a observadores, además,
 * se extiende de ManejoGenericoJSON para operar sobre un conjunto de Solicitudes almacenadas localmente.
 * @see ManejoGenericoJSON
 */
public class GestorSolicitudes extends ManejoGenericoJSON<Solicitud> {
    private static GestorSolicitudes instancia;
    private final Set<ObservadorSolicitudes> listeners = new HashSet<>();

    /**
     * Carga las solicitudes del archivo JSON.
     */
    private GestorSolicitudes(){
        super("ListaSolicitudes", new TypeToken<Set<Solicitud>>(){}.getType());
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
     * Si una estrategia propone una colección de clases válida (no nulas), esta se agrega a las clases sugeridas de la Solicitud,
     * después de asignarla, se guarda el estado y se notifica a los listeners, si fallan todas las estrategias se le asigna INCONCLUSO a la solicitud.
     * @param id: Id de la solicitud.
     * @param estrategias: Las estrategias disponibles.
     * @return True si es que se encontró al menos una clase válida, si no, false.
     */
    public boolean resolver(String id, EstrategiaSolicitud... estrategias){
        Set<Clase> sugerencias = new LinkedHashSet<>();
        Solicitud sol = buscarObjeto(id);

        for(EstrategiaSolicitud e : estrategias){
            if(e.puedeAplicar(sol)){
                Set<Clase> propuestas = e.proponerClase(sol);
                if(propuestas != null && !propuestas.isEmpty()){
                    sugerencias.addAll(propuestas);
                }
            }
        }
        if(!sugerencias.isEmpty()){
            sol.setClasesSugeridas(sugerencias);
            guardar();
            notificar();
            return true;
        }else {
            sol.setEstadoSolicitud(EstadoSolicitud.INCONCLUSO);
            return false;
        }
    }

    /**
     * Acepta la solicitud con base en su ID, al agregarlo a su clase sugerida y cambiar el
     * estado de su solicitud a ACEPTADA, para después almacenar cambio en el JSON y notificar a los listeners.
     * @param id: id de la solicitud.
     */
    public void aceptar(String id){
        Solicitud s = buscarObjeto(id);
        s.getClaseElegida().agregarEstudiante(s.getEstudiante());
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
        Solicitud s = buscarObjeto(id);
        s.setEstadoSolicitud(EstadoSolicitud.RECHAZADA);
        guardar();
        notificar();
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
        o.actualizar(super.objetos);
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
     * Actualiza las solicitudes en el JSON y notifica a los Listener.
     */
    public static void actualizar(){
        instancia.guardar();
        instancia.notificar();
    }
}