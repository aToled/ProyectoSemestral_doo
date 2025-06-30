import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import Enums.*;
import Excepciones.*;

/**
 * Clase calendario donde se almacenaran las clases
 */
public class Calendario {
    private static Calendario instancia;

    private final Map<BloqueHorario, List<Clase>> calendario;

    private Calendario() {
        calendario = new HashMap<>();
    }

    /**
     * Creacion de la instancia(uso de singleton)
     *
     * @return Retorna la instancia
     */
    public static Calendario getInstancia() {
        if (instancia == null) {
            instancia = new Calendario();
        }
        return instancia;
    }

    /**
     * Agrega la clase del parametro a un bloque del horario, además, si no existe ese bloque crea una nueva lista
     * de clases para ese horario, y antes de agregar el bloque se verifica que el profesor no esté cursando ya en ese horario alguna otra clase.
     * @param clase: la clase en cuestion.
     * @throws ConflictoHorarioException .
     */
    public void addClaseToBloque(Clase clase) throws ConflictoHorarioException {
        BloqueHorario bloque = clase.getBloqueHorario();
        List<Clase> clasesEnBloque = calendario.computeIfAbsent(bloque, _ -> new ArrayList<>());

        for (Clase c : clasesEnBloque) {
            if (c.getProfesor().equals(clase.getProfesor())) {
                throw new ConflictoHorarioException("El profesor ya tiene una clase en ese horario");
            }
        }
        clasesEnBloque.add(clase);
    }

    /**
     * Devuelve una lista con todas las clases en un bloque basándose en su hora y dia.
     * @param dia: tal dia
     * @param horario: tal hora
     * @return la lista o un NULL si es que esta vacio el bloque.
     */
    public List<Clase> getClasesEnBloque(Dia dia, Horario horario){
        BloqueHorario bloque = new BloqueHorario(dia, horario);
        return calendario.get(bloque);
    }
}
