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
import java.sql.Statement;
import java.util.ResourceBundle;

public class FXMLDocumentController implements Initializable {
    @FXML
    private Button close;

    @FXML
    private Button login_Btn;

    @FXML
    private Button loginStudent_Btn;

    @FXML
    private Button minimize;

    @FXML
    private PasswordField password;

    @FXML
    private TextField studentNumber;


    //DATABASE TOOLS
    private Connection connect;
    private PreparedStatement prepare;
    private ResultSet result;

    private double x = 0;
    private double y = 0;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        connect = Database.connectDB();
    }
    public void login(){
        String sql = "SELECT * FROM student WHERE studentNumber = ? AND password = ? AND studentRole = 'admin'";

        try{

            prepare = connect.prepareStatement(sql);
            prepare.setString(1, studentNumber.getText());
            prepare.setString(2, password.getText());
            result = prepare.executeQuery();

            Alert alert;

            if(studentNumber.getText().isEmpty() || password.getText().isEmpty()){

                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Admin Message");
                alert.setHeaderText(null);
                alert.setContentText("Please fill all blank fields.");
                alert.showAndWait();

            }else{

                if(result.next()){

                    getData.studentNumber = studentNumber.getText();
                    //getData.path = result.getString("image");
                    alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Admin Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Login Success");
                    alert.showAndWait();

                    // to Hide LOGIN FORM
                    login_Btn.getScene().getWindow().hide();

                    //For DashBoard
                    Parent root = FXMLLoader.load(getClass().getResource("dashboard.fxml"));

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
                }else{
                    alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Admin Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Your username or password is wrong.");
                    alert.showAndWait();

                }
            }

        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public void loginStudent(){
        String sql = "SELECT * FROM student WHERE studentNumber = ? AND password = ? AND studentRole = 'user'";
        try{

            prepare = connect.prepareStatement(sql);
            prepare.setString(1, studentNumber.getText());
            prepare.setString(2, password.getText());
            result = prepare.executeQuery();

            Alert alert;

            if(studentNumber.getText().isEmpty() || password.getText().isEmpty()){

                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Admin Message");
                alert.setHeaderText(null);
                alert.setContentText("Please fill all blank fields.");
                alert.showAndWait();

            }else{

                if(result.next()){

                    getData.studentNumber = studentNumber.getText();
                    //getData.path = result.getString("image");
                    alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Admin Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Login Success");
                    alert.showAndWait();

                    // to Hide LOGIN FORM
                    loginStudent_Btn.getScene().getWindow().hide();

                    //For DashBoard
                    Parent root = FXMLLoader.load(getClass().getResource("newStudentDashBoard.fxml"));

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
                }else{
                    alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Admin Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Your username or password is wrong.");
                    alert.showAndWait();

                }
            }

        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public void numbersOnly(KeyEvent event){
        if(event.getCharacter().matches("[^\\e\\t\\r\\d+$]")){
            event.consume();

            studentNumber.setStyle("-fx-border-color:#e04040");
        }else{
            studentNumber.setStyle("-fx-border-color:#fff");
        }
    }
    @FXML
    public void minimize(){
        Stage stage = (Stage) minimize.getScene().getWindow();
        stage.setIconified(true);
    }
    @FXML
    public void exit(){
        System.exit(0);
    }


}