package Except;

import javafx.scene.control.Alert;

public class FXException {
    Alert a;

    public FXException(Alert.AlertType type, String head, String text ) {
        this.a = new Alert(type);
        a.setHeaderText(head);
        a.setContentText(text);

        a.showAndWait();
    }
}
