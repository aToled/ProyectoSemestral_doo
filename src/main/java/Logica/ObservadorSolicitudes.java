package Logica;
import java.util.Set;

public interface ObservadorSolicitudes {
    void actualizar(Set<Solicitud> nuevasSolicitudes);
}