package Logica;

public class EstrategiaDefault implements EstrategiaSolicitud{

    /**
     * Como es la estrategia por defecto, se puede aplicar a todas las solicitudes.
     * (Idealmente es la ultima que se ejecuta)
     * @param s: la solicitud a evaluar.
     * @return .
     */
    @Override
    public boolean puedeAplicar(Solicitud s) {
        return true;
    }

    /**
     * Devuelve la primera clase con un cupo disponible, tomando el estudiante
     * y la asignatura asociadas a la solicitud, si no hay ninguna disponible devuelve null.
     * @param s: tal asignatura.
     * @param calendario: tal estudiante.
     * @return tal clase.
     */
    @Override
    public Clase proponerClase(Solicitud s, Calendario calendario) {
        return null;  //TODO hacer metodo.
    }
}
