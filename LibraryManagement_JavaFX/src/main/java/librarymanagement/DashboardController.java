package librarymanagement;

import javafx.animation.TranslateTransition;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;

public class DashboardController implements Initializable {

    @FXML
    private Button availableBooks_btn;

    @FXML
    private AnchorPane availableBooks_form;

    @FXML
    private ImageView availableBooks_imageView;

    @FXML
    private TableView<availableBooks> availableBooks_tableView;

    @FXML
    private Label availableBooks_title;

    @FXML
    private Circle circle_image;

    @FXML
    private TableColumn<availableBooks, String> col_ab_author;

    @FXML
    private TableColumn<availableBooks, String> col_ab_bookType;

    @FXML
    private TableColumn<availableBooks, String> col_ab_bookTitle;

    @FXML
    private TableColumn<availableBooks, String> col_ab_publishedDate;

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
    private Label studentNumber_label;

    @FXML
    private Button take_btn;



    @FXML
    private Button close;

    @FXML
    private Button minimize;

    @FXML
    private Button bars_btn;

    @FXML
    private Button arrow_btn;

    @FXML
    private AnchorPane nav_form;

    @FXML
    private AnchorPane mainCenter_form;

    @FXML
    private Button halfNav_availableBtn;

    @FXML
    private AnchorPane halfNav_form;

    @FXML
    private Button halfNav_returnBtn;

    @FXML
    private Button halfNav_saveBtn;

    @FXML
    private Button halfNav_takeBtn;

    @FXML
    private Circle smallCircle_image;

    @FXML
    private AnchorPane issue_form;

    @FXML
    private AnchorPane returnBook_form;

    @FXML
    private AnchorPane savedBook_form;

    @FXML
    private Label currentForm_label;

    Image image;

    private Connection connect;
    private PreparedStatement prepare;
    private Statement statement;
    private ResultSet result;

    public ObservableList<availableBooks> dataList(){

        ObservableList<availableBooks> listBooks = FXCollections.observableArrayList();

        String sql = "SELECT * FROM book";

        connect = Database.connectDB();

        try {
            availableBooks aBooks;

            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            while (result.next()){
                aBooks = new availableBooks(
                        result.getString("bookTitle"),
                        result.getString("author"),
                        result.getString("bookType"),
                        result.getString("image"),
                        result.getDate("date"));

                listBooks.add(aBooks);
            }

        }catch (Exception e){e.printStackTrace();}
        return listBooks;
    }

    //SHOWING BOOKS DATA
    private ObservableList<availableBooks> listBook;
    public void showAvailableBooks(){

        listBook = dataList();

        col_ab_bookTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
        col_ab_author.setCellValueFactory(new PropertyValueFactory<>("author"));
        col_ab_bookType.setCellValueFactory(new PropertyValueFactory<>("genre"));
        col_ab_publishedDate.setCellValueFactory(new PropertyValueFactory<>("date"));

        availableBooks_tableView.setItems(listBook);
    }

    public void selectAvailableBooks(){

        availableBooks bookData = availableBooks_tableView.getSelectionModel().getSelectedItem();

        int num = availableBooks_tableView.getSelectionModel().getFocusedIndex();

        if ((num - 1) < -1) {
            return;
        }

        availableBooks_title.setText(bookData.getTitle());

//        THIS IS REQUIRED TO DISPLAY THE IMAGE
//        NOTE! DON'T FORGET THE "file:"
        String uri = "file:" + bookData.getImage();

        image = new Image(uri, 134, 171, false, true);
        availableBooks_imageView.setImage(image);
    }

    public void abTakeButton(ActionEvent event){

        if (event.getSource()== take_btn){
            issue_form.setVisible(true);
            availableBooks_form.setVisible(false);
            savedBook_form.setVisible(false);
            returnBook_form.setVisible(false);
        }

    }

    public void studentNumber(){
//        WE CAN DISPLAY THE STUDENT NUMBER THAT STUDENT USED
        studentNumber_label.setText(getData.studentNumber);
    }

