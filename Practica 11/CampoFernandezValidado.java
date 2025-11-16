import javafx.scene.control.TextField;

public class CampoFernandezValidado extends TextField {
    public CampoFernandezValidado() {
        this.setPromptText("Nombre del estudiante");
        this.textProperty().addListener((obs, oldText, newText) -> {
            if (!newText.matches("[a-zA-ZáéíóúÁÉÍÓÚñÑ ]*")) {
                this.setText(oldText);
            }
        });
    }
}
