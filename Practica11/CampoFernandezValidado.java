package JavaPracticas.Practica11;

import javafx.scene.control.TextField;

public class CampoFernandezValidado extends TextField {
    public CampoFernandezValidado() {
        this.setOnKeyTyped(e -> {
            if (getText().length() > 20) {
                setStyle("-fx-border-color: red;");
            } else {
                setStyle("");
            }
        });
    }
}
