package Logica;

import Logica.Enums.*;

/**
 * Record que representa un bloque de horario único en el calendario, determinado por
 * día de la semana y horario específico, se usa como llave en el mapa del calendario, en el profesor para determinar sus
 * bloques de disponibilidad y en Clase para verificar que el profesor está disponible.
 * @param dia:
 * @param horario:
 */
public record BloqueHorario(Dia dia, Horario horario) {

    public Dia getDia() {
        return dia;
    }

    public Horario getHorario() {
        return horario;
    }
    @Override
    public String toString() {
        String horarioStr;
        if (this.horario == null) {
            horarioStr = "Horario no especificado";
        } else {
            horarioStr = this.horario.toString();
        }
        String diaStr;
        if (this.dia == null) {
            diaStr = "Día no especificado";
        } else {
            diaStr = this.dia.toString();
        }

        return "Día " + diaStr + ", Horario " + horarioStr;
    }
}