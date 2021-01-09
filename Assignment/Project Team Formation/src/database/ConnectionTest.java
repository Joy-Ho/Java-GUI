package database;

import java.sql.*;

public class ConnectionTest {

	public static void connection() {
		final String DB_NAME = "database1.db";

		try (Connection con = getConnection(DB_NAME)) {
			System.out.println("Connection to database " + DB_NAME + " created successfully");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public static Connection getConnection(String dbName) throws SQLException, ClassNotFoundException {
		String url = "jdbc:sqlite:database/" + dbName;
		Connection con = DriverManager.getConnection(url);
		return con;
	}
}
