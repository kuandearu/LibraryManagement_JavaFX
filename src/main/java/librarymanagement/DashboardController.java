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
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import javafx.scene.control.Alert.AlertType;


import java.net.URL;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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

    @FXML
    private TextField take_BookTitle;

    @FXML
    private TextField take_FirstName;

    @FXML
    private ComboBox<?> take_Gender;

    @FXML
    private Label take_IssuedDate;

    @FXML
    private TextField take_LastName;

    @FXML
    private Label take_StudentNumber;

    @FXML
    private Label take_authorLabel;

    @FXML
    private Button take_clearBtn;

    @FXML
    private Label take_dateLabel;

    @FXML
    private Label take_genreLabel;

    @FXML
    private ImageView take_imageView;

    @FXML
    private Button take_takeBtn;

    @FXML
    private Label take_titleLabel;

    @FXML
    private TableColumn<returnBook, String> returnBook_author;

    @FXML
    private TableColumn<returnBook, String> returnBook_date;

    @FXML
    private ImageView return_imageView;

    @FXML
    private TableView<returnBook> return_tableView;

    @FXML
    private TableColumn<returnBook, String> returnBook_title;

    @FXML
    private TableColumn<returnBook, String> returnBook_type;

    @FXML
    private Button return_button;

    Image image;

    private Connection connect;
    private PreparedStatement prepare;
    private ResultSet result;
    private Statement statement;
    private String comboBox[] = {"Male", "Female", "Others"};

    public void gender(){
        List<String> combo = new ArrayList<>();

        for(String data: comboBox){
            combo.add(data);
        }

        ObservableList list = FXCollections.observableList(combo);

        take_Gender.setItems(list);
    }

    public void takeBook() throws SQLException {

        Date date = new Date();
        java.sql.Date sqlDate = new java.sql.Date(date.getTime());

        String sql = "INSERT INTO take VALUES(?,?,?,?,?,?,?,?,?,?)";

        connect = Database.connectDB();

        try{

            Alert alert;

            if(take_FirstName.getText().isEmpty()
            || take_LastName.getText().isEmpty()
            || take_Gender.getSelectionModel().isEmpty()){
                alert = new Alert(AlertType.ERROR);
                alert.setTitle("Admin Message");
                alert.setHeaderText(null);
                alert.setContentText("Please insert completely all Student's Information!");
                alert.showAndWait();
            }else if(take_titleLabel.getText().isEmpty()){
                alert = new Alert(AlertType.ERROR);
                alert.setTitle("Admin Message");
                alert.setHeaderText(null);
                alert.setContentText("Please indicate the book you want to take");
                alert.showAndWait();
            }else if(take_titleLabel.getText().equals("Book is not available!")) {
                alert = new Alert(AlertType.ERROR);
                alert.setTitle("Admin Message");
                alert.setHeaderText(null);
                alert.setContentText("The selected book is not available. Please select another book.");
                alert.showAndWait();
            }
            else{

                prepare = connect.prepareStatement(sql);
                prepare.setString(1, take_StudentNumber.getText());
                prepare.setString(2, take_FirstName.getText());
                prepare.setString(3, take_LastName.getText());
                prepare.setString(4, (String)take_Gender.getSelectionModel().getSelectedItem());
                prepare.setString(5, take_titleLabel.getText());
                prepare.setString(6, take_authorLabel.getText());
                prepare.setString(7, take_genreLabel.getText());
                prepare.setString(8, getData.path);
                prepare.setDate(9, sqlDate);

                String check = "Not Return";

                prepare.setString(10, check);
                prepare.executeUpdate();

                alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Admin Message");
                alert.setHeaderText(null);
                alert.setContentText("Successfully take the book");
                alert.showAndWait();

                clearTakeData();

            }
        }catch(Exception e){
            e.printStackTrace();
        } finally {
            if(result != null)
                result.close();
            if(prepare != null)
                prepare.close();
            if(connect != null)
                connect.close();
        }
    }

    public void findBook(ActionEvent event) throws SQLException {

        clearFindData();

        String sql = "SELECT * FROM book WHERE bookTitle = '" + take_BookTitle.getText() + "'";

        connect = Database.connectDB();

        try{
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();
            boolean check = false;

            Alert alert;

            if(take_BookTitle.getText().isEmpty()){
                alert = new Alert(AlertType.ERROR);
                alert.setTitle("Admin Message");
                alert.setHeaderText(null);
                alert.setContentText("Please select the book!");
                alert.showAndWait();
            }else{
                while (result.next()){
                    take_titleLabel.setText(result.getString("bookTitle"));
                    take_authorLabel.setText(result.getString("author"));
                    take_genreLabel.setText(result.getString("bookType"));
                    take_dateLabel.setText(result.getString("date"));

                    getData.path = result.getString("image");

                    String uri = "file:" + getData.path;

                    image = new Image(uri, 127,162, false, true);
                    take_imageView.setImage(image);

                    check = true;
                }

                if(!check){
                    take_titleLabel.setText("Book is not available!");
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        } finally {
            if(result != null)
                result.close();
            if(prepare != null)
                prepare.close();
            if(connect != null)
                connect.close();
        }
    }

    public void studentNumberLabel(){
        take_StudentNumber.setText(getData.studentNumber);
    }

    public void clearTakeData(){
        take_BookTitle.setText("");
        take_titleLabel.setText("");
        take_authorLabel.setText("");
        take_genreLabel.setText("");
        take_dateLabel.setText("");
        take_imageView.setImage(null);
    }

    public void clearFindData(){
        take_titleLabel.setText("");
        take_authorLabel.setText("");
        take_genreLabel.setText("");
        take_dateLabel.setText("");
        take_imageView.setImage(null);
    }

    public void displayDate(){
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        String date = format.format(new Date());
        take_IssuedDate.setText(date);
    }

    public ObservableList<returnBook> returnBookData(){
        ObservableList<returnBook> listReturnBook = FXCollections.observableArrayList();
        String checkReturn = "Not Return";
        String sql = "SELECT * FROM take WHERE checkReturn = '"+ checkReturn +"' AND studentNumber = '"+ getData.studentNumber +"' ";
        Alert alert;
        connect = Database.connectDB();
        try{
            returnBook rBooks;
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();
            while (result.next()){
                rBooks = new returnBook(
                        result.getString("bookTitle"),
                        result.getString("author"),
                        result.getString("bookType"),
                        result.getDate("date"),
                        result.getString("image")

                );

                listReturnBook.add(rBooks);
            }

        }catch (Exception e){
            e.printStackTrace();
        }
        return listReturnBook;
    }

    public void returnBook(){
        String sql = "UPDATE take SET checkReturn = 'Returned' WHERE bookTitle = '"+ getData.takeBookTitle +"' ";
        connect = Database.connectDB();

        try{

            if(return_imageView.getImage() == null){
                Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("Program message");
                alert.setHeaderText(null);
                alert.setContentText("Please select the book you want to return!");
                alert.showAndWait();
            }else{
                statement = connect.createStatement();
                statement.executeUpdate(sql);
                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Program message");
                alert.setHeaderText(null);
                alert.setContentText("Return successfully!");
                alert.showAndWait();
            }

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void selectReturnBook(){
        returnBook rBooks = return_tableView.getSelectionModel().getSelectedItem();
        int num = return_tableView.getSelectionModel().getFocusedIndex();

        if ((num - 1) < -1) {
//            Alert alert = new Alert(AlertType.INFORMATION);
//            alert.setTitle("Program message");
//            alert.setHeaderText(null);
//            alert.setContentText(""+ num +"");
//            alert.showAndWait();
            return;
        }

        getData.takeBookTitle = rBooks.getTitle();

        getData.path = rBooks.getImage();

        String uri = "file:" + getData.path;

        image = new Image(uri, 134,171, false, true);
        return_imageView.setImage(image);

    }

    private ObservableList<returnBook> returnData;
    public void showReturnBooks(){
        returnData = returnBookData();

        returnBook_title.setCellValueFactory(new PropertyValueFactory<>("title"));
        returnBook_author.setCellValueFactory(new PropertyValueFactory<>("author"));
        returnBook_type.setCellValueFactory(new PropertyValueFactory<>("type"));
        returnBook_date.setCellValueFactory(new PropertyValueFactory<>("date"));

        return_tableView.setItems(returnData);
    }

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
            showReturnBooks();

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

            showReturnBooks();


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
        gender();
        studentNumberLabel();
        displayDate();

        try{
            showReturnBooks();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
