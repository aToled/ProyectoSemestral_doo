package interfaz;

import Logica.*;
import Logica.Enums.Asignatura;
import Logica.Enums.Dia;
import Logica.Enums.Horario;
import Logica.Estrategias.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/**
 * Panel de creación de solicitudes de estudiantes
 */
public class PanelEnviarSolicitudEstudiante extends JPanel {
    private JComboBox listaAsignaturas;
    private JComboBox listaClases;
    private JList listaDias;
    private JList listaHorarios;
    private final Estudiante estudiante;
    private Solicitud solicitud = null;
    private final GestorSolicitudes gestor = GestorSolicitudes.getInstancia();
    private JButton registrar;

    public PanelEnviarSolicitudEstudiante(Estudiante estudiante){
        this.estudiante = estudiante;
        setBackground(new Color(30, 30, 30));
        setLayout(new GridLayout(3,2,5,5));

        InterfazUtils.agregarTitulo("Ingresar Solicitud", this);
        InterfazUtils.agregarTitulo("Preferencia de Clase", this);

        texto();
        seleccionarClase();
        botones();
        volver();
    }

    /**
     * Crea los componentes para generar la creacion de una solicitud
     */
    private void texto(){
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(5, 2, 5, 5));
        panel.setOpaque(false);
        Font fuente = new Font("Arial", Font.BOLD, 20);

        listaAsignaturas = new JComboBox<>();
        for(Asignatura asignatura: Asignatura.values()){
            listaAsignaturas.addItem(asignatura);
        }

        listaDias = new JList<>(Dia.values());
        listaDias.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);

        listaHorarios = new JList<>(Horario.values());
        listaHorarios.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);

        panel.add(InterfazUtils.label("Asignaturas:", fuente)); panel.add(new JScrollPane(listaAsignaturas));
        panel.add(InterfazUtils.label("Días disponibles (Seleccionar Multiples con CTRL):", fuente)); panel.add(new JScrollPane(listaDias));
        panel.add(InterfazUtils.label("Horarios disponibles (Seleccionar Multiples con CTRL):", fuente)); panel.add(new JScrollPane(listaHorarios));

        panel.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.add(panel);
    }

    /**
     * Se crean los botonos para enviar solicitud y se le asignan las funcionalidades
     */
    private void botones() {
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());
        panel.setOpaque(false);

        JButton botonRegistrar = new JButton("Solicitar");
        botonRegistrar.setPreferredSize(new Dimension(250, 40));


        panel.add(botonRegistrar);
        add(panel);

        botonRegistrar.addActionListener((ActionEvent _) -> {

            if (listaDias.getSelectedValuesList().isEmpty()){JOptionPane.showMessageDialog(null, "seleccione al menos un día.", "Error!", JOptionPane.ERROR_MESSAGE);return;}
            if (listaHorarios.getSelectedValuesList().isEmpty()){JOptionPane.showMessageDialog(null, "seleccione al menos un horario.", "Error!", JOptionPane.ERROR_MESSAGE); return;}

            Set<Dia> dias = new HashSet<>(listaDias.getSelectedValuesList());
            Set<Horario> horarios = new HashSet<>(listaHorarios.getSelectedValuesList());


            if(horarios.size()==1 && dias.size() ==1) {
                new BloqueHorario(dias.iterator().next(), horarios.iterator().next());
                PanelPreferencias.diaHoraPreferido = true;
                PanelPreferencias.diaPreferido = false;
                PanelPreferencias.horaPreferido = false;
            }else{
                for (Dia dia : dias) {
                    estudiante.addDiasInteres(dia);
                }
                for (Horario horario : horarios) {
                    estudiante.addHorariosInteres(horario);
                }

                PanelPreferencias.diaHoraPreferido = false;
                PanelPreferencias.diaPreferido = (!dias.isEmpty());
                PanelPreferencias.horaPreferido = (!horarios.isEmpty());
            }

            solicitud = estudiante.crearSolicitud((Asignatura) listaAsignaturas.getSelectedItem());

            ArrayList<EstrategiaSolicitud> estrategias = new ArrayList<>();

            if(PanelPreferencias.menorTarifa){
                estrategias.add(new EstrategiaMenorTarifa());
            }
            if(PanelPreferencias.menorCantidadEstudiantes){
                estrategias.add(new EstrategiaConMenosEstudiantes());
            }
            if(PanelPreferencias.diaHoraPreferido){
                estrategias.add(new EstrategiaBloqueHorarioPreferido());
            } else if(PanelPreferencias.diaPreferido){
                estrategias.add(new EstrategiaDiaPreferido());
            } else if(PanelPreferencias.horaPreferido){
                estrategias.add(new EstrategiaHorarioPreferido());
            }
            estrategias.add(new EstrategiaDefault());

            gestor.resolver(solicitud.getId(), estrategias.toArray(new EstrategiaSolicitud[0]));

            combo();

            JOptionPane.showMessageDialog(null, "Su solicituda ha sido ingresada con exito.", "Solicitud Agregada", JOptionPane.INFORMATION_MESSAGE);


        });
    }

    /**
     * Creacion de los componentes para seleccionar clases dependiendo de las sugerencias
     */
    public void seleccionarClase(){

        Font fuente = new Font("Arial", Font.BOLD, 20);

        JPanel panelBoton = new JPanel();
        panelBoton.setLayout(new FlowLayout());
        panelBoton.setOpaque(false);

        listaClases = new JComboBox<>();

        panelBoton.add(InterfazUtils.label("Asignaturas:", fuente));
        panelBoton.add(listaClases);

        registrar = new JButton("Ingresar Clase Elegida");
        panelBoton.add(registrar);
        registrar.setEnabled(false);
        panelBoton.setPreferredSize(new Dimension(100,30));

        add(panelBoton);

        registrar.addActionListener((ActionEvent _) -> {
            if (listaClases.getSelectedItem() != null) {
                Clase claseSeleccionada = (Clase) listaClases.getSelectedItem();
                solicitud.setClaseElegida(claseSeleccionada);
                gestor.agregar(solicitud);
                JOptionPane.showMessageDialog(null, "Clase asignada a la solicitud.", "Clase Asignada", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "Por favor, seleccione una clase de la lista.", "Error de Selección", JOptionPane.WARNING_MESSAGE);
            }
        });
    }

    /**
     * agrega las clases al JComboBox, sirve para mantener acstualizado
     */
    private void combo(){
        listaClases.removeAllItems();

        Set<Clase> clasesSugeridas = solicitud.getClasesSugeridas();
        if (clasesSugeridas != null && !clasesSugeridas.isEmpty()){
            registrar.setEnabled(true);
            for(Clase clase: clasesSugeridas){
                listaClases.addItem(clase);
            }
            listaClases.setSelectedIndex(0);
        } else {
            registrar.setEnabled(false);
            JOptionPane.showMessageDialog(null, "No se encontraron clases sugeridas para su solicitud. Intente con otras preferencias.", "Sin Sugerencias", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    /**
     * Agrega un boton para volver atras
     */
    public void volver(){
        JPanel panel = new JPanel(new FlowLayout());
        panel.setOpaque(false);

        JButton botonSalir = new JButton("Volver");
        botonSalir.setPreferredSize(new Dimension(250,40));
        panel.add(botonSalir);

        botonSalir.addActionListener(_ -> Ventana.solicitudEstudiante(estudiante));
        add(panel);
    }
}


