/**
 * Contiene los Bloques en los que se desarrollaran las clases
 * Va desde las 8 hasta las 16 hrs
 */
public enum Horario {
    BLOQUE8,
    BLOQUE9,
    BLOQUE10,
    BLOQUE11,
    BLOQUE12,
    BLOQUE13,
    BLOQUE14,
    BLOQUE15,
    BLOQUE16;

    @Override
    public String toString() {
        switch (this) {
            case BLOQUE8 -> {
                return "8";
            }
            case BLOQUE9 -> {
                return "9";
            }
            case BLOQUE10 -> {
                return "10";
            }
            case BLOQUE11 -> {
                return "11";
            }
            case BLOQUE12 -> {
                return "12";
            }
            case BLOQUE13 -> {
                return "13";
            }
            case BLOQUE14 -> {
                return "14";
            }
            case BLOQUE15 -> {
                return "15";
            }
            case BLOQUE16 -> {
                return "16";
            }
            default -> {
                return null;
            }
        }
    }
}
