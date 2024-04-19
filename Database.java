package Database;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.SQLException;

public class Database {
	private static final String url = "jdbc:mysql://localhost:3306/";
	private static final String database_name = "library";
	private static final String username = "root";
	private static final String password = "";
	
	private static Connection conn = null;
	private static Statement st = null;
	
	public Database() {
		
	}
	
	public void createDatabase() {
		try {
			conn = DriverManager.getConnection(url, username, password);
			st = conn.createStatement();
			
			String sql = "CREATE DATABASE IF NOT EXISTS " + database_name;
			st.executeUpdate(sql);
			System.out.println("Create database successfully!");
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(st != null)
				st.close();
				if(conn != null)
				conn.close();
			}  catch(SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void createTable() {
		try {
			String urlWithDatabase = url + database_name;
			conn = DriverManager.getConnection(urlWithDatabase, username, password);
			st = conn.createStatement();
			
			String createBooks = "CREATE TABLE IF NOT EXISTS books ("
	                + "ID INT AUTO_INCREMENT PRIMARY KEY,"
	                + "Title VARCHAR(50) NOT NULL,"
	                + "Author VARCHAR(50) NOT NULL,"
	                + "Genre VARCHAR(50) NOT NULL,"
	                + "Date VARCHAR(10) NOT NULL,"
	                + "Publisher VARCHAR(20) NOT NULL)";

	        String createMagazines = "CREATE TABLE IF NOT EXISTS magazines ("
	                + "ID INT AUTO_INCREMENT PRIMARY KEY,"
	                + "Title VARCHAR(50) NOT NULL,"
	                + "Genre VARCHAR(50) NOT NULL,"
	                + "Date VARCHAR(10) NOT NULL,"
	                + "Publisher VARCHAR(20) NOT NULL)";

	        String createComics = "CREATE TABLE IF NOT EXISTS comics ("
	                + "ID INT AUTO_INCREMENT PRIMARY KEY,"
	                + "Title VARCHAR(50) NOT NULL,"
	                + "Author VARCHAR(50) NOT NULL,"
	                + "Genre VARCHAR(50) NOT NULL,"
	                + "Date VARCHAR(10) NOT NULL,"
	                + "Publisher VARCHAR(20) NOT NULL)";

	        String createThesises = "CREATE TABLE IF NOT EXISTS theses ("
	                + "ID INT AUTO_INCREMENT PRIMARY KEY,"
	                + "Title VARCHAR(50) NOT NULL,"
	                + "Author VARCHAR(50) NOT NULL,"
	                + "Genre VARCHAR(50) NOT NULL,"
	                + "Date VARCHAR(10) NOT NULL,"
	                + "Publisher VARCHAR(20) NOT NULL)";

	        st.executeUpdate(createBooks);
	        st.executeUpdate(createMagazines);
	        st.executeUpdate(createComics);
	        st.executeUpdate(createThesises);
	        System.out.println("Create Tables successfully!");
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(st != null)
				st.close();
				if(conn != null)
				conn.close();
			}  catch(SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
