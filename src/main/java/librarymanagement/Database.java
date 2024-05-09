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
            String createStudentTableQuery = "CREATE TABLE IF NOT EXISTS student (" +
                    "`student_id` int(100) NOT NULL AUTO_INCREMENT," +
                    "  `studentNumber` varchar(100) DEFAULT NULL," +
                    "  `studentName` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL," +
                    "  `dateOfBirth` date DEFAULT NULL," +
                    "  `gender` varchar(10) DEFAULT NULL," +
                    "  `phone` varchar(10) DEFAULT NULL," +
                    "  `email` varchar(50) DEFAULT NULL," +
                    "  `password` varchar(100) DEFAULT NULL," +
                    "  PRIMARY KEY (`student_id`))";
            prepare = connect.prepareStatement(createStudentTableQuery);
            prepare.executeUpdate();

            String createBookTableQuery = "CREATE TABLE IF NOT EXISTS book (" +
                    "`book_id` int(100) NOT NULL AUTO_INCREMENT," +
                    "  `bookNumber` varchar(100) DEFAULT NULL," +
                    "  `bookTitle` varchar(100) DEFAULT NULL," +
                    "  `author` varchar(100) DEFAULT NULL," +
                    "  `bookType` varchar(100) DEFAULT NULL," +
                    "  `image` varchar(500) DEFAULT NULL," +
                    "  `date` date DEFAULT NULL," +
                    "  PRIMARY KEY (`book_id`))";
            prepare = connect.prepareStatement(createBookTableQuery);
            prepare.executeUpdate();

            String createTakeBookTableQuery = "CREATE TABLE IF NOT EXISTS take (" +
                    "`id` int(100) NOT NULL AUTO_INCREMENT," +
                    "  `studentNumber` varchar(100) DEFAULT NULL," +
                    "  `firstname` varchar(100) DEFAULT NULL," +
                    "  `lastname` varchar(100) DEFAULT NULL," +
                    "  `gender` varchar(100) DEFAULT NULL," +
                    "  `bookTitle` varchar(100) DEFAULT NULL," +
                    "  `author` varchar(100) NOT NULL," +
                    "  `bookType` varchar(100) NOT NULL," +
                    "  `image` varchar(500) DEFAULT NULL," +
                    "  `date` date DEFAULT NULL," +
                    "  `checkReturn` varchar(100) DEFAULT NULL," +
                    "  PRIMARY KEY (`id`))";
            prepare = connect.prepareStatement(createTakeBookTableQuery);
            prepare.executeUpdate();

            String createSaveBookTableQuery = "CREATE TABLE IF NOT EXISTS save (" +
                    "`id` int(100) NOT NULL AUTO_INCREMENT," +
                    "  `studentNumber` varchar(100) DEFAULT NULL," +
                    "  `bookTitle` varchar(100) DEFAULT NULL," +
                    "  `author` varchar(100) DEFAULT NULL," +
                    "  `bookType` varchar(100) DEFAULT NULL," +
                    "  `image` varchar(500) DEFAULT NULL," +
                    "  `date` date DEFAULT NULL," +
                    "  PRIMARY KEY (`id`))" ;
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
        String sql = "INSERT INTO student (studentNumber, studentName, password, dateOfBirth) VALUES (?, ?, ?, ?fff)";
        try {
            prepare = connect.prepareStatement(sql);

            // Insert values for each student only if they don't already exist
            if (!studentExists(Database.connect, "0969571699")) {
                prepare.setString(1, "0969571699");
                prepare.setString(2, "Giang Khánh Quân");
                prepare.setString(3, "123456");
                prepare.setString(4, "1999-10-05");
                prepare.addBatch();
            }

            if (!studentExists(Database.connect, "123")) {
                prepare.setString(1, "123");
                prepare.setString(2, "Nguyễn Phúc Toàn");
                prepare.setString(3, "123456");
                prepare.setString(4, "2003-01-12");
                prepare.addBatch();
            }

            if (!studentExists(Database.connect, "456")) {
                prepare.setString(1, "456");
                prepare.setString(2, "Nguyễn Anh Đức");
                prepare.setString(3, "123456");
                prepare.setString(4, "2002-09-05");
                prepare.addBatch();
            }

            if (!studentExists(Database.connect, "789")) {
                prepare.setString(1, "789");
                prepare.setString(2, "Nguyễn Hoàng Long");
                prepare.setString(3, "123456");
                prepare.setString(4, "2000-03-20");
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
        String sql = "INSERT INTO student (id, studentName, studentNumber, password) VALUES (?, ?, ?,?)";
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
