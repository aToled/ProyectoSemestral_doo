package Logica;

import com.google.gson.reflect.TypeToken;

import java.util.List;
import java.util.Set;

/**
 * Clase encargada de gestionar la persistencia de las clases del calendario en un archivo JSON.
 * Extiende ManejoGenericoJSON para ofrecer funciones de guardar, cargar y buscar clases.
 * @see ManejoGenericoJSON
 */
public class ManejoClasesJSON extends ManejoGenericoJSON<Clase> {
    private static ManejoClasesJSON instancia;

    ManejoClasesJSON(){
        super("ClasesCalendario", new TypeToken<Set<Clase>>() {}.getType());
    }

    /**
     * Creación de la instancia. (uso de singleton)
     * @return Retorna la instancia
     */
    public static ManejoClasesJSON getInstancia(){
        if (instancia == null) {
            instancia = new ManejoClasesJSON();
        }
        return instancia;
    }

    /**
     * Guarda una lista de clases recibida en el archivo JSON, reemplazando el contenido anterior.
     * @param clases lista de clases a serializar
     */
    public void guardarClasesDesdeCalendario(List<Clase> clases) {
        eliminarTodas();
        for (Clase c : clases) {
            agregar(c);
        }
    }
}