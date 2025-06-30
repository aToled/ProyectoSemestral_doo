package Logica.Enums;

public enum Dia {
    LUNES, MARTES, MIERCOLES, JUEVES, VIERNES;

    /**
     * Simplifica la el string del dia
     * ejemplos: LUNES = LU, MARTES = MA
     * @return String del dia usado
     */
    @Override
    public String toString(){
        switch (this){
            case LUNES -> {
                return "LU";
            }
            case MARTES -> {
                return "MA";
            }
            case MIERCOLES -> {
                return "MI";
            }
            case JUEVES -> {
                return "JU";
            }
            case VIERNES -> {
                return "VI";
            }
            default -> {
                return null;
            }
        }
    }
}
