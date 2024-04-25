package librarymanagement;

import javafx.application.Application;
import javafx.stage.Stage;

import java.sql.*;

public class Database {

    public static Connection connectDB() {
        try {   
            //Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306"; // Include the database name
            String username = "root";
            String password = "";

            Connection connect = DriverManager.getConnection(url, username, password);

            // Create the database if it doesn't exist
            String createDatabaseQuery = "CREATE DATABASE IF NOT EXISTS librarydb";
            PreparedStatement createDatabaseStatement = connect.prepareStatement(createDatabaseQuery);
            createDatabaseStatement.executeUpdate();

            url += "/librarydb";
            connect = DriverManager.getConnection(url,username,password);

            // Create the table if it doesn't exist
            String createStudentTableQuery = "CREATE TABLE IF NOT EXISTS student ("
                    + "studentNumber VARCHAR(100), "
                    + "studentName NVARCHAR(100),"
                    + "password VARCHAR(100), "
                    + "image VARCHAR(500))";
            PreparedStatement createStudentTableStatement = connect.prepareStatement(createStudentTableQuery);
            createStudentTableStatement.executeUpdate();


            return connect;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void insertStudents(Connection connection) {
        String sql = "INSERT INTO student (studentNumber, studentName, password, image) VALUES (?, ?, ?, ?)";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            // Insert values for each student only if they don't already exist
            if (!studentExists(connection, "0969571699")) {
                preparedStatement.setString(1, "0969571699");
                preparedStatement.setString(2, "Giang Khánh Quân");
                preparedStatement.setString(3, "123456");
                preparedStatement.setString(4, "default_image_path");
                preparedStatement.addBatch();
            }

            if (!studentExists(connection, "123")) {
                preparedStatement.setString(1, "123");
                preparedStatement.setString(2, "Nguyễn Phúc Toàn");
                preparedStatement.setString(3, "123456");
                preparedStatement.setString(4, "default_image_path");
                preparedStatement.addBatch();
            }

            if (!studentExists(connection, "456")) {
                preparedStatement.setString(1, "456");
                preparedStatement.setString(2, "Nguyễn Anh Đức");
                preparedStatement.setString(3, "123456");
                preparedStatement.setString(4, "default_image_path");
                preparedStatement.addBatch();
            }

            if (!studentExists(connection, "789")) {
                preparedStatement.setString(1, "789");
                preparedStatement.setString(2, "Nguyễn Hoàng Long");
                preparedStatement.setString(3, "123456");
                preparedStatement.setString(4, "default_image_path");
                preparedStatement.addBatch();
            }


            // Execute the batch insert
            preparedStatement.executeBatch();
        } catch (SQLException e) {
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
