package JavaPracticas.Practica11;

import javafx.scene.control.Button;

public class Boton10Estilizado extends Button {
    public Boton10Estilizado(String texto) {
        super(texto);
        this.getStyleClass().add("boton-estilizado");
    }
}
