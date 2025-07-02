package Logica;

import Logica.Enums.Asignatura;
import Logica.Enums.Dia;
import Logica.Enums.Horario;
import java.util.HashSet;
import java.util.Set;

public class Estudiante extends Persona {
    private Set<Asignatura> materiasInteres;
    private Set<BloqueHorario> bloquesHorariosInteres;
    private Set<Dia> diasInteres;
    private Set<Horario> horariosInteres;
    private boolean preferirMenorTarifa;
    private boolean preferirClaseConMenosEstudiantes;

    public Estudiante(String nombre, String apellido, String correo, String id) {
        super(nombre, apellido, correo, id);

        this.materiasInteres = new HashSet<>();
        this.bloquesHorariosInteres = new HashSet<>();
        this.diasInteres = new HashSet<>();
        this.horariosInteres = new HashSet<>();
        this.preferirMenorTarifa = false;
        this.preferirClaseConMenosEstudiantes = false;
    }

    /**
     * Crea una solicitud en el sistema sobre alguna asignatura en particular, y retorna la solicitud creada. (para mostrarla en la GUI)
     * (en realidad agrega una solicitud con un ID única a la lista interna de solicitudes, para que esta despues
     * pueda ser evaluada con el método resolver())
     * @param a: tal asignatura.
     * @return la solicitud.
     */
    public Solicitud crearSolicitud(Asignatura a){
        String idSolicitud = getId() + "_" + (int)(Math.random() * 100000);
        Solicitud s = new Solicitud(idSolicitud, this, a);
        GestorSolicitudes.getInstancia().agregar(s);
        return s;
    }

    public void addMateriasInteres(Asignatura a) {materiasInteres.add(a);}
    public void addBloquesHorariosInteres(BloqueHorario b) {bloquesHorariosInteres.add(b);}
    public void addDiasInteres(Dia d) {diasInteres.add(d);}
    public void addHorariosInteres(Horario h) {horariosInteres.add(h);}
    public void setPreferirMenorTarifa(boolean b) {this.preferirMenorTarifa = b;}
    public void setPreferirClaseConMenosEstudiantes(boolean b) {this.preferirClaseConMenosEstudiantes = b;}

    public Set<Asignatura> getMateriasInteres() {return materiasInteres;}
    public Set<BloqueHorario> getBloquesHorariosInteres() {return bloquesHorariosInteres;}
    public Set<Dia> getDiasInteres() {return diasInteres;}
    public Set<Horario> getHorariosInteres() {return horariosInteres;}
    public boolean isPreferirMenorTarifa() {return preferirMenorTarifa;}
    public boolean isPreferirClaseConMenosEstudiantes() {return preferirClaseConMenosEstudiantes;}
}