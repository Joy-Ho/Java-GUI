package database;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class CreateTable {

	public static void createOwnerTable() throws SQLException {	
		final String DB_NAME = "database1.db";
		final String TABLE_NAME = "owner";
		
		try (Connection con = ConnectionTest.getConnection(DB_NAME);
				Statement stmt = con.createStatement();
		) {
			int result = stmt.executeUpdate("CREATE TABLE " + TABLE_NAME + " ("
										+ "firstName VARCHAR(20) NOT NULL,"
										+ "surName VARCHAR(20) NOT NULL," 
										+ "ownerID VARCHAR(8) NOT NULL,"
										+ "role VARCHAR(20) NOT NULL,"
										+ "email VARCHAR(50) NOT NULL,"
										+ "companyID VARCHAR(8) NOT NULL,"
										+ "PRIMARY KEY (ownerID))");
			if(result == 0) {
				System.out.println("Table " + TABLE_NAME + " is created");
			} else {
				System.out.println("Table " + TABLE_NAME + " is not created");
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public static void createProjectTable() throws SQLException {	
		final String DB_NAME = "database1.db";
		final String TABLE_NAME = "project";
		
		try (Connection con = ConnectionTest.getConnection(DB_NAME);
				Statement stmt = con.createStatement();
		) {
			int result = stmt.executeUpdate("CREATE TABLE " + TABLE_NAME + " ("
										+ "title VARCHAR(60) NOT NULL,"
										+ "projectID VARCHAR(8) NOT NULL," 
										+ "description VARCHAR(100) NOT NULL,"
										+ "ownerID VARCHAR(8) NOT NULL,"
										+ "ranking_W VARCHAR(2) NOT NULL,"
										+ "ranking_P VARCHAR(2) NOT NULL,"
										+ "ranking_N VARCHAR(2) NOT NULL,"
										+ "ranking_A VARCHAR(2) NOT NULL,"
										+ "PRIMARY KEY (projectID))");
			if(result == 0) {
				System.out.println("Table " + TABLE_NAME + " is created");
			} else {
				System.out.println("Table " + TABLE_NAME + " is not created");
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public static void createStudentTable() throws SQLException {	
		final String DB_NAME = "database1.db";
		final String TABLE_NAME = "student";
		
		try (Connection con = ConnectionTest.getConnection(DB_NAME);
				Statement stmt = con.createStatement();
		) {
			int result = stmt.executeUpdate("CREATE TABLE " + TABLE_NAME + " ("
										+ "studentID VARCHAR(10) NOT NULL,"
										+ "grade_P VARCHAR(2) NOT NULL," 
										+ "grade_N VARCHAR(2) NOT NULL,"
										+ "grade_A VARCHAR(2) NOT NULL,"
										+ "grade_W VARCHAR(2) NOT NULL,"
										+ "personalityType VARCHAR(2) NOT NULL,"
										+ "conflict1 VARCHAR(10) NOT NULL,"
										+ "conflict2 VARCHAR(10) NOT NULL,"
										+ "PRIMARY KEY (studentID))");
			if(result == 0) {
				System.out.println("Table " + TABLE_NAME + " is created");
			} else {
				System.out.println("Table " + TABLE_NAME + " is not created");
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public static void createTeamTable() throws SQLException {	
		final String DB_NAME = "database1.db";
		final String TABLE_NAME = "team";
		
		try (Connection con = ConnectionTest.getConnection(DB_NAME);
				Statement stmt = con.createStatement();
		) {
			int result = stmt.executeUpdate("CREATE TABLE " + TABLE_NAME + " ("
					                    + "teamID VARCHAR(8) NOT NULL,"
					                    + "projectID VARCHAR(8) NOT NULL,"
										+ "member1 VARCHAR(8) NOT NULL," 
										+ "member2 VARCHAR(8) NOT NULL,"
										+ "member3 VARCHAR(8) NOT NULL,"
										+ "member4 VARCHAR(8) NOT NULL,"
										+ "PRIMARY KEY (teamID))");
			if(result == 0) {
				System.out.println("Table " + TABLE_NAME + " is created");
			} else {
				System.out.println("Table " + TABLE_NAME + " is not created");
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}





}
