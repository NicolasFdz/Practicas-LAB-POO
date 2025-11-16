package JavaPracticas.Practica11;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import java.io.File;

public class SistemaEstudiantilN9698 extends Application {

    private TableView<Estudiante> tabla;

    @Override
    public void start(Stage stage) {
        BorderPane root = new BorderPane();

        // Menú superior
        MenuBar menuBar = new MenuBar();
        Menu menuArchivo = new Menu("Archivo");
        MenuItem salir = new MenuItem("Salir");
        salir.setOnAction(e -> stage.close());
        menuArchivo.getItems().add(salir);
        menuBar.getMenus().add(menuArchivo);
        root.setTop(menuBar);

        // Tabla de estudiantes
        tabla = new TableView<>();
        TableColumn<Estudiante, String> colNombre = new TableColumn<>("Nombre");
        colNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        TableColumn<Estudiante, String> colMatricula = new TableColumn<>("Matrícula");
        colMatricula.setCellValueFactory(new PropertyValueFactory<>("matricula"));
        tabla.getColumns().addAll(colNombre, colMatricula);
        tabla.setPlaceholder(new Label("No hay estudiantes registrados."));
        root.setCenter(tabla);

        // Ícono para botón Agregar
        try {
            File archivoImagen = new File("recursos_Fernandez/AgregarEstudiante.png");
            System.out.println("Buscando imagen en: " + archivoImagen.getAbsolutePath());
            System.out.println("¿Existe? " + archivoImagen.exists());
            
            if (archivoImagen.exists()) {
                Image imagen = new Image(archivoImagen.toURI().toString());
                ImageView iconoAgregar = new ImageView(imagen);
                iconoAgregar.setFitHeight(24);
                iconoAgregar.setFitWidth(24);

                Boton10Estilizado botonAgregar = new Boton10Estilizado("Agregar");
                botonAgregar.setGraphic(iconoAgregar);
                botonAgregar.setOnAction(e -> mostrarFormulario());

                HBox acciones = new HBox(botonAgregar);
                acciones.setSpacing(10);
                acciones.setStyle("-fx-padding: 10; -fx-alignment: center;");
                root.setBottom(acciones);
            } else {
                System.out.println("❌ Imagen NO encontrada");
                Boton10Estilizado botonAgregar = new Boton10Estilizado("Agregar");
                botonAgregar.setOnAction(e -> mostrarFormulario());
                HBox acciones = new HBox(botonAgregar);
                acciones.setStyle("-fx-padding: 10; -fx-alignment: center;");
                root.setBottom(acciones);
            }
        } catch (Exception e) {
            System.out.println("Error al cargar imagen:");
            e.printStackTrace();
        }

        // Escena y estilos
        Scene scene = new Scene(root, 640, 420);
        scene.getStylesheets().add("file:recursos_Fernandez/estilos_9698.css");
        stage.setTitle("Sistema Estudiantil N9698");
        stage.setScene(scene);
        stage.show();
    }

    private void mostrarFormulario() {
        Dialog<Estudiante> dialogo = new Dialog<>();
        dialogo.setTitle("Nuevo Estudiante");

        CampoFernandezValidado campoNombre = new CampoFernandezValidado();
        campoNombre.setPromptText("Nombre");

        CampoFernandezValidado campoMatricula = new CampoFernandezValidado();
        campoMatricula.setPromptText("Matrícula");

        VBox contenido = new VBox(campoNombre, campoMatricula);
        contenido.setSpacing(10);
        dialogo.getDialogPane().setContent(contenido);

        ButtonType guardar = new ButtonType("Guardar", ButtonBar.ButtonData.OK_DONE);
        dialogo.getDialogPane().getButtonTypes().addAll(guardar, ButtonType.CANCEL);

        dialogo.setResultConverter(b -> {
            if (b == guardar) {
                return new Estudiante(campoNombre.getText(), campoMatricula.getText());
            }
            return null;
        });

        dialogo.showAndWait().ifPresent(est -> tabla.getItems().add(est));
    }

    public static void main(String[] args) {
        launch(args);
    }
}