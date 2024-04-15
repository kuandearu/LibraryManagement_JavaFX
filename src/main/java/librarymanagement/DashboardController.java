package librarymanagement;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.net.URL;
import java.util.ResourceBundle;

public class DashboardController implements Initializable {

    @FXML
    private Button availableBooks_btn;

    @FXML
    private AnchorPane availableBooks_form;

    @FXML
    private ImageView availableBooks_image_btn;

    @FXML
    private TableView<?> availableBooks_table_btn;

    @FXML
    private Label availableBooks_title_btn;

    @FXML
    private Circle circle_image;

    @FXML
    private TableColumn<?, ?> col_ab_author;

    @FXML
    private TableColumn<?, ?> col_ab_bookType;

    @FXML
    private TableColumn<?, ?> col_ab_booksTitle;

    @FXML
    private TableColumn<?, ?> col_ab_publishedDate;

    @FXML
    private Button edit_btn;

    @FXML
    private Button issueBooks_btn;

    @FXML
    private Button returnBooks_btn;

    @FXML
    private Button save_btn;

    @FXML
    private Button savedBooks_btn;

    @FXML
    private Button logout_btn;

    @FXML
    private Label studentNumber_btn;

    @FXML
    private Button take_btn;

    private double x = 0;
    private double y = 0;

    @FXML
    public void logout(ActionEvent event){
        try{
            if(event.getSource() == logout_btn){
                //To SWAP FROM DASHBOARD TO LOGIN FORM
                Parent root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));

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

                logout_btn.getScene().getWindow().hide();

            }
        }catch (Exception e){
            e.printStackTrace();
        }

    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
