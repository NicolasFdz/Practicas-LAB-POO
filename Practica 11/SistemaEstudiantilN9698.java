import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class SistemaEstudiantilN9698 extends Application {
    @Override
    public void start(Stage primaryStage) {
        MenuBar menuBar = new MenuBar();
        Menu menuArchivo = new Menu("Archivo");
        Menu menuEstudiantes = new Menu("Estudiantes");
        Menu menuAyuda = new Menu("Ayuda");

        menuArchivo.getItems().add(new MenuItem("Salir"));
        menuEstudiantes.getItems().add(new MenuItem("Gestionar"));
        menuAyuda.getItems().add(new MenuItem("Acerca de"));

        menuBar.getMenus().addAll(menuArchivo, menuEstudiantes, menuAyuda);

        BorderPane root = new BorderPane();
        root.setTop(menuBar);

        Scene scene = new Scene(root, 800, 600);
        scene.getStylesheets().add("estilos__9698.css");

        primaryStage.setTitle("Sistema Estudiantil N9698");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
