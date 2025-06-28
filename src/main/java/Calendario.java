import java.util.HashMap;
import java.util.Map;

/**
 * Clase calendario donde se almacenaran las clases
 */
public class Calendario {
    private static Calendario instancia;
    private Map<String, Clase> calendario;

    private Calendario() {
        calendario = new HashMap<>();
    }

    /**
     * Creacion de la instancia(uso de sigleton)
     * @return Retorna la instancia
     */
    public static Calendario getInstancia(){
        if(instancia == null){
            instancia = new Calendario();
            return instancia;
        }
        else{return instancia;}
    }

    public void addClase(Clase clase){

    }
}
