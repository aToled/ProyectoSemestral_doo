import java.util.ArrayList;
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

            if (listaEstudiantes.size() < profesor.getCantidadEstudiantes()) {
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

    public void eliminarEstudiante(int id) {
        listaEstudiantes.remove(id);
    }


    public int getId(){
        return id;
    }

    public int cantidadEstudiantes(){
        return listaEstudiantes.size();
    }
}
