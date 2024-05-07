module librarymanagement {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires fontawesomefx;

    opens librarymanagement to javafx.fxml;
    exports librarymanagement;
}