package edu.lawrence.puzzle;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.VBox;

public class PrimaryController implements Initializable {

    @FXML VBox vbox;
    PuzzlePane pane;
    
    @FXML
    private void exit() {
        Platform.exit();
    }

    @FXML
    private void scramble() {
        pane.scramble();
    }
    
    @FXML
    private void save() {
        pane.save();
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        pane = new PuzzlePane();
        vbox.getChildren().add(pane);
    }
    
    
}
