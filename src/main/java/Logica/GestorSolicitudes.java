package Logica;

import Logica.Enums.EstadoSolicitud;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class GestorSolicitudes {
    private static final String rutaArchivoSolicitudes = "src/main/resources/ListaSolicitudes.JSON";
    private static final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    private static GestorSolicitudes instacia;
    private final List<Solicitud> solicitudes;

    /**
     * Carga las solicitudes del archivo JSON.
     */
    private GestorSolicitudes(){
        this.solicitudes = cargar();
    }

    /**
     * Creacion de la instancia. (uso de singleton)
     * @return Retorna la instancia.
     */
    public static GestorSolicitudes getInstacia(){
        if (instacia == null){
            instacia = new GestorSolicitudes();
        }
        return instacia;
    }

    /**
     * Agrega una solicitud a la lista que usa la clase y la almacena en el JSON.
     * @param s: Solicitud a agregar.
     */
    public void agregar(Solicitud s){
        solicitudes.add(s);
        guardar();
    }

    /**
     * Dado el ID y un conjunto de estrategias, itera entre las estrategias para determinar
     * si alguna es aplicable, al encontrar una la ejecuta, si falla sigue hasta que se acaben las estrategias o alguna
     * no falle para poder sugerir alguna clase valida, la cual se almacena inmediatamente despues de asignar la clase Sugerida a la Solicitud.
     * @param id
     * @param estrategias
     * @return True si es que se encontro al menos una clase valida, si no, false.
     */
    public boolean resolver(String id, EstrategiaSolicitud... estrategias){
        Solicitud sol = buscarSolicitud(id);
        Calendario cal = Calendario.getInstancia();
        for(EstrategiaSolicitud e : estrategias){
            if(e.puedeAplicar(sol)){
                Clase propuesta = e.proponerClase(sol, cal);
                if(propuesta != null){
                    sol.setClaseSugerida(propuesta);
                    guardar();
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Acepta la solicitud con base en su ID, al agregarlo a su clase sugerida y cambiar el
     * estado de su solicitud a ACEPTADA, para después almacenar cambio en el JSON.
     * @param id
     */
    public void aceptar(String id){
        Solicitud s = buscarSolicitud(id);
        s.getClaseSugerida().agregarEstudiante(s.getEstudiante());
        s.setEstadoSolicitud(EstadoSolicitud.ACEPTADA);
        guardar();
    }

    /**
     * Rechaza la solicitud con base en su ID, al cambiar el
     * estado de su solicitud a RECHAZADA, para después almacenar el cambio en el JSON.
     * @param id
     */
    public void rechazar(String id){
        Solicitud s = buscarSolicitud(id);
        s.setEstadoSolicitud(EstadoSolicitud.RECHAZADA);
        guardar();
    }

    /**
     * Dado el ID busca entre la lista de solicitudes si hay alguna que coincida, si
     * la encuentra devuelve una referencia y si no devuelve null.
     * @param id
     * @return
     */
    private Solicitud buscarSolicitud(String id){
        for(Solicitud s : solicitudes){
            if(Objects.equals(s.getId(), id)){
                return s;
            }
        }
        return null;
    }

    /**
     * Carga las solicitudes que contiene el JSON como un List (y si por alguna razon está vacio u ocurre una excepcion
     * devuelve un ArrayList vacio).
     * @return conjunto deserializado de las solicitudes.
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
     * Serializa la lista de solicitudes de esta clase, si ocurre una excepcion se ignora.
     */
    private void guardar(){
        try(Writer writer = Files.newBufferedWriter(Path.of(rutaArchivoSolicitudes))){
            gson.toJson(solicitudes, writer);
        } catch (IOException _){}
    }
}
