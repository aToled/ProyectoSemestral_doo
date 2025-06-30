package Logica;

import java.util.ArrayList;
import java.util.List;

public class GestorSolicitudes {
    private static GestorSolicitudes instacia;
    private final List<Solicitud> solicitudes;

    private GestorSolicitudes(){ solicitudes = new ArrayList<>();}

    public static GestorSolicitudes getInstacia(){
        if (instacia == null){
            instacia = new GestorSolicitudes();
        }
        return instacia;
    }

    public void agregar(Solicitud s){
        solicitudes.add(s);
        // guardar(); TODO implementar JSON de Solicitudes
    }

    public boolean resolver(String id){
    }

    public void aceptar(String id){

    }

    public void rechazar(String id){}
}
