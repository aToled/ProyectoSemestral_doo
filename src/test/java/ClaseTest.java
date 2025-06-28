import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ClaseTest {
    @Test
    public void testLimiteDeEstudiantes(){
        Estudiante estudiante = new Estudiante("bla","bla","hola@gmail.com","14",Asignatura.MATEMATICA, Horario.BLOQUE10, Dia.JUEVES);
        Clase clase = new Clase(new Profesor("bla","bla","hola@gmail.com","14",Asignatura.LENGUAJE, 3, Horario.BLOQUE10, Dia.JUEVES), 1465);
        Assertions.assertFalse(clase.agregarEstudiante(estudiante));
        estudiante = new Estudiante("bla","bla","hola@gmail.com","14",Asignatura.LENGUAJE, Horario.BLOQUE10, Dia.JUEVES);
        Assertions.assertTrue(clase.agregarEstudiante(estudiante));
        Assertions.assertTrue(clase.agregarEstudiante(estudiante));
        Assertions.assertTrue(clase.agregarEstudiante(estudiante));
        Assertions.assertFalse(clase.agregarEstudiante(estudiante));
    }
    @Test
    public void testCantidadDeEstudiantes(){
        Estudiante estudiante = new Estudiante("bla","bla","hola@gmail.com","14",Asignatura.LENGUAJE, Horario.BLOQUE10, Dia.JUEVES);
        Clase clase = new Clase(new Profesor("bla","bla","hola@gmail.com","14",Asignatura.LENGUAJE, 3, Horario.BLOQUE10, Dia.JUEVES), 1465);
        Assertions.assertEquals(0, clase.cantidadEstudiantes());
        Assertions.assertTrue(clase.agregarEstudiante(estudiante));
        Assertions.assertEquals(1, clase.cantidadEstudiantes());
        clase.eliminarEstudiante(14);
        Assertions.assertEquals(0, clase.cantidadEstudiantes());
    }
}
