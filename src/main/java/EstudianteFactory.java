public class EstudianteFactory extends PersonaFactory {
    @Override
    public Persona crearPersona() {
        return new Estudiante();//agregar atributos
    }
}
