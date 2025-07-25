package Logica;

import java.util.*;

import Logica.Enums.*;
import Logica.Excepciones.*;
import Logica.Identificables.Clase;

/**
 * Clase calendario donde se almacenaran las clases, las almacena en el disco.
 * @see ManejoClasesJSON
 */
public class Calendario extends ManejoClasesJSON{
    private static Calendario instancia;

    private final Map<BloqueHorario, List<Clase>> calendario;

    private Calendario() {
        calendario = new HashMap<>();
        cargarDesdeJSON();
    }

    /**
     * Creación de la instancia. (uso de singleton)
     * @return Retorna la instancia
     */
    public static Calendario getInstancia() {
        if (instancia == null) {
            instancia = new Calendario();}
        return instancia;
    }

    /**
     * Agrega la clase del parámetro a un bloque del horario, además, si no existe ese bloque crea una nueva lista
     * de clases para ese horario, y antes de agregar el bloque se verifica que el profesor no esté cursando ya en ese horario alguna otra clase.
     * Guarda el estado actual del Calendario en el JSON.
     * @param clase: la clase en cuestión.
     * @throws ConflictoHorarioException .
     */
    public void addClaseToBloque(Clase clase) {
        BloqueHorario bloque = clase.getBloqueHorario();
        List<Clase> clasesEnBloque = calendario.computeIfAbsent(bloque, _ -> new ArrayList<>());

        for (Clase c : clasesEnBloque) {
            if (c.getProfesor().equals(clase.getProfesor())) {
                throw new ConflictoHorarioException("El profesor ya tiene una clase en ese horario");
            }
        }
        clasesEnBloque.add(clase);
        guardarEnJSON();
    }

    /**
     * Devuelve una lista con todas las clases en un bloque basándose en su hora y día.
     * @param dia: tal dia
     * @param horario: tal hora
     * @return la lista o un NULL si es que esta vacío el bloque.
     */
    public List<Clase> getClasesEnBloque(Dia dia, Horario horario){
        BloqueHorario bloque = new BloqueHorario(dia, horario);
        return calendario.get(bloque);
    }

    /**
     * Retorna una lista de 1 dimension con todas las clases, al iterar sobre el calendario
     * extrayendo todas las listas de clases y extrayendo cada clase para copiar su referencia en la nueva lista.
     * @return tal lista.
     */
    public List<Clase> getTodasLasClases(){
        List<Clase> clases = new ArrayList<>();
        for (List<Clase> listaClases : calendario.values()){
            clases.addAll(listaClases);
        }
        return clases;
    }

    /**
     * Retorna una lista con todas las clases de un Día en específico, al iterar sobre el calendario
     * verificando que el Día del BloqueHorario coincida con el Día del parámetro para acumular todas las clases de ese día
     * y devolverlas en la salida, si no encuentra clases devuelve una lista vacía.
     * @param dia: tal dia.
     * @return la lista con las clases del Día.
     */
    public List<Clase> getClasesEnDia(Dia dia){
        List<Clase> clasesEnDia = new ArrayList<>();
        for(Map.Entry<BloqueHorario, List<Clase>> entry : calendario.entrySet()){
            if(entry.getKey().dia() == dia){
                clasesEnDia.addAll(entry.getValue());
            }
        }
        return clasesEnDia;
    }

    /**
     * Elimina una clase del calendario al buscar entre todas las clases la que
     * coincida con el ID de la clase.
     * Guarda el estado actual del Calendario en el JSON.
     * @param id: Id de la clase a quitar.
     */
    public void removeClase(String id){
        for (Map.Entry<BloqueHorario, List<Clase>> entry : calendario.entrySet()) {
            List<Clase> clases = entry.getValue();
            clases.removeIf(clase -> id.equals(clase.getId()));
        }
        guardarEnJSON();
    }

    /**
     * Limpia el calendario.
     */
    public void clear(){
        calendario.clear();
    }

    /**
     * Carga las clases desde el archivo JSON y las agrega al calendario actual.
     */
    public void cargarDesdeJSON() {
        Set<Clase> clasesGuardadas = ManejoClasesJSON.getInstancia().getObjetosNoModificable();
        for (Clase c : clasesGuardadas) {
            try {
                addClaseToBloque(c);
            } catch (ConflictoHorarioException e) {
                System.err.println("Conflicto al restaurar clase: " + c.getId() + " - " + e.getMessage());
            }
        }
    }

    /**
     * Guarda todas las clases actuales del calendario en el archivo JSON.
     */
    public void guardarEnJSON() {
        List<Clase> clasesActuales = getTodasLasClases();
        ManejoClasesJSON.getInstancia().guardarClasesDesdeCalendario(clasesActuales);
    }
}