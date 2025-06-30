package Logica.Enums;

/**
 * Contiene los Bloques en los que se desarrollaran las clases
 * Va desde las 8:15 hasta las 20:00 horas
 */
public enum Horario {
    BLOQUE1, BLOQUE2, BLOQUE3, BLOQUE4, BLOQUE5, BLOQUE6, BLOQUE7, BLOQUE8, BLOQUE9, BLOQUE10, BLOQUE11, BLOQUE12;

    @Override
    public String toString() {
        switch (this) {
            case BLOQUE1 -> {
                return "8:15 - 9:00";
            }
            case BLOQUE2 -> {
                return "9:15 - 10:00";
            }
            case BLOQUE3 -> {
                return "10:15 - 11:00";
            }
            case BLOQUE4 -> {
                return "11:15 - 12:00";
            }
            case BLOQUE5 -> {
                return "12:15 - 13:00";
            }
            case BLOQUE6 -> {
                return "13:15 - 14:00";
            }
            case BLOQUE7 -> {
                return "14:15 - 15:00";
            }
            case BLOQUE8 -> {
                return "15:15 - 16:00";
            }
            case BLOQUE9 -> {
                return "16:15 - 17:00";
            }
            case BLOQUE10 -> {
                return "17:15 - 18:00";
            }
            case BLOQUE11 -> {
                return "18:15 - 19:00";
            }
            case BLOQUE12 -> {
                return "19:15 - 20:00";
            }
            default -> {
                return null;
            }
        }
    }
}
