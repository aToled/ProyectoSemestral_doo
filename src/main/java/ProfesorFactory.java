public class ProfesorFactory extends PersonaFactory{

    @Override
    public Persona crearPersona() {
        return new Profesor("bla","bla","hola@gmail.com","14",Asignatura.LENGUAJE, 3); //agregar los atributos por el Json
    }

}
