package Logica;

import Logica.Enums.Asignatura;
import Logica.Enums.Dia;
import Logica.Enums.Horario;
import java.util.HashSet;
import java.util.Set;

/**
 * Representa un Estudiante que puede asignar sus preferencias para enviar una solicitud sobre
 * alguna Asignatura especifica que desea cursar, se extiende de Persona.
 * @see Persona
 */
public class Estudiante extends Persona {
    private final Set<Asignatura> materiasInteres;
    private final Set<BloqueHorario> bloquesHorariosInteres;
    private final Set<Dia> diasInteres;
    private final Set<Horario> horariosInteres;
    private boolean preferirMenorTarifa;
    private boolean preferirClaseConMenosEstudiantes;
    private String password = "";

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
     * (en realidad agrega una solicitud con un ID única a la lista interna de solicitudes, para que esta después
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
    // setters/adders:
    public void addMateriasInteres(Asignatura a) {materiasInteres.add(a);}
    public void addBloquesHorariosInteres(BloqueHorario b) {bloquesHorariosInteres.add(b);}
    public void addDiasInteres(Dia d) {diasInteres.add(d);}
    public void addHorariosInteres(Horario h) {horariosInteres.add(h);}
    public void setPreferirMenorTarifa(boolean b) {this.preferirMenorTarifa = b;}
    public void setPreferirClaseConMenosEstudiantes(boolean b) {this.preferirClaseConMenosEstudiantes = b;}
    public boolean setPassword(String password){
        if(this.password.isEmpty()){
            this.password = password;
            return true;
        }
        return false;
    }

    // getters:
    public Set<Asignatura> getMateriasInteres() {return materiasInteres;}
    public Set<BloqueHorario> getBloquesHorariosInteres() {return bloquesHorariosInteres;}
    public Set<Dia> getDiasInteres() {return diasInteres;}
    public Set<Horario> getHorariosInteres() {return horariosInteres;}
    public boolean isPreferirMenorTarifa() {return preferirMenorTarifa;}
    public boolean isPreferirClaseConMenosEstudiantes() {return preferirClaseConMenosEstudiantes;}
    public String getPassword(){return password;}
}