    public void sideNavButtonDesign(ActionEvent event){
        if (event.getSource() == halfNav_availableBtn){

            issue_form.setVisible(false);
            availableBooks_form.setVisible(true);
            savedBook_form.setVisible(false);
            returnBook_form.setVisible(false);

            availableBooks_btn.setStyle("-fx-background-color: linear-gradient(to bottom right, #46589a, #4278a7);");
            issueBooks_btn.setStyle("-fx-background-color: linear-gradient(to bottom right, #344275, #3a6389);");
            returnBooks_btn.setStyle("-fx-background-color: linear-gradient(to bottom right, #344275, #3a6389);");
            savedBooks_btn.setStyle("-fx-background-color: linear-gradient(to bottom right, #344275, #3a6389);");

            halfNav_availableBtn.setStyle("-fx-background-color: linear-gradient(to bottom right, #46589a, #4278a7);");
            halfNav_takeBtn.setStyle("-fx-background-color: linear-gradient(to bottom right, #344275, #3a6389);");
            halfNav_returnBtn.setStyle("-fx-background-color: linear-gradient(to bottom right, #344275, #3a6389);");
            halfNav_saveBtn.setStyle("-fx-background-color: linear-gradient(to bottom right, #344275, #3a6389);");

            currentForm_label.setText("Available Books");
        }else if (event.getSource() == halfNav_takeBtn){

            issue_form.setVisible(true);
            availableBooks_form.setVisible(false);
            savedBook_form.setVisible(false);
            returnBook_form.setVisible(false);

            issueBooks_btn.setStyle("-fx-background-color: linear-gradient(to bottom right, #46589a, #4278a7);");
            availableBooks_btn.setStyle("-fx-background-color: linear-gradient(to bottom right, #344275, #3a6389);");
            returnBooks_btn.setStyle("-fx-background-color: linear-gradient(to bottom right, #344275, #3a6389);");
            savedBooks_btn.setStyle("-fx-background-color: linear-gradient(to bottom right, #344275, #3a6389);");

            halfNav_takeBtn.setStyle("-fx-background-color: linear-gradient(to bottom right, #46589a, #4278a7);");
            halfNav_availableBtn.setStyle("-fx-background-color: linear-gradient(to bottom right, #344275, #3a6389);");
            halfNav_returnBtn.setStyle("-fx-background-color: linear-gradient(to bottom right, #344275, #3a6389);");
            halfNav_saveBtn.setStyle("-fx-background-color: linear-gradient(to bottom right, #344275, #3a6389);");

            currentForm_label.setText("Issue Books");

        }else if (event.getSource() == halfNav_returnBtn){

            issue_form.setVisible(false);
            availableBooks_form.setVisible(false);
            savedBook_form.setVisible(false);
            returnBook_form.setVisible(true);

            returnBooks_btn.setStyle("-fx-background-color: linear-gradient(to bottom right, #46589a, #4278a7);");
            availableBooks_btn.setStyle("-fx-background-color: linear-gradient(to bottom right, #344275, #3a6389);");
            issueBooks_btn.setStyle("-fx-background-color: linear-gradient(to bottom right, #344275, #3a6389);");
            savedBooks_btn.setStyle("-fx-background-color: linear-gradient(to bottom right, #344275, #3a6389);");

            halfNav_returnBtn.setStyle("-fx-background-color: linear-gradient(to bottom right, #46589a, #4278a7);");
            halfNav_takeBtn.setStyle("-fx-background-color: linear-gradient(to bottom right, #344275, #3a6389);");
            halfNav_availableBtn.setStyle("-fx-background-color: linear-gradient(to bottom right, #344275, #3a6389);");
            halfNav_saveBtn.setStyle("-fx-background-color: linear-gradient(to bottom right, #344275, #3a6389);");

            currentForm_label.setText("Return Books");

        }else if (event.getSource() == halfNav_saveBtn){

            issue_form.setVisible(false);
            availableBooks_form.setVisible(false);
            savedBook_form.setVisible(true);
            returnBook_form.setVisible(false);

            savedBooks_btn.setStyle("-fx-background-color: linear-gradient(to bottom right, #46589a, #4278a7);");
            availableBooks_btn.setStyle("-fx-background-color: linear-gradient(to bottom right, #344275, #3a6389);");
            issueBooks_btn.setStyle("-fx-background-color: linear-gradient(to bottom right, #344275, #3a6389);");
            returnBooks_btn.setStyle("-fx-background-color: linear-gradient(to bottom right, #344275, #3a6389);");

            halfNav_saveBtn.setStyle("-fx-background-color: linear-gradient(to bottom right, #46589a, #4278a7);");
            halfNav_takeBtn.setStyle("-fx-background-color: linear-gradient(to bottom right, #344275, #3a6389);");
            halfNav_returnBtn.setStyle("-fx-background-color: linear-gradient(to bottom right, #344275, #3a6389);");
            halfNav_availableBtn.setStyle("-fx-background-color: linear-gradient(to bottom right, #344275, #3a6389);");

            currentForm_label.setText("Saved Books");

        }
    }

