package librarymanagement;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;

public class FXMLDocumentController implements Initializable {
    @FXML
    private Button close;

    @FXML
    private Button login_Btn;

    @FXML
    private Button minimize;

    @FXML
    private PasswordField password;

    @FXML
    private TextField userNumber;

    // DATABASE TOOLS
    private Connection connect;
    private PreparedStatement prepare;
    private ResultSet result;

    private double x = 0;
    private double y = 0;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        connect = Database.connectDB();
    }

    public void login() {
        String sql = "SELECT * FROM users WHERE userNumber = ? AND password = ?";

        try {
            prepare = connect.prepareStatement(sql);
            prepare.setString(1, userNumber.getText());
            prepare.setString(2, password.getText());
            result = prepare.executeQuery();

            Alert alert;

            if (userNumber.getText().isEmpty() || password.getText().isEmpty()) {
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Admin Message");
                alert.setHeaderText(null);
                alert.setContentText("Please fill all blank fields.");
                alert.showAndWait();
            } else {
                if (result.next()) {
                    getData.userNumber = userNumber.getText();
                    // getData.path = result.getString("image");
                    int userRoll = result.getInt("userRoll");

                    alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Admin Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Login Success");
                    alert.showAndWait();

                    // Hide LOGIN FORM
                    login_Btn.getScene().getWindow().hide();

                    // Determine which dashboard to load
                    String dashboardFXML;
                    if (userRoll == 1) {
                        dashboardFXML = "UserDashBoardController.fxml";
                    } else if (userRoll == 2) {
                        dashboardFXML = "newStudentDashBoard.fxml";
                    } else {
                        alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Admin Message");
                        alert.setHeaderText(null);
                        alert.setContentText("Invalid user role.");
                        alert.showAndWait();
                        return;
                    }

                    // Load the determined dashboard
                    Parent root = FXMLLoader.load(getClass().getResource(dashboardFXML));
                    Stage stage = new Stage();
                    Scene scene = new Scene(root);

                    root.setOnMousePressed((MouseEvent e) -> {
                        x = e.getSceneX();
                        y = e.getSceneY();
                    });

                    root.setOnMouseDragged((MouseEvent e) -> {
                        stage.setX(e.getScreenX() - x);
                        stage.setY(e.getScreenY() - y);
                    });

                    stage.initStyle(StageStyle.TRANSPARENT);
                    stage.setScene(scene);
                    stage.show();
                } else {
                    alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Admin Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Your username or password is wrong.");
                    alert.showAndWait();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void numbersOnly(KeyEvent event) {
        if (event.getCharacter().matches("[^\\e\\t\\r\\d+$]")) {
            event.consume();
            userNumber.setStyle("-fx-border-color:#e04040");
        } else {
            userNumber.setStyle("-fx-border-color:#fff");
        }
    }

    @FXML
    public void minimize() {
        Stage stage = (Stage) minimize.getScene().getWindow();
        stage.setIconified(true);
    }

    @FXML
    public void exit() {
        System.exit(0);
    }
}
