package librarymanagement;

import javafx.animation.TranslateTransition;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
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
import javafx.scene.layout.VBox;
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
import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.*;
import java.util.regex.Matcher;
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

    @FXML
    private TextField updateEmail_text;

    @FXML
    private TextField updateFirstName_text;

    @FXML
    private ComboBox<?> updateGender_text;

    @FXML
    private TextField updateLastName_text;

    @FXML
    private TextField updatePassword_text;

    @FXML
    private TextField updatePhone_text;

    @FXML
    private ComboBox<?> updateRoll_text;

    @FXML
    private ImageView updateStudentImage_View;

    @FXML
    private TextField updateStudentNumber_text;

    @FXML
    private Button updateStudent_btn;

    @FXML
    private AnchorPane updateStudent_form;

    @FXML
    private Button addStudentBtn;

    @FXML
    private Button showStudentBtn;

    @FXML
    private Button updateStudentBtn;

    @FXML
    private Button deleteStudentBtn;

    @FXML
    private AnchorPane addStudent_form;

    @FXML
    private AnchorPane showStudent_form;

    @FXML
    private Button addBook_btn;

    @FXML
    private AnchorPane addBook_form;

    @FXML
    private Button showBook_btn;

    @FXML
    private AnchorPane showBooks_form;

    @FXML
    private Button updateBooks_btn;

    @FXML
    private AnchorPane updateBooks_form;

    @FXML
    private TextField searchBook_field;

    @FXML
    private TextField searchStudent_field;

    @FXML
    private TextField updateBookNumber_label;

    @FXML
    private TextField updateBookTitle_label;

    @FXML
    private TextField updateBookType_label;

    @FXML
    private TextField updateAuthor_label;

    @FXML
    private TextField updateDate_label;

    @FXML
    private Button show_deleteButton;

    @FXML
    private Button show_updateButton;

    @FXML
    private Button student_deleteBtn;

    @FXML
    private Button student_updateBtn;

    @FXML
    private TextField addBookNumber_label;

    Image image;

    private Connection connect;
    private PreparedStatement prepare;
    private ResultSet result;
    private Statement statement;
    private File selectedFile;
    private String comboBox[] = {"Male", "Female", "Others"};
    private String rollBox[] = {"Admin", "User"};


    public void addPerson() {
        String sql = "INSERT INTO student(studentNumber, studentName, dateOfBirth, email, studentRole, gender, phone, password, image) VALUES (?,?,?,?,?,?,?,?,?) ";

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
                // Validate input fields
                if (containsMaliciousContent(addStudentNumber_text.getText()) ||
                        containsMaliciousContent(addFirstName_text.getText()) ||
                        containsMaliciousContent(addLastName_text.getText()) ||
                        containsMaliciousContent(addDateofBirth_text.getText()) ||
                        containsMaliciousContent(addEmail_text.getText()) ||
                        containsMaliciousContent(addPhone_text.getText()) ||
                        containsMaliciousContent(addPassword_text.getText())) {

                    // Clear all text fields
                   clearAddPerson();

                    showAlert(AlertType.ERROR, "Program message", "Are you trying to hack? Nice try!");
                    return;
                }

                // Check and convert date of birth
                String dateString = addDateofBirth_text.getText();
                Timestamp timestamp = validateAndConvertToTimestamp(dateString);
                if (timestamp == null) {
                    return; // Invalid date format
                }

                if (!isValidPhoneNumber(addPhone_text.getText())) {
                    showAlert(AlertType.ERROR, "Program message", "Invalid phone number! Please enter a 10-digit phone number.");
                    return;
                }

                // Validate email
                if (!isValidEmail(addEmail_text.getText())) {
                    showAlert(AlertType.ERROR, "Program message", "Invalid email format! Please enter a valid email address (e.g., user@gmail.com).");
                    return;
                }

                // Combine first name and last name into full name
                String fullName = addLastName_text.getText() + " " + addFirstName_text.getText();

                // Prepare SQL statement
                prepare = connect.prepareStatement(sql);
                prepare.setString(1, addStudentNumber_text.getText());
                prepare.setString(2, fullName);
                prepare.setTimestamp(3, timestamp);
                prepare.setString(4, addEmail_text.getText());
                prepare.setString(5, addRoll_text.getValue().toString()); // Assuming roll is an Integer in the database
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
                    clearAddPerson(); // Assuming you have a method to clear input fields

                    // Refresh the student table view
                    showAvailableStudents();
                } else {
                    // Show error message if insertion fails
                    showAlert(AlertType.ERROR, "Program message", "Failed to add student. Please try again.");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void clearAddPerson() {
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
                        result.getString("studentRole"),
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

        student_deleteBtn.setDisable(true);
        student_updateBtn.setDisable(true);
    }

    userList getStudentData;

    public void selectAvailableStudents() {

        userList studentData = availableStudent_TableView.getSelectionModel().getSelectedItem();

        int num = availableStudent_TableView.getSelectionModel().getFocusedIndex();

        if ((num - 1) < -1) {
            return;
        }

        student_deleteBtn.setDisable(false);
        student_updateBtn.setDisable(false);
        getStudentData = studentData;

        getData.userName = getStudentData.getName();
        getData.userNumber = getStudentData.getNumber();
        getData.userDoB = (java.sql.Date) getStudentData.getDate();
        getData.userGender = getStudentData.getGender();
        getData.userPhone = getStudentData.getPhone();
        getData.userEmail = getStudentData.getEmail();
        getData.userImg = getStudentData.getImage();
        getData.userPass = getStudentData.getPassword();
        getData.userRole = getStudentData.getRole();
        System.out.println(getData.userNumber);
        // This is required to display the image
        // Note: Don't forget the "file:"
        String uri = "file:" + getStudentData.getImage();

        Image image = new Image(uri, 134, 171, false, true);
        showStudentImage_View.setImage(image);
    }

    public void studentUpdateBtn(ActionEvent e) {
        if(e.getSource() == student_updateBtn){
            addStudent_form.setVisible(false);
            showStudent_form.setVisible(false);
            updateStudent_form.setVisible(true);

            updateStudentNumber_text.setText(getData.userNumber);
            String studentName = getData.userName;
            String[] nameParts = studentName.split("\\s+", 2); // Split into two parts: last name and rest
            if (nameParts.length == 2) {
                String firstName = nameParts[1]; // Rest as first name
                String lastName = nameParts[0]; // First word as last name

                updateFirstName_text.setText(firstName);
                updateLastName_text.setText(lastName);
            } else {
                // If the name format is not as expected, set the whole name as first name
                updateFirstName_text.setText(studentName);
                updateLastName_text.setText(""); // Set last name as empty
            }

            updateEmail_text.setText(getData.userEmail);
            updatePhone_text.setText(getData.userPhone);
            updatePassword_text.setText(getData.userPass);

            String gender = getData.userGender;
            ComboBox genderBox = updateGender_text;
            setComboBoxValue(genderBox, gender);


            String roll = getData.userRole;
            ComboBox rollbox = updateRoll_text;
            setComboBoxValue(rollbox, roll);

            String dbDate = getData.userDoB.toString(); // Assuming userDoB is of type java.sql.Date
            String formattedDate = convertDateFormat(dbDate);
            updateDate.setText(formattedDate);

            String imagePath = getData.userImg;

            String uri = "file:" + imagePath;
            Image image = new Image(uri, 127, 162, false, true);
            updateStudentImage_View.setImage(image);
        }
    }

    public void studentDeleteBtn(ActionEvent e){
        if (e.getSource() == student_deleteBtn) {
            Alert alert = new Alert(AlertType.CONFIRMATION);
            alert.setTitle("Confirmation");
            alert.setHeaderText("Do you want to remove this user");
            if (alert.showAndWait().get().equals(javafx.scene.control.ButtonType.OK)) {
                deleteUserData();
            } else {

            }
        }
    }

    public void deleteUserData(){
        String sql = "Delete from student where studentNumber = '" + getData.userNumber + "'";
        System.out.println(getData.userNumber);
        connect = Database.connectDB();
        try {
            Alert alert;

            if (showStudentImage_View.getImage() == null) {
                alert = new Alert(AlertType.ERROR);
                alert.setTitle("Program message");
                alert.setHeaderText(null);
                alert.setContentText("Please select user you want to delete!");
                alert.showAndWait();
            } else {
                statement = connect.createStatement();
                statement.executeUpdate(sql);
                alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Program message");
                alert.setHeaderText(null);
                alert.setContentText("User deleted successfully!");
                alert.showAndWait();

                // Refresh the student table view
                showAvailableStudents();
                showStudentImage_View.setImage(null);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void findPersonByNumber(ActionEvent event) throws SQLException {
        String studentNumber = updateStudentNumber_text.getText(); // Get the student number from the TextField

        // Construct the SQL query to retrieve student information by student number
        String sql = "SELECT * FROM student WHERE studentNumber = ?";

        connect = Database.connectDB();

        try {
            prepare = connect.prepareStatement(sql);
            prepare.setString(1, studentNumber); // Set the student number parameter in the query
            result = prepare.executeQuery();
            boolean check = false;

                while (result.next()) {

                    String studentName = result.getString("studentName");
                    String[] nameParts = studentName.split("\\s+", 2); // Split into two parts: last name and rest
                    if (nameParts.length == 2) {
                        String firstName = nameParts[1]; // Rest as first name
                        String lastName = nameParts[0]; // First word as last name

                        updateFirstName_text.setText(firstName);
                        updateLastName_text.setText(lastName);
                    } else {
                        // If the name format is not as expected, set the whole name as first name
                        updateFirstName_text.setText(studentName);
                        updateLastName_text.setText(""); // Set last name as empty
                    }

                    updateEmail_text.setText(result.getString("email"));
                    updatePhone_text.setText(result.getString("phone"));
                    updatePassword_text.setText(result.getString("password"));

                    String gender = result.getString("gender");
                    ComboBox genderBox = updateGender_text;
                    setComboBoxValue(genderBox, gender);


                    String roll = result.getString("studentRole");
                    ComboBox rollbox = updateRoll_text;
                    setComboBoxValue(rollbox, roll);

                    displayFormattedDate(result.getTimestamp("dateOfBirth"));

                    String imagePath = result.getString("image");

                    String uri = "file:" + imagePath;
                    Image image = new Image(uri, 127, 162, false, true);
                    updateStudentImage_View.setImage(image);

                    check = true;
                }

                if (!check) {
                    showAlert(AlertType.INFORMATION, "Program message", "No Student Found!");
                    clearUpdatePerson();
                }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updatePerson() {
        String sql = "UPDATE student SET studentRole=?, studentNumber=?, studentName=?, dateOfBirth=?, gender=?, phone=?, email=?, password=?, image=? WHERE studentNumber=?";

        connect = Database.connectDB();

        try {
            if (updateStudentNumber_text.getText().isEmpty()) {
                showAlert(AlertType.ERROR, "Program message", "Please enter the student number!");
                return;
            }

            if (updateStudentNumber_text.getText().isEmpty() ||
                    updateFirstName_text.getText().isEmpty() ||
                    updateLastName_text.getText().isEmpty() ||
                    updateEmail_text.getText().isEmpty() ||
                    updatePhone_text.getText().isEmpty() ||
                    updatePassword_text.getText().isEmpty() ||
                    updateGender_text.getValue() == null ||
                    updateRoll_text.getValue() == null ||
                    updateDate.getText().isEmpty() ||
                    updateStudentImage_View.getImage() == null) {
                showAlert(AlertType.ERROR, "Program message", "Please insert all information!");
                return;
            }

            String imagePath = processImagePath(getData.studentNumber);
            if (imagePath == null) {
                return; // If imagePath is null, there was an error, and we should not proceed
            }

            if (!isValidPhoneNumber(updatePhone_text.getText())) {
                showAlert(AlertType.ERROR, "Program message", "Invalid phone number! Please enter a 10-digit phone number.");
                return;
            }

            // Validate email
            if (!isValidEmail(updateEmail_text.getText())) {
                showAlert(AlertType.ERROR, "Program message", "Invalid email format! Please enter a valid email address (e.g., user@gmail.com).");
                return;
            }

            String studentNumber = updateStudentNumber_text.getText();

            // Retrieve existing student data
            String fetchSql = "SELECT * FROM student WHERE studentNumber = ?";
            prepare = connect.prepareStatement(fetchSql);
            prepare.setString(1, studentNumber);
            result = prepare.executeQuery();

            if (result.next()) {
                // Prepare SQL statement
                prepare = connect.prepareStatement(sql);
                prepare.setString(10, result.getString("studentNumber"));
                // Update studentRoll
                prepare.setString(1, updateRoll_text.getValue() != null ? updateRoll_text.getValue().toString() : result.getString("studentRole"));

                // Update studentNumber
                prepare.setString(2, updateStudentNumber_text.getText().isEmpty() ? result.getString("studentNumber") : updateStudentNumber_text.getText());

                // Update studentName
                String fullName = (updateLastName_text.getText().isEmpty() ? result.getString("studentName").split(" ")[0] : updateLastName_text.getText())
                        + " " + (updateFirstName_text.getText().isEmpty() ? result.getString("studentName").split(" ")[1] : updateFirstName_text.getText());
                prepare.setString(3, fullName);

                // Update dateOfBirth
                Timestamp timestamp = validateAndConvertToTimestamp(updateDate.getText());
                if(timestamp == null){
                    return;
                }
                prepare.setTimestamp(4, updateDate.getText().isEmpty() ? result.getTimestamp("dateOfBirth") : timestamp);

                // Update gender
                prepare.setString(5, updateGender_text.getValue() != null ? updateGender_text.getValue().toString() : result.getString("gender"));

                // Update phone
                prepare.setString(6, updatePhone_text.getText().isEmpty() ? result.getString("phone") : updatePhone_text.getText());

                // Update email
                prepare.setString(7, updateEmail_text.getText().isEmpty() ? result.getString("email") : updateEmail_text.getText());

                // Update password
                prepare.setString(8, updatePassword_text.getText().isEmpty() ? result.getString("password") : updatePassword_text.getText());

                prepare.setString(9, imagePath);
                // Execute the SQL statement
                int rowsAffected = prepare.executeUpdate();

                if (rowsAffected > 0) {
                    showAlert(AlertType.INFORMATION, "Program message", "Student updated successfully!");

                    // Clear input fields
                    clearUpdatePerson();

                    // Refresh the student table view
                    showAvailableStudents();
                } else {
                    showAlert(AlertType.ERROR, "Program message", "Failed to update student. Please try again.");
                }
            } else {
                showAlert(AlertType.ERROR, "Program message", "No Student Found!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String processImagePath(String studentNumber) {
        String existingImagePath = "";
        String imagePath = "";

        // Fetch existing image path from the database
        String fetchImageSql = "SELECT image FROM student WHERE studentNumber=?";
        try (Connection connect = Database.connectDB();
             PreparedStatement fetchImageStmt = connect.prepareStatement(fetchImageSql)) {
            fetchImageStmt.setString(1, studentNumber);
            try (ResultSet result = fetchImageStmt.executeQuery()) {
                if (result.next()) {
                    existingImagePath = result.getString("image");
                } else {
                    showAlert(AlertType.ERROR, "Program message", "Student not found!");
                    return null;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // Process image path
        if (selectedFile != null) {
            // If a new image is selected, set imagePath to the absolute path of the selected file
            imagePath = selectedFile.getAbsolutePath();
        } else {
            // If no new image is selected, retain the existing image path
            imagePath = existingImagePath;
        }
        return imagePath;
    }


    public void clearUpdatePerson(){
        updateStudentNumber_text.setText("");
        updateFirstName_text.setText("");
        updateLastName_text.setText("");
        updateDate.setText("");
        updateGender_text.setValue(null);
        updateRoll_text.setValue(null);
        updatePhone_text.setText("");
        updateEmail_text.setText("");
        updatePassword_text.setText("");
        updateStudentImage_View.setImage(null);
    }

    public void setComboBoxValue(ComboBox<String> comboBox, String value) {
        comboBox.setValue(value);
    }


    public void addBook() {

        String sql = "INSERT INTO book(bookNumber, bookTitle, author, bookType, image, date) VALUES (?,?,?,?,?,?) ";

        connect = Database.connectDB();

        try {
            Alert alert;

            if (addBookNumber_label.getText().isEmpty() ||
                    addAuthor_label.getText().isEmpty() ||
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
                prepare.setString(1, addBookNumber_label.getText());
                prepare.setString(2, addBookTitle_label.getText()); // Assuming studentNumber is empty for book addition
                prepare.setString(3, addAuthor_label.getText()); // Assuming firstname is empty for book addition
                prepare.setString(4, addBookType_label.getText()); // Assuming lastname is empty for book addition

                //save the picture path
                String imagePath = selectedFile.getAbsolutePath();
                prepare.setString(5, imagePath);


                prepare.setTimestamp(6, timestamp); // Assuming gender is empty for book addition

                // Execute the SQL statement
                int rowsAffected = prepare.executeUpdate();

                if (rowsAffected > 0) {
                    // Show success message
                    showAlert(AlertType.INFORMATION, "Program message", "Book added successfully!");

                    // Clear input fields
                    clearAddBook();

                    //refresh showBook
                    showAvailableBooks();
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
        addBookNumber_label.setText("");
        addBookTitle_label.setText("");
        addAuthor_label.setText("");
        addBookType_label.setText("");
        addDate_label.setText("");
        addBookImage_View.setImage(null);
    }


    public void findBookByNumber(ActionEvent event) throws SQLException {

        String bookNumber = updateBookNumber_label.getText(); // Get the book number from the TextField

        // Construct the SQL query to retrieve book information by book number
        String sql = "SELECT * FROM book WHERE bookNumber = ?";

        connect = Database.connectDB();

        try {
            prepare = connect.prepareStatement(sql);
            prepare.setString(1, bookNumber); // Set the book number parameter in the query
            result = prepare.executeQuery();
            boolean check = false;

            if (result.next()) {
                // Set book details in the respective text fields
                updateBookTitle_label.setText(result.getString("bookTitle"));
                updateAuthor_label.setText(result.getString("author"));
                updateBookType_label.setText(result.getString("bookType"));
                updateDate_label.setText(result.getString("date").toString());

                String imagePath = result.getString("image");
                String uri = "file:" + imagePath;
                Image image = new Image(uri, 127, 162, false, true);
                updateBookImage_View.setImage(image);

                check = true;
            }

            if (!check) {
                showAlert(AlertType.INFORMATION, "Program message", "No Book Found!");
                clearUpdateBook();
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
        String sql = "UPDATE book SET bookNumber=?, bookTitle=?, author=?, bookType=?, date=?, image=? WHERE bookNumber=?";

        connect = Database.connectDB();

        try {
            if (updateBookNumber_label.getText().isEmpty()) {
                showAlert(AlertType.ERROR, "Program message", "Please enter the book number!");
                return;
            }

            if (updateBookNumber_label.getText().isEmpty() ||
                    updateBookTitle_label.getText().isEmpty() ||
                    updateAuthor_label.getText().isEmpty() ||
                    updateBookType_label.getText().isEmpty() ||
                    updateDate_label.getText().isEmpty() ||
                    updateBookImage_View.getImage() == null) {
                showAlert(AlertType.ERROR, "Program message", "Please insert all information!");
                return;
            }

            String imagePath = processBookImagePath(updateBookNumber_label.getText());
            if (imagePath == null) {
                return; // If imagePath is null, there was an error, and we should not proceed
            }

            String bookNumber = updateBookNumber_label.getText();

            // Retrieve existing book data
            String fetchSql = "SELECT * FROM book WHERE bookNumber = ?";
            prepare = connect.prepareStatement(fetchSql);
            prepare.setString(1, bookNumber);
            result = prepare.executeQuery();

            if (result.next()) {
                // Prepare SQL statement
                prepare = connect.prepareStatement(sql);
                prepare.setString(7, result.getString("bookNumber"));

                // Update book properties
                prepare.setString(1, updateBookNumber_label.getText().isEmpty() ? result.getString("bookNumber") : updateBookNumber_label.getText());
                prepare.setString(2, updateBookTitle_label.getText().isEmpty() ? result.getString("title") : updateBookTitle_label.getText());
                prepare.setString(3, updateAuthor_label.getText().isEmpty() ? result.getString("author") : updateAuthor_label.getText());
                prepare.setString(4, updateBookType_label.getText().isEmpty() ? result.getString("genre") : updateBookType_label.getText());

                // Update publishDate
                String publishDate = updateDate_label.getText().isEmpty() ? result.getString("publishDate") : updateDate_label.getText();
                prepare.setString(5, publishDate);

                prepare.setString(6, imagePath);

                // Execute the SQL statement
                int rowsAffected = prepare.executeUpdate();

                if (rowsAffected > 0) {
                    showAlert(AlertType.INFORMATION, "Program message", "Book updated successfully!");
                    clearUpdateBook();
                    showAvailableBooks();
                } else {
                    showAlert(AlertType.ERROR, "Program message", "Failed to update book. Please try again.");
                }
            } else {
                showAlert(AlertType.ERROR, "Program message", "No Book Found!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String processBookImagePath(String bookNumber) {
        String existingImagePath = "";
        String imagePath = "";

        // Fetch existing image path from the database
        String fetchImageSql = "SELECT image FROM book WHERE bookNumber=?";
        try (Connection connect = Database.connectDB();
             PreparedStatement fetchImageStmt = connect.prepareStatement(fetchImageSql)) {
            fetchImageStmt.setString(1, bookNumber);
            try (ResultSet result = fetchImageStmt.executeQuery()) {
                if (result.next()) {
                    existingImagePath = result.getString("image");
                } else {
                    showAlert(AlertType.ERROR, "Program message", "Book not found!");
                    return null;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // Process image path
        if (selectedFile != null) {
            // If a new image is selected, set imagePath to the absolute path of the selected file
            imagePath = selectedFile.getAbsolutePath();
        } else {
            // If no new image is selected, retain the existing image path
            imagePath = existingImagePath;
        }
        return imagePath;
    }


    public void clearUpdateBook() {
        updateBookNumber_label.setText("");
        updateBookTitle_label.setText("");
        updateAuthor_label.setText("");
        updateBookType_label.setText("");
        updateDate_label.setText("");
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

    public void uploadUpdateBookImage() {
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

    public void uploadPersonImage() {
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

    public String convertDateFormat(String dbDate) {
        DateTimeFormatter dbFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        DateTimeFormatter uiFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate date = LocalDate.parse(dbDate, dbFormatter);
        return date.format(uiFormatter);
    }

    public void uploadUpdatePersonImage() {
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
            updateStudentImage_View.setImage(image);
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
                        result.getString("bookNumber"),
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
        show_updateButton.setDisable(true);
        show_deleteButton.setDisable(true);
    }

    availableBooks getBookData;
    public void selectAvailableBooks() {

        availableBooks bookData = availableBooks_tableView.getSelectionModel().getSelectedItem();

        int num = availableBooks_tableView.getSelectionModel().getFocusedIndex();

        if ((num - 1) < -1) {
            return;
        }
        getBookData = bookData;
        show_updateButton.setDisable(false);
        show_deleteButton.setDisable(false);
        availableBooks_title.setText(bookData.getTitle());

//        THIS IS REQUIRED TO DISPLAY THE IMAGE
//        NOTE! DON'T FORGET THE "file:"
        String uri = "file:" + bookData.getImage();

        image = new Image(uri, 134, 171, false, true);
        availableBooks_imageView.setImage(image);

        getData.takeBookTitle = getBookData.getTitle();
        getData.saveTitle = getBookData.getTitle();
        getData.saveAuthor = getBookData.getAuthor();
        getData.saveType = getBookData.getGenre();
        getData.saveImg = getBookData.getImage();
        getData.saveDate = (java.sql.Date) getBookData.getDate();

        getData.updateNumber = getBookData.getNumber();
        getData.updateTitle = getBookData.getTitle();
        getData.updateAuthor = getBookData.getAuthor();
        getData.updateType = getBookData.getGenre();
        getData.updateImg = getBookData.getImage();
        getData.updateDate = (java.sql.Date) getBookData.getDate();
    }

    //Update button in show book list
    public void showUpdateButton(ActionEvent e){
        if(e.getSource() == show_updateButton){
            addBook_form.setVisible(false);
            showBooks_form.setVisible(false);
            updateBooks_form.setVisible(true);
        }
        updateBookNumber_label.setText(getData.updateNumber.toString());
        updateBookTitle_label.setText(getData.updateTitle.toString());
        updateAuthor_label.setText(getData.updateAuthor.toString());
        updateBookType_label.setText(getData.updateType.toString());
        updateDate_label.setText(getData.updateDate.toString());

        String imagePath = getData.updateImg.toString();
        String uri = "file:" + imagePath;
        Image image = new Image(uri, 127, 162, false, true);
        updateBookImage_View.setImage(image);
    }

    public void showDeleteButton(ActionEvent e) {
        if (e.getSource() == show_deleteButton) {
            Alert alert = new Alert(AlertType.CONFIRMATION);
            alert.setTitle("Confirmation");
            alert.setHeaderText("Do you want to remove this book");
            if (alert.showAndWait().get().equals(javafx.scene.control.ButtonType.OK)) {
                deleteBookData();
            } else {

            }
        }
    }

    private void deleteBookData() {
        String sql = "Delete from book where bookNumber = '" + getData.updateNumber + "'";

        connect = Database.connectDB();
        try {
            Alert alert;

            if (availableBooks_imageView.getImage() == null) {
                alert = new Alert(AlertType.ERROR);
                alert.setTitle("Program message");
                alert.setHeaderText(null);
                alert.setContentText("Please select the book you want to delete!");
                alert.showAndWait();
            } else {
                statement = connect.createStatement();
                statement.executeUpdate(sql);
                alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Program message");
                alert.setHeaderText(null);
                alert.setContentText("Book deleted successfully!");
                alert.showAndWait();

                showAvailableBooks();
                availableBooks_imageView.setImage(null);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void roll() {
        List<String> rollCombo = new ArrayList<>();

        for (String data : rollBox) {
            rollCombo.add(data);
        }

        ObservableList listRoll = FXCollections.observableList(rollCombo);

        addRoll_text.setItems(listRoll);

        updateRoll_text.setItems(listRoll);
    }

    public void gender() {
        List<String> combo = new ArrayList<>();

        for (String data : comboBox) {
            combo.add(data);
        }

        ObservableList list = FXCollections.observableList(combo);

        addGender_text.setItems(list);

        updateGender_text.setItems(list);
    }

    @FXML
    public void findStudent(ActionEvent event) throws SQLException {
        String searchQuery = searchStudent_field.getText().trim();

        String sql = "SELECT * FROM student WHERE studentName LIKE ?";
        connect = Database.connectDB();

        ObservableList<userList> studentList = FXCollections.observableArrayList();

        try {
            prepare = connect.prepareStatement(sql);
            prepare.setString(1, "%" + searchQuery + "%");
            result = prepare.executeQuery();

            while (result.next()) {
                int id = result.getInt("student_id");
                String role = result.getString("studentRole");
                String studentNumber = result.getString("studentNumber");
                String studentName = result.getString("studentName");
                Date dateOfBirth = result.getDate("dateOfBirth");
                String gender = result.getString("gender");
                String phone = result.getString("phone");
                String email = result.getString("email");
                String password = result.getString("password");
                String imagePath = result.getString("image");

                userList student = new userList(role,studentNumber, studentName, dateOfBirth, gender, phone, email, password, imagePath);
                studentList.add(student);
            }

            if (studentList.isEmpty()) {
                showAllStudents(studentList);
            } else {
                availableStudent_TableView.setItems(studentList);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (result != null) result.close();
            if (prepare != null) prepare.close();
            if (connect != null) connect.close();
        }
    }

    private void showAllStudents(ObservableList<userList> studentList) throws SQLException {
        String sql = "SELECT * FROM student";
        connect = Database.connectDB();

        try {
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            while (result.next()) {
                int id = result.getInt("student_id");
                String role = result.getString("studentRole");
                String studentNumber = result.getString("studentNumber");
                String studentName = result.getString("studentName");
                Date dateOfBirth = result.getDate("dateOfBirth");
                String gender = result.getString("gender");
                String phone = result.getString("phone");
                String email = result.getString("email");
                String password = result.getString("password");
                String imagePath = result.getString("image");

                userList student = new userList(role,studentNumber, studentName, dateOfBirth, gender, phone, email, password, imagePath);
                studentList.add(student);
            }

            availableStudent_TableView.setItems(studentList);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (result != null) result.close();
            if (prepare != null) prepare.close();
            if (connect != null) connect.close();
        }
    }

    @FXML
    public void findBook(ActionEvent event) throws SQLException {
        String searchQuery = searchBook_field.getText().trim();


        String sql = "SELECT * FROM book WHERE bookTitle LIKE ?";
        connect = Database.connectDB();

        ObservableList<availableBooks> bookList = FXCollections.observableArrayList();

        try {
            prepare = connect.prepareStatement(sql);
            prepare.setString(1, "%" + searchQuery + "%");
            result = prepare.executeQuery();

            while (result.next()) {
                int id = result.getInt("book_id");
                String number = result.getString("bookNumber");
                String bookTitle = result.getString("bookTitle");
                String author = result.getString("author");
                String bookType = result.getString("bookType");
                String imagePath = result.getString("image");
                Date publishedDate = result.getDate("date");
                String status = result.getString("status");

                availableBooks book = new availableBooks(id,number, bookTitle, author, bookType, imagePath, publishedDate, status);
                bookList.add(book);
            }

            if (bookList.isEmpty()) {
                showAllBooks(bookList);
            } else {
                availableBooks_tableView.setItems(bookList);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (result != null) result.close();
            if (prepare != null) prepare.close();
            if (connect != null) connect.close();
        }
    }

    private void showAllBooks(ObservableList<availableBooks> bookList) throws SQLException {
        String sql = "SELECT * FROM book";
        connect = Database.connectDB();

        try {
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            while (result.next()) {
                int id = result.getInt("book_id");
                String number = result.getString("bookNumber");
                String bookTitle = result.getString("bookTitle");
                String author = result.getString("author");
                String bookType = result.getString("bookType");
                String imagePath = result.getString("image");
                Date publishedDate = result.getDate("date");
                String status = result.getString("status");

                availableBooks book = new availableBooks(id,number, bookTitle, author, bookType, imagePath, publishedDate, status);
                bookList.add(book);
            }

            availableBooks_tableView.setItems(bookList);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (result != null) result.close();
            if (prepare != null) prepare.close();
            if (connect != null) connect.close();
        }
    }


    private boolean check_conditions(){
        return true;
    }

    private boolean studentExists(String studentNumber) throws SQLException {
        String sql = "SELECT COUNT(*) FROM student WHERE studentNumber = ?";
        prepare = connect.prepareStatement(sql);
        prepare.setString(1, studentNumber);
        result = prepare.executeQuery();
        result.next();
        return result.getInt(1) > 0;
    }

    private boolean containsMaliciousContent(String input) {
        // Define a pattern to match potentially malicious content
        String regex = ".*(['\";\\-\\-]+|\\b(SELECT|INSERT|UPDATE|DELETE|DROP|CREATE|ALTER|EXEC|UNION|SCRIPT|<|>|\\(\\)|\\{|\\}|\\[|\\])\\b).*";
        Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
        return pattern.matcher(input).matches();
    }

    public void clearFindData(){
        col_ab_bookTitle.setText("");
        col_ab_author.setText("");
        col_ab_bookType.setText("");
        col_ab_publishedDate.setText("");
        availableBooks_imageView.setImage(null);
    }


    public ObservableList<returnBook> returnBookData(){
        ObservableList<returnBook> listReturnBook = FXCollections.observableArrayList();
        String get_status = "Not returned";
        String sql = "SELECT * FROM book_request where status = '"+get_status+"' ";
        Alert alert;
        connect = Database.connectDB();
        try{
            returnBook rBooks;
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();
            while (result.next()){
                rBooks = new returnBook(
                        result.getString("studentNumber"),
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
        String update_status = "Returned";
        String sql = "UPDATE book_request set status = '"+update_status+"' where bookTitle = '" + getData.takeBookTitle + "' and studentNumber = '"+getData.saveNumber+"' ";
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
            return;
        }
        else{
            getData.saveNumber = rBooks.getNumber();
            getData.takeBookTitle = rBooks.getTitle();

            getData.path = rBooks.getImage();

            String uri = "file:" + getData.path;

            image = new Image(uri, 134,171, false, true);
            return_imageView.setImage(image);
        }


    }

    private ObservableList<returnBook> returnData;
    public void showReturnBooks(){
        returnData = returnBookData();

        returnBook_number.setCellValueFactory(new PropertyValueFactory<>("number"));
        returnBook_title.setCellValueFactory(new PropertyValueFactory<>("title"));
        returnBook_author.setCellValueFactory(new PropertyValueFactory<>("author"));
        returnBook_type.setCellValueFactory(new PropertyValueFactory<>("type"));
        returnBook_date.setCellValueFactory(new PropertyValueFactory<>("date"));
        returnBook_status.setCellValueFactory(new PropertyValueFactory<>("status"));

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
                sBooks = new saveBook(
                        result.getString("studentNumber"),
                        result.getString("bookTitle"),
                        result.getString("author"),
                        result.getString("bookType"),
                        result.getString("image"),
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

    private boolean isValidPhoneNumber(String phone) {
        return phone != null && phone.matches("\\d{10}");
    }

    private boolean isValidEmail(String email) {
        String emailRegex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$";
        Pattern pattern = Pattern.compile(emailRegex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }


    public void abTakeButton(ActionEvent event){

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

            student_list.setVisible(true);
            availableBooks_form.setVisible(false);
            returnBook_form.setVisible(false);

//            availableBooks_btn.setStyle("-fx-background-color: linear-gradient(to bottom right, #46589a, #4278a7);");
//            issueBooks_btn.setStyle("-fx-background-color: linear-gradient(to bottom right, #344275, #3a6389);");
//            returnBooks_btn.setStyle("-fx-background-color: linear-gradient(to bottom right, #344275, #3a6389);");
//            savedBooks_btn.setStyle("-fx-background-color: linear-gradient(to bottom right, #344275, #3a6389);");

            halfNav_availableBtn.setStyle("-fx-background-color: linear-gradient(to bottom right, #46589a, #4278a7);");
            halfNav_takeBtn.setStyle("-fx-background-color: linear-gradient(to bottom right, #344275, #3a6389);");
            halfNav_returnBtn.setStyle("-fx-background-color: linear-gradient(to bottom right, #344275, #3a6389);");
//            halfNav_saveBtn.setStyle("-fx-background-color: linear-gradient(to bottom right, #344275, #3a6389);");

            currentForm_label.setText("List Students");
        }else if (event.getSource() == halfNav_takeBtn){

            student_list.setVisible(false);
            availableBooks_form.setVisible(true);
            returnBook_form.setVisible(false);

//            issueBooks_btn.setStyle("-fx-background-color: linear-gradient(to bottom right, #46589a, #4278a7);");
//            availableBooks_btn.setStyle("-fx-background-color: linear-gradient(to bottom right, #344275, #3a6389);");
//            returnBooks_btn.setStyle("-fx-background-color: linear-gradient(to bottom right, #344275, #3a6389);");
//            savedBooks_btn.setStyle("-fx-background-color: linear-gradient(to bottom right, #344275, #3a6389);");

            halfNav_takeBtn.setStyle("-fx-background-color: linear-gradient(to bottom right, #46589a, #4278a7);");
            halfNav_availableBtn.setStyle("-fx-background-color: linear-gradient(to bottom right, #344275, #3a6389);");
            halfNav_returnBtn.setStyle("-fx-background-color: linear-gradient(to bottom right, #344275, #3a6389);");
//            halfNav_saveBtn.setStyle("-fx-background-color: linear-gradient(to bottom right, #344275, #3a6389);");

            currentForm_label.setText("List Books");

        }else if (event.getSource() == halfNav_returnBtn){

            student_list.setVisible(false);
            availableBooks_form.setVisible(false);
            returnBook_form.setVisible(true);

            halfNav_takeBtn.setStyle("-fx-background-color: linear-gradient(to bottom right, #46589a, #4278a7);");
            halfNav_availableBtn.setStyle("-fx-background-color: linear-gradient(to bottom right, #344275, #3a6389);");
            halfNav_returnBtn.setStyle("-fx-background-color: linear-gradient(to bottom right, #344275, #3a6389);");

            currentForm_label.setText("Return Books");
            showReturnBooks();

        }
    }


    public void buttonStudent(ActionEvent event) {
        if (event.getSource() == addStudentBtn) {
            addStudent_form.setVisible(true);
            showStudent_form.setVisible(false);
            updateStudent_form.setVisible(false);
        }else if (event.getSource() == showStudentBtn){
            addStudent_form.setVisible(false);
            showStudent_form.setVisible(true);
            updateStudent_form.setVisible(false);
        }else if (event.getSource() == updateStudentBtn){
            addStudent_form.setVisible(false);
            showStudent_form.setVisible(false);
            updateStudent_form.setVisible(true);
        }
    }


    public void buttonBook(ActionEvent event) {
        if (event.getSource() == addBook_btn) {
            addBook_form.setVisible(true);
            showBooks_form.setVisible(false);
            updateBooks_form.setVisible(false);
        }else if (event.getSource() == showBook_btn){
            addBook_form.setVisible(false);
            showBooks_form.setVisible(true);
            updateBooks_form.setVisible(false);
        }else if (event.getSource() == updateBooks_btn){
            addBook_form.setVisible(false);
            showBooks_form.setVisible(false);
            updateBooks_form.setVisible(true);
        }
    }

    public void navButtonDesign(ActionEvent event){

        if (event.getSource() == availableBooks_btn){

            student_list.setVisible(true);
            availableBooks_form.setVisible(false);
            returnBook_form.setVisible(false);

            availableBooks_btn.setStyle("-fx-background-color: linear-gradient(to bottom right, #46589a, #4278a7);");
            issueBooks_btn.setStyle("-fx-background-color: linear-gradient(to bottom right, #344275, #3a6389);");
            returnBooks_btn.setStyle("-fx-background-color: linear-gradient(to bottom right, #344275, #3a6389);");

            halfNav_availableBtn.setStyle("-fx-background-color: linear-gradient(to bottom right, #46589a, #4278a7);");
            halfNav_takeBtn.setStyle("-fx-background-color: linear-gradient(to bottom right, #344275, #3a6389);");
            halfNav_returnBtn.setStyle("-fx-background-color: linear-gradient(to bottom right, #344275, #3a6389);");

            currentForm_label.setText("Student List");

        }else if (event.getSource() == issueBooks_btn){

            student_list.setVisible(false);
            availableBooks_form.setVisible(true);
            returnBook_form.setVisible(false);

            issueBooks_btn.setStyle("-fx-background-color: linear-gradient(to bottom right, #46589a, #4278a7);");
            availableBooks_btn.setStyle("-fx-background-color: linear-gradient(to bottom right, #344275, #3a6389);");
            returnBooks_btn.setStyle("-fx-background-color: linear-gradient(to bottom right, #344275, #3a6389);");

            halfNav_takeBtn.setStyle("-fx-background-color: linear-gradient(to bottom right, #46589a, #4278a7);");
            halfNav_availableBtn.setStyle("-fx-background-color: linear-gradient(to bottom right, #344275, #3a6389);");
            halfNav_returnBtn.setStyle("-fx-background-color: linear-gradient(to bottom right, #344275, #3a6389);");

            currentForm_label.setText("List Books");

        }else if (event.getSource() == returnBooks_btn){

            student_list.setVisible(false);
            availableBooks_form.setVisible(false);
            returnBook_form.setVisible(true);

            returnBooks_btn.setStyle("-fx-background-color: linear-gradient(to bottom right, #46589a, #4278a7);");
            availableBooks_btn.setStyle("-fx-background-color: linear-gradient(to bottom right, #344275, #3a6389);");
            issueBooks_btn.setStyle("-fx-background-color: linear-gradient(to bottom right, #344275, #3a6389);");

            halfNav_returnBtn.setStyle("-fx-background-color: linear-gradient(to bottom right, #46589a, #4278a7);");
            halfNav_takeBtn.setStyle("-fx-background-color: linear-gradient(to bottom right, #344275, #3a6389);");
            halfNav_availableBtn.setStyle("-fx-background-color: linear-gradient(to bottom right, #344275, #3a6389);");

            currentForm_label.setText("Return Books");

            showReturnBooks();

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
        showReturnBooks();
        gender();
        roll();
//        uploadImage_View.setOnAction(event -> uploadImage());
        try {
            showSaveBook();
        }catch (Exception e){
            e.printStackTrace();
        }

    }

}
