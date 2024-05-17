package librarymanagement;

import javafx.animation.TranslateTransition;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import javafx.beans.Observable;
import javafx.beans.binding.Bindings;
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
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import javafx.scene.control.Alert.AlertType;


import java.io.File;
import java.net.URL;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

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
    private Label issueBook_title;

    @FXML
    private Circle circle_image;

    @FXML
    private TableColumn<availableBooks, Integer> col_ab_BookId;

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
    private FontAwesomeIcon edit_icon;

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
    private TableColumn<returnBook, String> returnBook_number;

    @FXML
    private TableColumn<returnBook, String> returnBook_status;

    @FXML
    private Button return_button;

    @FXML
    private TableColumn<saveBook, String> saveBook_author;

    @FXML
    private TableColumn<saveBook, String> saveBook_date;

    @FXML
    private TableColumn<saveBook, String> saveBook_title;

    @FXML
    private TableColumn<saveBook, String > saveBook_type;

    @FXML
    private Button unsave_btn;

    @FXML
    private ImageView save_imageView;

    @FXML
    private TableView<saveBook> saveBook_tableView;

    @FXML
    private TextField addAuthor_label;

    @FXML
    private ImageView addBookImage_View;

    @FXML
    private TextField addBookTitle_label;

    @FXML
    private TextField addBookType_label;

    @FXML
    private TextField addDate_label;

    @FXML
    private Button clearAddBook_btn;

    @FXML
    private Button uploadImage_View;

    @FXML
    private Button clearUpdateBook_btn;

    @FXML
    private TextField updateAuthor;

    @FXML
    private TextField updateBookID;

    @FXML
    private ImageView updateBookImage_View;

    @FXML
    private TextField updateBookTitle;

    @FXML
    private TextField updateBookType;

    @FXML
    private Button updateBook_btn;

    @FXML
    private AnchorPane updateBook_form;

    @FXML
    private TextField updateDate;

    @FXML
    private Button uploadUpdateImage_View;

    @FXML
    private ComboBox<?> addGender_text;

    @FXML
    private AnchorPane student_list;

    @FXML
    private ImageView studentImage_View;

    @FXML
    private Button clearAddStudent_btn;

    @FXML
    private TextField addStudentNumber_text;

    @FXML
    private TextField addFirstName_text;

    @FXML
    private TextField addLastName_text;

    @FXML
    private TextField addDateofBirth_text;

    @FXML
    private TextField addEmail_text;

    @FXML
    private ComboBox<?> addRoll_text;

    @FXML
    private TextField addPhone_text;

    @FXML
    private TextField addPassword_text;

    @FXML
    private TableView<userList> availableStudent_TableView;

    @FXML
    private TableColumn<?, ?> col_ab_DeleteStudent;

    @FXML
    private TableColumn<userList, String> col_ab_Email;

    @FXML
    private TableColumn<userList, String> col_ab_Gender;

    @FXML
    private TableColumn<userList, String> col_ab_Password;

    @FXML
    private TableColumn<userList, String> col_ab_Phone;

    @FXML
    private TableColumn<?, ?> col_ab_UpdateStudent;

    @FXML
    private TableColumn<userList, Date> col_ab_dateOfBirth;

    @FXML
    private TableColumn<userList, String> col_ab_studentName;

    @FXML
    private TableColumn<userList, String> col_ab_studentNumber;

    @FXML
    private ImageView showStudentImage_View;

    Image image;

    private Connection connect;
    private PreparedStatement prepare;
    private ResultSet result;
    private Statement statement;
    private File selectedFile;
    private String comboBox[] = {"Male", "Female", "Others"};
    private int rollBox[] = {1, 2};

    private boolean containsMaliciousContent(String input) {
        // Define a pattern to match potentially malicious content
        String regex = ".*(['\";\\-\\-]+|\\b(SELECT|INSERT|UPDATE|DELETE|DROP|CREATE|ALTER|EXEC|UNION|SCRIPT|<|>|\\(\\)|\\{|\\}|\\[|\\])\\b).*";
        Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
        return pattern.matcher(input).matches();
    }

    public void addStudent() {
        String sql = "INSERT INTO student(studentNumber, studentName, dateOfBirth, email, studentRoll, gender, phone, password, image) VALUES (?,?,?,?,?,?,?,?,?) ";

        connect = Database.connectDB();

        try {

            if (addStudentNumber_text.getText().isEmpty() ||
                    addFirstName_text.getText().isEmpty() ||
                    addLastName_text.getText().isEmpty() ||
                    addDateofBirth_text.getText().isEmpty() ||
                    addEmail_text.getText().isEmpty() ||
                    addPhone_text.getText().isEmpty() ||
                    addPassword_text.getText().isEmpty() ||
                    addGender_text.getValue() == null ||
                    addRoll_text.getValue() == null ||
                    studentImage_View.getImage() == null) {
                showAlert(AlertType.ERROR, "Program message", "Please insert all information!");
            } else {
                //Check validate date of birth
                String dateString = addDateofBirth_text.getText();
                Timestamp timestamp = validateAndConvertToTimestamp(dateString);
                if (timestamp == null) {
                    // Return if date format is invalid
                    return;
                }
                // Combine first name and last name into full name
                String fullName = addFirstName_text.getText() + " " + addLastName_text.getText();

                // Prepare SQL statement
                prepare = connect.prepareStatement(sql);
                prepare.setString(1, addStudentNumber_text.getText());
                prepare.setString(2, fullName);
                prepare.setTimestamp(3, timestamp);
                prepare.setString(4, addEmail_text.getText());
                prepare.setInt(5, (int) addRoll_text.getValue()); // Assuming roll is an Integer in the database
                prepare.setString(6, addGender_text.getValue().toString()); // Assuming gender is a String in the database
                prepare.setString(7, addPhone_text.getText());
                prepare.setString(8, addPassword_text.getText());

                String imagePath = selectedFile.getAbsolutePath();
                prepare.setString(9, imagePath);

                // Execute the SQL statement
                int rowsAffected = prepare.executeUpdate();

                if (rowsAffected > 0) {
                    // Show success message
                    showAlert(AlertType.INFORMATION, "Program message", "Student added successfully!");

                    // Clear input fields
                    clearAddStudent(); // Assuming you have a method to clear input fields
                } else {
                    // Show error message if insertion fails
                    showAlert(AlertType.ERROR, "Program message", "Failed to add student. Please try again.");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void clearAddStudent() {
        addStudentNumber_text.setText("");
        addFirstName_text.setText("");
        addLastName_text.setText("");
        addDateofBirth_text.setText("");
        addEmail_text.setText("");
        addPhone_text.setText("");
        addPassword_text.setText("");
        addGender_text.setValue(null);
        addRoll_text.setValue(null);
        studentImage_View.setImage(null);
    }

    public ObservableList<userList> studentList() {

        ObservableList<userList> listStudents = FXCollections.observableArrayList();

        String sql = "SELECT * FROM student";

        connect = Database.connectDB();

        try {
            userList student;

            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            while (result.next()) {
                student = new userList(
                        result.getString("studentNumber"),
                        result.getString("studentName"),
                        result.getDate("dateOfBirth"),
                        result.getString("gender"),
                        result.getString("phone"),
                        result.getString("email"),
                        result.getString("image"),
                        result.getString("password"));
                listStudents.add(student);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return listStudents;
    }

    //SHOWING STUDENT DATA
    private ObservableList<userList> listStudents;

    public void showAvailableStudents() {

        listStudents = studentList();

        col_ab_studentNumber.setCellValueFactory(new PropertyValueFactory<>("number"));
        col_ab_studentName.setCellValueFactory(new PropertyValueFactory<>("name"));
        col_ab_dateOfBirth.setCellValueFactory(new PropertyValueFactory<>("date"));
        col_ab_Gender.setCellValueFactory(new PropertyValueFactory<>("gender"));
        col_ab_Phone.setCellValueFactory(new PropertyValueFactory<>("phone"));
        col_ab_Email.setCellValueFactory(new PropertyValueFactory<>("email"));
        col_ab_Password.setCellValueFactory(new PropertyValueFactory<>("password"));

        availableStudent_TableView.setItems(listStudents);
    }

    userList getStudentData;

    public void selectAvailableStudents() {

        userList studentData = availableStudent_TableView.getSelectionModel().getSelectedItem();

        int num = availableStudent_TableView.getSelectionModel().getFocusedIndex();

        if ((num - 1) < -1) {
            return;
        }

        getStudentData = studentData;

        // This is required to display the image
        // Note: Don't forget the "file:"
        String uri = "file:" + studentData.getImage();

        Image image = new Image(uri, 134, 171, false, true);
        showStudentImage_View.setImage(image);
    }

    public void addBook() {

        String sql = "INSERT INTO book(bookTitle, author, bookType, image, date) VALUES (?,?,?,?,?) ";

        connect = Database.connectDB();

        try {
            Alert alert;

            if (addAuthor_label.getText().isEmpty() ||
                    addBookTitle_label.getText().isEmpty() ||
                    addBookType_label.getText().isEmpty() ||
                    addDate_label.getText().isEmpty() ||
                    addBookImage_View.getImage() == null) {
                showAlert(AlertType.ERROR, "Program message", "Please insert all information!");
            } else {
                String dateString = addDate_label.getText();
                Timestamp timestamp = validateAndConvertToTimestamp(dateString);
                if (timestamp == null) {
                    // Return if date format is invalid
                    return;
                }
                // Prepare SQL statement
                prepare = connect.prepareStatement(sql);
                prepare.setString(1, addBookTitle_label.getText()); // Assuming studentNumber is empty for book addition
                prepare.setString(2, addAuthor_label.getText()); // Assuming firstname is empty for book addition
                prepare.setString(3, addBookType_label.getText()); // Assuming lastname is empty for book addition

                //save the picture path
                String imagePath = selectedFile.getAbsolutePath();
                prepare.setString(4, imagePath);


                prepare.setTimestamp(5, timestamp); // Assuming gender is empty for book addition

                // Execute the SQL statement
                int rowsAffected = prepare.executeUpdate();

                if (rowsAffected > 0) {
                    // Show success message
                    showAlert(AlertType.INFORMATION, "Program message", "Book added successfully!");

                    // Clear input fields
                    clearAddBook();
                } else {
                    // Show error message if insertion fails
                    showAlert(AlertType.INFORMATION, "Program message", "Failed to add book. Please try again!");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void clearAddBook() {
        addBookTitle_label.setText("");
        addAuthor_label.setText("");
        addBookType_label.setText("");
        addDate_label.setText("");
        addBookImage_View.setImage(null);
    }


    public void findBookforUpdating(ActionEvent event) throws SQLException {

        String bookID = updateBookID.getText(); // Get the BookID from the TextField

        // Construct the SQL query to retrieve book information by BookID
        String sql = "SELECT * FROM book WHERE book_id = ?";

        connect = Database.connectDB();

        try {
            prepare = connect.prepareStatement(sql);
            prepare.setString(1, bookID); // Set the BookID parameter in the query
            result = prepare.executeQuery();
            boolean check = false;

            while (result.next()) {
                updateBookTitle.setText(result.getString("bookTitle"));
                updateAuthor.setText(result.getString("author"));
                updateBookType.setText(result.getString("bookType"));

                displayFormattedDate(result.getTimestamp("date"));

                getData.path = result.getString("image");

                String uri = "file:" + getData.path;

                image = new Image(uri, 127, 162, false, true);
                updateBookImage_View.setImage(image);

                check = true;
            }

            if (!check) {
                updateBookTitle.setText("Book is not available!");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void displayFormattedDate(Timestamp timestamp) {
        if (timestamp != null) {
            LocalDate localDate = timestamp.toLocalDateTime().toLocalDate();
            String formattedDate = localDate.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
            updateDate.setText(formattedDate);
        } else {
            updateDate.setText(""); // Set empty if the date is null
        }
    }

    public void updateBook() {
        String sql = "UPDATE book SET bookTitle=?, author=?, bookType=?, image=?, date=? WHERE book_id=?";

        connect = Database.connectDB();

        try {
            if ( updateBookID.getText().isEmpty() || updateBookTitle.getText().isEmpty() || updateAuthor.getText().isEmpty() ||
                    updateBookType.getText().isEmpty() || updateDate.getText().isEmpty()) {
                showAlert(AlertType.ERROR, "Program message", "Please insert all information!");
                return;
            }

            String bookID = updateBookID.getText();
            if (!isBookIDValid(bookID)) {
                showAlert(AlertType.ERROR, "Program message", "Book ID does not exist!");
                return;
            }

            prepare = connect.prepareStatement(sql);
            prepare.setString(6, bookID);

            prepare.setString(1, updateBookTitle.getText().isEmpty() ? result.getString("bookTitle") : updateBookTitle.getText());
            prepare.setString(2, updateAuthor.getText().isEmpty() ? result.getString("author") : updateAuthor.getText());
            prepare.setString(3, updateBookType.getText().isEmpty() ? result.getString("bookType") : updateBookType.getText());

            String imagePath = "";
            if (selectedFile != null) {
                // If a new image is selected, set imagePath accordingly
                imagePath = selectedFile.getAbsolutePath();
            } else {
                // If no new image is selected, or the result set is empty, retain the existing image path
                if (result.next()) {
                    imagePath = result.getString("image");
                } else {
                    showAlert(AlertType.ERROR, "Program message", "Image not found!");
                }
            }

            if (selectedFile != null) {
                prepare.setString(4, imagePath);
                // Additional code to update the database with the new image path
                // prepare.executeUpdate(); // Assuming prepare is your PreparedStatement
            }

            // Set the image path for display purposes
            updateBookImage_View.setImage(new Image(new File(imagePath).toURI().toString()));

            Timestamp timestamp = validateAndConvertToTimestamp(updateDate.getText());
            //Timestamp timestamp = (!updateDate.getText().isEmpty()) ? validateAndConvertToTimestamp(updateDate.getText()) : result.getTimestamp("date");
            prepare.setTimestamp(5, timestamp);

            int rowsAffected = prepare.executeUpdate();

            if (rowsAffected > 0) {
                showAlert(AlertType.INFORMATION, "Program message", "Book updated successfully!");
                clearUpdateBook();
            } else {
                showAlert(AlertType.ERROR, "Program message", "Failed to update book. Please try again.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void clearUpdateBook() {
        updateBookID.setText("");
        updateBookTitle.setText("");
        updateAuthor.setText("");
        updateBookType.setText("");
        updateDate.setText("");
        updateBookImage_View.setImage(null);
    }

    private boolean isBookIDValid(String bookID) throws SQLException {
        String checkSql = "SELECT * FROM book WHERE book_id=?";
        PreparedStatement checkStatement = connect.prepareStatement(checkSql);
        checkStatement.setString(1, bookID);
        ResultSet resultSet = checkStatement.executeQuery();
        return resultSet.next();
    }

    private void showAlert(AlertType type, String title, String content) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }

    public Timestamp validateAndConvertToTimestamp(String dateString) {
        try {
            // Parse the user-entered date
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            LocalDate localDate = LocalDate.parse(dateString, formatter);

            // Set the time part to 00:00:00 (start of day)
            LocalTime localTime = LocalTime.MIDNIGHT;

            // Combine the date and time into a LocalDateTime object
            LocalDateTime localDateTime = LocalDateTime.of(localDate, localTime);

            // Convert LocalDateTime to java.sql.Timestamp for database insertion
            return Timestamp.valueOf(localDateTime);
        } catch (DateTimeParseException e) {
            // If parsing fails, catch the exception and display error message
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Program message");
            alert.setHeaderText(null);
            alert.setContentText("Invalid date format! It should be 'dd/MM/yyyy'");
            alert.showAndWait();

            return null;
        }
    }

    public void uploadImage() {
        // Create a file chooser
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Image File");

        // Set initial directory
        File initialDirectory = new File("src/main/java/image/");
        fileChooser.setInitialDirectory(initialDirectory);

        // Filter to show only image files
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif");
        fileChooser.getExtensionFilters().add(extFilter);

        // Show open file dialog
        selectedFile = fileChooser.showOpenDialog(uploadImage_View.getScene().getWindow());

        if (selectedFile != null) {
            // Load the selected image into the ImageView
            Image image = new Image(selectedFile.toURI().toString(), 140, 162, false, true);
            addBookImage_View.setImage(image);
        }
    }

    public void uploadUpdateImage() {
        // Create a file chooser
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Image File");

        // Set initial directory
        File initialDirectory = new File("src/main/java/image/");
        fileChooser.setInitialDirectory(initialDirectory);

        // Filter to show only image files
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif");
        fileChooser.getExtensionFilters().add(extFilter);

        // Show open file dialog
        selectedFile = fileChooser.showOpenDialog(uploadImage_View.getScene().getWindow());

        if (selectedFile != null) {
            // Load the selected image into the ImageView
            Image image = new Image(selectedFile.toURI().toString(), 140, 162, false, true);
            updateBookImage_View.setImage(image);
        }
    }

    public void uploadStudentImage() {
        // Create a file chooser
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Image File");

        // Set initial directory
        File initialDirectory = new File("src/main/java/image/");
        fileChooser.setInitialDirectory(initialDirectory);

        // Filter to show only image files
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif");
        fileChooser.getExtensionFilters().add(extFilter);

        // Show open file dialog
        selectedFile = fileChooser.showOpenDialog(uploadImage_View.getScene().getWindow());

        if (selectedFile != null) {
            // Load the selected image into the ImageView
            Image image = new Image(selectedFile.toURI().toString(), 140, 162, false, true);
            studentImage_View.setImage(image);
        }
    }

    public ObservableList<availableBooks> dataList() {

        ObservableList<availableBooks> listBooks = FXCollections.observableArrayList();

        String sql = "SELECT * FROM book";

        connect = Database.connectDB();

        try {
            availableBooks aBooks;

            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            while (result.next()) {
                aBooks = new availableBooks(
                        result.getInt("book_id"),
                        result.getString("bookTitle"),
                        result.getString("author"),
                        result.getString("bookType"),
                        result.getString("image"),
                        result.getDate("date"),
                        result.getString("status"));
                listBooks.add(aBooks);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return listBooks;
    }

    //SHOWING BOOKS DATA
    private ObservableList<availableBooks> listBook;

    public void showAvailableBooks() {

        listBook = dataList();

        col_ab_BookId.setCellValueFactory(new PropertyValueFactory<>("id"));
        col_ab_bookTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
        col_ab_author.setCellValueFactory(new PropertyValueFactory<>("author"));
        col_ab_bookType.setCellValueFactory(new PropertyValueFactory<>("genre"));
        col_ab_publishedDate.setCellValueFactory(new PropertyValueFactory<>("date"));

        availableBooks_tableView.setItems(listBook);
    }

    availableBooks getBookData;

    public void selectAvailableBooks() {

        availableBooks bookData = availableBooks_tableView.getSelectionModel().getSelectedItem();

        int num = availableBooks_tableView.getSelectionModel().getFocusedIndex();

        if ((num - 1) < -1) {
            return;
        }
        getBookData = bookData;

        availableBooks_title.setText(bookData.getTitle());

//        THIS IS REQUIRED TO DISPLAY THE IMAGE
//        NOTE! DON'T FORGET THE "file:"
        String uri = "file:" + bookData.getImage();

        image = new Image(uri, 134, 171, false, true);
        availableBooks_imageView.setImage(image);

        getData.takeBookTitle = bookData.getTitle();
        getData.saveTitle = bookData.getTitle();
        getData.saveAuthor = bookData.getAuthor();
        getData.saveType = bookData.getGenre();
        getData.saveImg = bookData.getImage();
        getData.saveDate = (java.sql.Date) bookData.getDate();
    }

    public void roll() {
        List<Integer> rollCombo = new ArrayList<>();

        for (int data : rollBox) {
            rollCombo.add(data);
        }

        ObservableList listRoll = FXCollections.observableList(rollCombo);
        addRoll_text.setItems(listRoll);
    }

    public void gender() {
        List<String> combo = new ArrayList<>();

        for (String data : comboBox) {
            combo.add(data);
        }

        ObservableList list = FXCollections.observableList(combo);

        addGender_text.setItems(list);
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
                alert.setTitle("Program message");
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


    private boolean check_conditions(){
        return true;
    }

//    public void takeBook() throws SQLException {
//
//        Date date = new Date();
//        java.sql.Date sqlDate = new java.sql.Date(date.getTime());
//
//        String sql = "INSERT INTO take(`studentNumber`,`firstname`,`lastname`,`gender`," +
//                "`bookTitle`,`author`,`bookType`,`image`,`date`,`checkReturn`)" +
//                " VALUES(?,?,?,?,?,?,?,?,?,?)";
//
//        connect = Database.connectDB();
//
//        try{
//
//            Alert alert;
//
//            if(take_FirstName.getText().isEmpty()
//                    || take_LastName.getText().isEmpty()
//                    || addGender_text.getSelectionModel().isEmpty()){
//                alert = new Alert(AlertType.ERROR);
//                alert.setTitle("Program message");
//                alert.setHeaderText(null);
//                alert.setContentText("Please insert completely all Information!");
//                alert.showAndWait();
//            } else
//            {
//                prepare = connect.prepareStatement(sql);
//                prepare.setString(1, take_StudentNumber.getText());
//                prepare.setString(2, take_FirstName.getText());
//                prepare.setString(3, take_LastName.getText());
//                prepare.setString(4, (String)addGender_text.getSelectionModel().getSelectedItem());
//                prepare.setString(5, take_titleLabel.getText());
//                prepare.setString(6, take_authorLabel.getText());
//                prepare.setString(7, take_genreLabel.getText());
//                prepare.setString(8, getData.pathImage);
//                prepare.setDate(9, sqlDate);
//
//                String check = "Not Return";
//
//                prepare.setString(10, check);
//                prepare.executeUpdate();
//
//                alert = new Alert(AlertType.INFORMATION);
//                alert.setTitle("Program message");
//                alert.setHeaderText(null);
//                alert.setContentText("Successfully take the book");
//                alert.showAndWait();
//
//                clearTakeData();
//
//            }
//        }catch(Exception e){
//            e.printStackTrace();
//        } finally {
//            if(result != null)
//                result.close();
//            if(prepare != null)
//                prepare.close();
//            if(connect != null)
//                connect.close();
//        }
//    }


//    public void clearTakeData(){
//        issueBook_title.setText("");
//        take_BookTitle.setText("");
//        take_titleLabel.setText("");
//        take_authorLabel.setText("");
//        take_genreLabel.setText("");
//        take_dateLabel.setText("");
//        take_imageView.setImage(null);
//    }
//
    public void clearFindData(){
        take_titleLabel.setText("");
        take_authorLabel.setText("");
        take_genreLabel.setText("");
        take_dateLabel.setText("");
        take_imageView.setImage(null);
    }

//    public void displayDate(){
//        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
//        String date = format.format(new Date());
//        take_IssuedDate.setText(date);
//    }

    //Return book

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
                        result.getString("bookNumber"),
                        result.getString("bookTitle"),
                        result.getString("author"),
                        result.getString("bookType"),
                        result.getDate("date"),
                        result.getString("image"),
                        result.getString("status")

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

                showReturnBooks();
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
    //Save book

    public  ObservableList<saveBook> saveBookData(){
        ObservableList<saveBook> listSaveData = FXCollections.observableArrayList();
        String sql = "Select * from save where studentNumber = '"+ getData.studentNumber +"' ";
        //int count =0;
        connect = Database.connectDB();
        try {
            saveBook sBooks;
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();
            while(result.next()){
                sBooks = new saveBook(result.getString("bookTitle"),result.getString("author"),
                        result.getString("bookType"),result.getString("image"),
                        result.getDate("date"));
                listSaveData.add(sBooks);
                //count ++;
            }
//        Alert alert = new Alert(AlertType.INFORMATION);
//        alert.setTitle("Program message");
//        alert.setHeaderText(null);
//        alert.setContentText(""+ count +"");
//        alert.showAndWait();
        }catch (Exception e){
            e.printStackTrace();
        }
        return listSaveData;
    }

    private ObservableList<saveBook> sBookList;
    public void showSaveBook(){
        sBookList = saveBookData();
        int count =0;
        saveBook_title.setCellValueFactory(new PropertyValueFactory<>("title"));
        saveBook_author.setCellValueFactory(new PropertyValueFactory<>("author"));
        saveBook_type.setCellValueFactory(new PropertyValueFactory<>("type"));
        saveBook_date.setCellValueFactory(new PropertyValueFactory<>("date"));

        saveBook_tableView.setItems(sBookList);
    }

    public void saveBook(){
        String sql = "INSERT INTO save(`studentNumber`,`bookTitle`,`author`,`bookType`,`image`,`date`) VALUES(?,?,?,?,?,?)";
        connect = Database.connectDB();

        try {

            Alert alert;
            if(availableBooks_title.getText().isEmpty()){
                alert = new Alert(AlertType.ERROR);
                alert.setTitle("Program message");
                alert.setHeaderText(null);
                alert.setContentText("Please select the book!");
                alert.showAndWait();
            }
            else{
                prepare = connect.prepareStatement(sql);
                prepare.setString(1, getData.studentNumber);
                prepare.setString(2, getData.saveTitle);
                prepare.setString(3, getData.saveAuthor);
                prepare.setString(4, getData.saveType);
                prepare.setString(5, getData.saveImg);
                prepare.setDate(6, getData.saveDate);
                prepare.executeUpdate();

                alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Program message");
                alert.setHeaderText(null);
                alert.setContentText("Book saved!");
                alert.showAndWait();

                showSaveBook();
            }


        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void selectSaveBook(){
        saveBook sBook = saveBook_tableView.getSelectionModel().getSelectedItem();
        int num = saveBook_tableView.getSelectionModel().getFocusedIndex();

        if ((num - 1) < -1) {
            return;
        }

        getData.takeBookTitle = sBook.getTitle();

        getData.path = sBook.getImg();

        String uri = "file:" + getData.path;

        image = new Image(uri, 108,147, false, true);
        save_imageView.setImage(image);
        getData.saveImg = sBook.getImg();
        getData.saveTitle = sBook.getTitle();
    }

    public void unsaveBook(){
        String sql = "Delete from save where bookTitle = '" + getData.saveTitle + "' and studentNumber = '" + getData.studentNumber + "'";

        connect = Database.connectDB();
        try {
            Alert alert;

            if(save_imageView.getImage() == null){
                alert = new Alert(AlertType.ERROR);
                alert.setTitle("Program message");
                alert.setHeaderText(null);
                alert.setContentText("Please select the book you want to unsave!");
                alert.showAndWait();
            }else{
                statement = connect.createStatement();
                statement.executeUpdate(sql);
                alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Program message");
                alert.setHeaderText(null);
                alert.setContentText("Unsave successfully!");
                alert.showAndWait();

                showSaveBook();
                save_imageView.setImage(null);
            }

        }catch (Exception e){
            e.printStackTrace();
        }

    }


    public void abTakeButton(ActionEvent event){

        if (event.getSource()== take_btn){
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
        }

        issueBook_title.setText(" " +getBookData.getTitle());
        take_titleLabel.setText(getBookData.getTitle());
        take_authorLabel.setText(getBookData.getAuthor());
        take_genreLabel.setText(getBookData.getGenre());
        take_dateLabel.setText(getBookData.getDate().toString());

        String uri = "file:" + getBookData.getImage();
        getData.pathImage = getBookData.getImage();
        image = new Image(uri, 134, 171, false, true);
        take_imageView.setImage(image);

//        Alert alert;
//        alert = new Alert(AlertType.INFORMATION);
//        alert.setTitle("Program message");
//        alert.setHeaderText(null);
//        alert.setContentText(bookData.getTitle());
//        alert.showAndWait();

    }

    public void studentNumber(){
//        WE CAN DISPLAY THE STUDENT NUMBER THAT STUDENT USED
        studentNumber_label.setText(getData.studentNumber);
    }

    public void hideInsertImage(){
        edit_btn.setVisible(false);
    }

    public void setUserImage() {
        String rootPath = System.getProperty("user.dir");
        String relativeImagePath = "/src/main/java/image/logo.png";
        String absoluteImagePath = rootPath + relativeImagePath;

        image = new Image("file:" + absoluteImagePath, 130, 87, false, true);
        circle_image.setFill(new ImagePattern(image));
        smallCircle_image.setFill(new ImagePattern(image));
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
            showSaveBook();
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

            showSaveBook();
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
        showAvailableStudents();
        setUserImage();
        hideInsertImage();
        studentNumber();
        gender();
        roll();
//        uploadImage_View.setOnAction(event -> uploadImage());
        try {
            showSaveBook();
        }catch (Exception e){
            e.printStackTrace();
        }

        try{
            showReturnBooks();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
//    public void insertImage(){
//
//        FileChooser open = new FileChooser();
//        open.setTitle("Image file");
//        open.getExtensionFilters().add(new FileChooser.ExtensionFilter("Image file", "*png", "*jpg"));
//        Stage stage = (Stage) nav_form.getScene().getWindow();
//
//        File file = open.showOpenDialog(stage);
//
//        if(file != null){
//
//            image = new Image(file.toURI().toString(),130,87,false,true);
//            circle_image.setFill(new ImagePattern(image));
//            smallCircle_image.setFill(new ImagePattern(image));
//
//            getData.path = file.getAbsolutePath();
//            System.out.println(getData.path);
//
//            changeProfile();
//        }
//    }
//
//    public void changeProfile(){
//
//        String uri = getData.path;
//        //uri.replace("\\","\\\\");
//        String uri_convert = uri.replace("\\","/");
//        String sql = "Update student set image = '"+ uri_convert +"' where studentNumber = '"+ getData.studentNumber +"' ";
//        connect = Database.connectDB();
//        try {
//
//            statement = connect.createStatement();
//            statement.executeUpdate(sql);
//
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//    }
//
//    public void showProfile(){
//        String uri = "file:" + getData.path;
//        image = new Image(uri, 130, 87, false, true);
//        circle_image.setFill(new ImagePattern(image));
//        smallCircle_image.setFill(new ImagePattern(image));
//    }

//    public void DesignInsertImage(){
//        circle_image.setOnMouseEntered((MouseEvent event)->{
//            edit_btn.setVisible(true);
//        });
//        circle_image.setOnMouseExited((MouseEvent event)->{
//            edit_btn.setVisible(false);
//        });
//
//        edit_btn.setOnMouseEntered((MouseEvent event)->{
//            edit_btn.setVisible(true);
//            edit_icon.setFill(Color.valueOf("#fff"));
//        });
//        edit_btn.setOnMousePressed((MouseEvent event)->{
//            edit_btn.setVisible(true);
//            edit_icon.setFill(Color.RED);
//        });
//        edit_btn.setOnMouseExited((MouseEvent event)->{
//            edit_btn.setVisible(false);
//        });
//
//
//    }
}
