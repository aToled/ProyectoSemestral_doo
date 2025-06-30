package Logica;

import Logica.Enums.*;

/**
 * Record que representa un bloque de horario unico en el calendario, determinado por
 * dia de la semana y horario especificos, se usa como llave en el mapa del calendario, en el profesor para determinar sus
 * bloques de disponibilidad y en Clase para verificar que el profesor esta disponible.
 * @param dia:
 * @param horario:
 */
public record BloqueHorario(Dia dia, Horario horario) {
    @Override
    public String toString(){
        return dia.toString() + " - " + horario.toString();
    }
}