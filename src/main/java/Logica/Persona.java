package Logica;

/**
 * Clase genérica de Persona la cual contiene varios atributos básicos como nombre, apellido, correo.
 * Las personas son Identificables.
 * @see IdentificableAbstracta
 */
public abstract class Persona extends IdentificableAbstracta {
    private final String nombre, apellido, correo;

    public Persona(String nombre, String apellido, String correo, String id) {
        super(id);
        this.nombre = nombre;
        this.apellido = apellido;
        this.correo = correo;
    }

    // getters:
    public String getNombre() {
        return nombre;
    }
    public String getApellido(){
        return apellido;
    }
    public String getCorreo() {
        return correo;
    }

    @Override
    public String toString(){
        return "Nombre: "+nombre+", Apellido: "+apellido+", Correo: "+correo+", ID: "+this.getId();
    }
}