    public void navButtonDesign(ActionEvent event){

        if (event.getSource() == availableBooks_btn){

            issue_form.setVisible(false);
            availableBooks_form.setVisible(true);
            savedBook_form.setVisible(false);
            returnBook_form.setVisible(false);

            availableBooks_btn.setStyle("-fx-background-color: linear-gradient(to bottom right, #46589a, #4278a7);");
            issueBooks_btn.setStyle("-fx-background-color: linear-gradient(to bottom right, #344275, #3a6389);");
            returnBooks_btn.setStyle("-fx-background-color: linear-gradient(to bottom right, #344275, #3a6389);");
            savedBooks_btn.setStyle("-fx-background-color: linear-gradient(to bottom right, #344275, #3a6389);");

            halfNav_availableBtn.setStyle("-fx-background-color: linear-gradient(to bottom right, #46589a, #4278a7);");
            halfNav_takeBtn.setStyle("-fx-background-color: linear-gradient(to bottom right, #344275, #3a6389);");
            halfNav_returnBtn.setStyle("-fx-background-color: linear-gradient(to bottom right, #344275, #3a6389);");
            halfNav_saveBtn.setStyle("-fx-background-color: linear-gradient(to bottom right, #344275, #3a6389);");

            currentForm_label.setText("Available Books");

        }else if (event.getSource() == issueBooks_btn){

            issue_form.setVisible(true);
            availableBooks_form.setVisible(false);
            savedBook_form.setVisible(false);
            returnBook_form.setVisible(false);

            issueBooks_btn.setStyle("-fx-background-color: linear-gradient(to bottom right, #46589a, #4278a7);");
            availableBooks_btn.setStyle("-fx-background-color: linear-gradient(to bottom right, #344275, #3a6389);");
            returnBooks_btn.setStyle("-fx-background-color: linear-gradient(to bottom right, #344275, #3a6389);");
            savedBooks_btn.setStyle("-fx-background-color: linear-gradient(to bottom right, #344275, #3a6389);");

            halfNav_takeBtn.setStyle("-fx-background-color: linear-gradient(to bottom right, #46589a, #4278a7);");
            halfNav_availableBtn.setStyle("-fx-background-color: linear-gradient(to bottom right, #344275, #3a6389);");
            halfNav_returnBtn.setStyle("-fx-background-color: linear-gradient(to bottom right, #344275, #3a6389);");
            halfNav_saveBtn.setStyle("-fx-background-color: linear-gradient(to bottom right, #344275, #3a6389);");

            currentForm_label.setText("Issue Books");

        }else if (event.getSource() == returnBooks_btn){

            issue_form.setVisible(false);
            availableBooks_form.setVisible(false);
            savedBook_form.setVisible(false);
            returnBook_form.setVisible(true);

            returnBooks_btn.setStyle("-fx-background-color: linear-gradient(to bottom right, #46589a, #4278a7);");
            availableBooks_btn.setStyle("-fx-background-color: linear-gradient(to bottom right, #344275, #3a6389);");
            issueBooks_btn.setStyle("-fx-background-color: linear-gradient(to bottom right, #344275, #3a6389);");
            savedBooks_btn.setStyle("-fx-background-color: linear-gradient(to bottom right, #344275, #3a6389);");

            halfNav_returnBtn.setStyle("-fx-background-color: linear-gradient(to bottom right, #46589a, #4278a7);");
            halfNav_takeBtn.setStyle("-fx-background-color: linear-gradient(to bottom right, #344275, #3a6389);");
            halfNav_availableBtn.setStyle("-fx-background-color: linear-gradient(to bottom right, #344275, #3a6389);");
            halfNav_saveBtn.setStyle("-fx-background-color: linear-gradient(to bottom right, #344275, #3a6389);");

            currentForm_label.setText("Return Books");


        }else if (event.getSource() == savedBooks_btn){

            issue_form.setVisible(false);
            availableBooks_form.setVisible(false);
            savedBook_form.setVisible(true);
            returnBook_form.setVisible(false);

            savedBooks_btn.setStyle("-fx-background-color: linear-gradient(to bottom right, #46589a, #4278a7);");
            availableBooks_btn.setStyle("-fx-background-color: linear-gradient(to bottom right, #344275, #3a6389);");
            issueBooks_btn.setStyle("-fx-background-color: linear-gradient(to bottom right, #344275, #3a6389);");
            returnBooks_btn.setStyle("-fx-background-color: linear-gradient(to bottom right, #344275, #3a6389);");

            halfNav_saveBtn.setStyle("-fx-background-color: linear-gradient(to bottom right, #46589a, #4278a7);");
            halfNav_takeBtn.setStyle("-fx-background-color: linear-gradient(to bottom right, #344275, #3a6389);");
            halfNav_returnBtn.setStyle("-fx-background-color: linear-gradient(to bottom right, #344275, #3a6389);");
            halfNav_availableBtn.setStyle("-fx-background-color: linear-gradient(to bottom right, #344275, #3a6389);");

            currentForm_label.setText("Saved Books");

        }
    }

