package Logica;

import Logica.Enums.Asignatura;
import java.util.Set;

public class Estudiante extends Persona {
    private Set<Asignatura> materiasInteres;
    public Estudiante(String nombre, String apellido, String correo, String id, Set<Asignatura> materiasInteres) {
        super(nombre, apellido, correo, id);
        this.materiasInteres = materiasInteres;
    }

    /**
     * Envia una solicitud al sistema sobre alguna asignatura en particular, y retorna la solicitud enviada. (para mostrarla en la GUI)
     * (en realidad agrega una solicitud con un ID unica a la lista interna de solicitudes)
     * @param a: tal asignatura.
     * @return la solicitud.
     */
    public Solicitud enviarSolicitud(Asignatura a){
        String idSolicitud = getId() + "_" + (int)(Math.random() * 100000);
        Solicitud s = new Solicitud(idSolicitud, this, a);
        GestorSolicitudes.getInstacia().agregar(s);
        return s;
    }

    public Set<Asignatura> getMateriasInteres() {
        return materiasInteres;
    }
}
