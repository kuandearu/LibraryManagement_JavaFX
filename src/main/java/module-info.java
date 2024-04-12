module org.example.librarymanagementfx {
    requires javafx.controls;
    requires javafx.fxml;


    opens librarymanagement to javafx.fxml;
    exports librarymanagement;
}