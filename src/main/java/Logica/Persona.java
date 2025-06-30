package Logica;

public abstract class Persona {
    private final String nombre, apellido, correo, id;

    public Persona(String nombre, String apellido, String correo, String id) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.correo = correo;
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }
    public String getApellido(){
        return apellido;
    }
    public String getCorreo() {
        return correo;
    }
    public String getId() {
        return id;
    }

    @Override
    public String toString(){
        return "Nombre: "+nombre+", Apellido: "+apellido+", Correo: "+correo+", ID: "+id;
    }

    @Override
    public boolean equals(Object o){
        if(o == this) return true;
        if(!(o instanceof Persona otro)) return false;
        return getId().equals(otro.getId());
    }

    @Override
    public final int hashCode() {
        return Integer.parseInt(id);
    }
}
