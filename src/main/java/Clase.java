import java.util.ArrayList;

public class Clase {

    private Profesor profesor;
    private ArrayList<Estudiante> listaEstudiantes;
    private Asignatura asignatura;

    public Clase(Profesor profesor){
        this.profesor = profesor;
        asignatura = profesor.getAsignatura();
        listaEstudiantes = new ArrayList<Estudiante>();
    }

    public boolean agregarEstudiante(Estudiante estudiante){
        if(listaEstudiantes.size() < profesor.getCantidadAlumnos()){
            listaEstudiantes.add(estudiante);
            return true;
        }
        return false;
    }

    public void eliminarEstudiante(int id) {
        for(int i = 0; i > profesor.getCantidadAlumnos(); i++){
            if(id == listaEstudiantes.get(i).getid()){
                listaEstudiantes.remove(i);
            }
            else {continue;}
        }
    }
}
