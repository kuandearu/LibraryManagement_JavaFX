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
}
