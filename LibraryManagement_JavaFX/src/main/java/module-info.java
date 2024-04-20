module librarymanagement {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    opens librarymanagement to javafx.fxml;
    exports librarymanagement;
}