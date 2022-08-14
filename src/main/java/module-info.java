module edu.lawrence.puzzle {
    requires javafx.controls;
    requires javafx.fxml;

    opens edu.lawrence.puzzle to javafx.fxml;
    exports edu.lawrence.puzzle;
}
