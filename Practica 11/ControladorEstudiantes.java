import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ControladorEstudiantes {
    private ObservableList<Estudiante> lista = FXCollections.observableArrayList();

    public ObservableList<Estudiante> obtenerEstudiantes() {
        return lista;
    }

    public void agregarEstudiante(Estudiante e) {
        lista.add(e);
    }

    public void eliminarEstudiante(Estudiante e) {
        lista.remove(e);
    }

    public void actualizarEstudiante(int index, Estudiante nuevo) {
        lista.set(index, nuevo);
    }
}
