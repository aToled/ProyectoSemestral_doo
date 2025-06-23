public class ProfesorFactory extends PersonaFactory{

    @Override
    public Persona crearPersona() {
        return new Profesor(); //agregar los atributos por el Json
    }
}
