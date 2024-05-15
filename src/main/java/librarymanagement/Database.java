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
                    "  `studentRoll` int(1) DEFAULT NULL," +
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
                    "  `status` varchar(20) DEFAULT NULL," +
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
        String sql = "INSERT INTO student (studentNumber, studentName, password, dateOfBirth, studentRoll, gender, phone, email) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            prepare = connect.prepareStatement(sql);

            // Insert values for each student only if they don't already exist
            if (!studentExists(Database.connect, "0969571699")) {
                prepare.setString(1, "0969571699");
                prepare.setString(2, "Giang Khánh Quân");
                prepare.setString(3, "123456");
                prepare.setString(4, "1999-10-05");
                prepare.setInt(5, 1);
                prepare.setString(6, "Male");
                prepare.setString(7, "0123456789"); // Example phone number
                prepare.setString(8, "quan.giang@example.com");
                prepare.addBatch();
            }

            if (!studentExists(Database.connect, "123")) {
                prepare.setString(1, "123");
                prepare.setString(2, "Nguyễn Phúc Toàn");
                prepare.setString(3, "123456");
                prepare.setString(4, "2003-01-12");
                prepare.setInt(5, 1);
                prepare.setString(6, "Male");
                prepare.setString(7, "0987654321"); // Example phone number
                prepare.setString(8, "toan.nguyen@example.com");
                prepare.addBatch();
            }

            if (!studentExists(Database.connect, "456")) {
                prepare.setString(1, "456");
                prepare.setString(2, "Nguyễn Anh Đức");
                prepare.setString(3, "123456");
                prepare.setString(4, "2002-09-05");
                prepare.setInt(5, 1);
                prepare.setString(6, "Male");
                prepare.setString(7, "0123987654"); // Example phone number
                prepare.setString(8, "duc.nguyen@example.com");
                prepare.addBatch();
            }

            if (!studentExists(Database.connect, "789")) {
                prepare.setString(1, "789");
                prepare.setString(2, "Nguyễn Hoàng Long");
                prepare.setString(3, "123456");
                prepare.setString(4, "2000-03-20");
                prepare.setInt(5, 1);
                prepare.setString(6, "Male");
                prepare.setString(7, "0987123456"); // Example phone number
                prepare.setString(8, "long.nguyen@example.com");
                prepare.addBatch();
            }

            if (!studentExists(Database.connect, "27890")) {
                prepare.setString(1, "27890");
                prepare.setString(2, "Nguyễn Tiến Minh");
                prepare.setString(3, "123456");
                prepare.setString(4, "2004-05-20");
                prepare.setInt(5, 2);
                prepare.setString(6, "Male");
                prepare.setString(7, "0982789023"); // Example phone number
                prepare.setString(8, "minh.nguyen@example.com");
                prepare.addBatch();
            }

            // Execute the batch insert
            prepare.executeBatch();
        } catch (SQLException e) {
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

    private static boolean studentExists(Connection connection, String studentNumber) throws SQLException {
        String sql = "SELECT COUNT(*) FROM student WHERE studentNumber = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, studentNumber);
        ResultSet resultSet = preparedStatement.executeQuery();
        resultSet.next();
        return resultSet.getInt(1) > 0;
    }
}