    private double x = 0;
    private double y = 0;

    public void sliderArrow(){

        TranslateTransition slide = new TranslateTransition();

        slide.setDuration(Duration.seconds(.5));
        slide.setNode(nav_form);
        slide.setToX(-263);

        TranslateTransition slide1 = new TranslateTransition();

        slide1.setDuration(Duration.seconds(.5));
        slide1.setNode(mainCenter_form);
        slide1.setToX(-263 + 100);

        TranslateTransition slide2 = new TranslateTransition();

        slide2.setDuration(Duration.seconds(.5));
        slide2.setNode(halfNav_form);
        slide2.setToX(0);



        slide.setOnFinished((ActionEvent event) -> {
            arrow_btn.setVisible(false);
            bars_btn.setVisible(true);
        });

        slide2.play();
        slide1.play();
        slide.play();
    }

    public void sliderBars(){
        TranslateTransition slide = new TranslateTransition();

        slide.setDuration(Duration.seconds(.5));
        slide.setNode(nav_form);
        slide.setToX(0);

        TranslateTransition slide1 = new TranslateTransition();
        slide1.setDuration(Duration.seconds(.5));
        slide1.setNode(mainCenter_form);
        slide1.setToX(0);

        TranslateTransition slide2 = new TranslateTransition();

        slide2.setDuration(Duration.seconds(.5));
        slide2.setNode(halfNav_form);
        slide2.setToX(-86);

        slide.setOnFinished((ActionEvent event) -> {
            arrow_btn.setVisible(true);
            bars_btn.setVisible(false);
        });

        slide2.play();
        slide1.play();
        slide.play();
    }

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

    public void exit(){
        System.exit(0);
    }

    public void minimize(){
        Stage stage = (Stage) minimize.getScene().getWindow();
        stage.setIconified(true);
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        //TO SHOW THE AVAILABLE BOOKS
        showAvailableBooks();
        studentNumber();
    }
}
