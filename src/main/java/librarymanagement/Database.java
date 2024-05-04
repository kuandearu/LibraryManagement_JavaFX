package librarymanagement;


import java.sql.*;

public class Database {

    private static Connection connect;
    private static PreparedStatement prepare;

    public static Connection connectDB() {
        try {
            String url = "jdbc:mysql://localhost:3306"; // Include the database name
            String username = "root";
            String password = "";

            connect = DriverManager.getConnection(url, username, password);

            // Create the database if it doesn't exist
            String createDatabaseQuery = "CREATE DATABASE IF NOT EXISTS librarydb";
            PreparedStatement createDatabaseStatement = connect.prepareStatement(createDatabaseQuery);
            createDatabaseStatement.executeUpdate();

            url += "/librarydb";
            connect = DriverManager.getConnection(url, username, password);

            // Create the table if it doesn't exist
            String createStudentTableQuery = "CREATE TABLE IF NOT EXISTS student ("
                    + "studentNumber VARCHAR(100), "
                    + "studentName NVARCHAR(100),"
                    + "password VARCHAR(100))";
            prepare = connect.prepareStatement(createStudentTableQuery);
            prepare.executeUpdate();

            String createBookTableQuery = "CREATE TABLE IF NOT EXISTS book ("
                    + "bookTitle VARCHAR(100), "
                    + "author VARCHAR(100), "
                    + "bookType VARCHAR(100), "
                    + "image VARCHAR(500), "
                    + "date DATE NULL)";
            prepare = connect.prepareStatement(createBookTableQuery);
            prepare.executeUpdate();

            String createTakeBookTableQuery = "CREATE TABLE IF NOT EXISTS take (" +
                    "  studentNumber varchar(100)," +
                    "  firstname varchar(100)," +
                    "  lastname varchar(100)," +
                    "  gender varchar(100)," +
                    "  bookTitle varchar(100)," +
                    "  author varchar(100)," +
                    "  bookType varchar(100)," +
                    "  image varchar(500)," +
                    "  date date," +
                    "  checkReturn varchar(100)" +
                    ")";
            prepare = connect.prepareStatement(createTakeBookTableQuery);
            prepare.executeUpdate();

            String createSaveBookTableQuery = "CREATE TABLE IF NOT EXISTS save (" +
                    "  studentNumber varchar(100)," +
                    "  bookTitle varchar(100)," +
                    "  author varchar(100)," +
                    "  bookType varchar(100)," +
                    "  image varchar(500)," +
                    "  date date" +
                    ")" ;
            prepare = connect.prepareStatement(createSaveBookTableQuery);
            prepare.executeUpdate();

            String createNewStudentTableQuery = "CREATE TABLE IF NOT EXISTS newStudent ("
                    + "id INT AUTO_INCREMENT PRIMARY KEY,"
                    + "studentNumber VARCHAR(100), "
                    + "studentName NVARCHAR(100),"
                    + "password VARCHAR(100))";
            prepare = connect.prepareStatement(createNewStudentTableQuery);
            prepare.executeUpdate();


        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connect;
    }

    public static void insertStudents() {
        String sql = "INSERT INTO student (studentNumber, studentName, password) VALUES (?, ?, ?)";
        try {
            prepare = connect.prepareStatement(sql);

            // Insert values for each student only if they don't already exist
            if (!studentExists(Database.connect, "0969571699")) {
                prepare.setString(1, "0969571699");
                prepare.setString(2, "Giang Khánh Quân");
                prepare.setString(3, "123456");
                prepare.addBatch();
            }

            if (!studentExists(Database.connect, "123")) {
                prepare.setString(1, "123");
                prepare.setString(2, "Nguyễn Phúc Toàn");
                prepare.setString(3, "123456");
                prepare.addBatch();
            }

            if (!studentExists(Database.connect, "456")) {
                prepare.setString(1, "456");
                prepare.setString(2, "Nguyễn Anh Đức");
                prepare.setString(3, "123456");
                prepare.addBatch();
            }

            if (!studentExists(Database.connect, "789")) {
                prepare.setString(1, "789");
                prepare.setString(2, "Nguyễn Hoàng Long");
                prepare.setString(3, "123456");
                prepare.addBatch();
            }
            // Execute the batch insert
            prepare.executeBatch();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void insertNewStudents() {
        String sql = "INSERT INTO student (id, studentName, studentNumber, password) VALUES (?, ?, ?,)";
        try {
            prepare = connect.prepareStatement(sql);

            // Insert values for each student only if they don't already exist
            if (!studentExists(Database.connect, "0969571699")) {
                prepare.setString(1, "0969571699");
                prepare.setString(2, "Giang Khánh Quân");
                prepare.setString(3, "123456");
                prepare.addBatch();
            }

            // Execute the batch insert
            prepare.executeBatch();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void insertBooks() throws SQLException{
        String sql = "INSERT INTO book (bookTitle, author, bookType, image, date) VALUES(?,?,?,?,?)";

        try{
            prepare= connect.prepareStatement(sql);

            //INSERT BOOKS WHEN THEY ONLY DONT EXIST
            if (!bookExists(Database.connect, "Java Tutorial")) {
                prepare.setString(1, "Java Tutorial");
                prepare.setString(2, "March");
                prepare.setString(3, "Thesis, Education, IT");
                prepare.setString(4, "src/main/java/image/java tutorial.jpg");
                prepare.setString(5, "2020-09-24");
                prepare.addBatch();
            }

            if (!bookExists(Database.connect, "JavaFX Tutorial")) {
                prepare.setString(1, "JavaFX Tutorial");
                prepare.setString(2, "Steven");
                prepare.setString(3, "Journal, Education, IT");
                prepare.setString(4, "src/main/java/image/javafx tutorial book.jpg");
                prepare.setString(5, "2023-06-27");
                prepare.addBatch();
            }

            if (!bookExists(Database.connect, "Programming Language")) {
                prepare.setString(1, "Programming Language");
                prepare.setString(2, "Ammy Adam");
                prepare.setString(3, "Note, Education, Tutorial, IT");
                prepare.setString(4, "src/main/java/image/programming language book.jpg");
                prepare.setString(5, "2024-05-30");
                prepare.addBatch();
            }

            if (!bookExists(Database.connect, "Python")) {
                prepare.setString(1, "Python");
                prepare.setString(2, "Army");
                prepare.setString(3, "Magazine, Education, Introduction, Tutorial");
                prepare.setString(4, "src/main/java/image/python tutorial.jpg");
                prepare.setString(5, "2022-09-12");
                prepare.addBatch();
            }

            // Execute the batch insert
            prepare.executeBatch();
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            if(prepare != null)
                prepare.close();
            if(connect != null)
                connect.close();
        }
    }

    private static boolean studentExists(Connection connection, String studentNumber) throws SQLException {
        String sql = "SELECT COUNT(*) FROM student WHERE studentNumber = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, studentNumber);
        ResultSet resultSet = preparedStatement.executeQuery();
        resultSet.next();
        return resultSet.getInt(1) > 0;
    }

    private static boolean bookExists(Connection connection, String bookTitle) throws SQLException {
        String sql = "SELECT COUNT(*) FROM book WHERE bookTitle = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, bookTitle);
        ResultSet resultSet = preparedStatement.executeQuery();
        resultSet.next();
        return resultSet.getInt(1) > 0;
    }
}
