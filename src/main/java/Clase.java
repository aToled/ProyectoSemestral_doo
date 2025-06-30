import java.util.HashMap;
import java.util.Map;

public class Clase {

    private Profesor profesor;
    private Map<Integer,Estudiante> listaEstudiantes;
    private Asignatura asignatura;
    private int id;
    private Horario horario;
    private Dia dia;

    public Clase(Profesor profesor, int id){
        this.profesor = profesor;
        asignatura = profesor.getAsignatura();
        listaEstudiantes = new HashMap<>();
        this.id = id;
        dia = profesor.getDia();
        horario = profesor.getHorario();
    }

    /**
     * @param estudiante
     * @return Booleano si es que se pudo realizar la operacion
     */
    public boolean agregarEstudiante(Estudiante estudiante) {
        //faltan las validaciones si para ver si es que coincide el dia y el horario

        if (estudiante.getAsignatura() == asignatura) {

            if (listaEstudiantes.size() < profesor.getCantidadMaximaAlumnos()) {
                listaEstudiantes.put(estudiante.getIdInt(),estudiante);
                System.out.println(estudiante.getNombre()+" "+ estudiante.getApellido()+" Se agrego con exito");
                return true;
            }

            else{
                System.out.println(estudiante.getNombre()+" "+ estudiante.getApellido()+" No se pudo agregar");
                return false;
            }
        }
        else{
            System.out.println(estudiante.getNombre()+" "+ estudiante.getApellido()+" No se pudo agregar");
            return false;
        }

    }

    /**
     * Verifica si la Clase esta llena o no.
     * @return True si la lista es mayor o igual a la cantidad maxima permitida por el profesor.
     */
    public boolean isLlena(){
        return listaEstudiantes.size() >= profesor.getCantidadMaximaAlumnos();
    }

    /**
     * Metodo utilitario que válida si un estudiante pertenece o no a la clase.
     * @param e: dicho estudiante.
     * @return valor booleano que depende de si los atributos necesarios del estudiante coinciden con la asignatura.
     */
    public boolean estudianteEnClase(Estudiante e){
        return asignatura==e.getAsignatura() && dia==e.getDia() && horario==e.getHorario();

    }

    public void eliminarEstudiante(int id) {
        listaEstudiantes.remove(id);
    }


    public int getId(){
        return id;
    }

    public int cantidadEstudiantes(){
        return listaEstudiantes.size();
    }

    public Dia getDia() {
        return dia;
    }

    public Horario getHorario() {
        return horario;
    }

    public Profesor getProfesor() {
        return profesor;
    }
}
