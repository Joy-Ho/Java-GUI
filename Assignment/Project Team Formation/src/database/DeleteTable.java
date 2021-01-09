package database;

import java.sql.Connection;
import java.sql.Statement;

public class DeleteTable {
	public static void deleteTeamTable() {
		final String DB_NAME = "database1.db";
		final String TABLE_NAME = "team";

		try (Connection con = ConnectionTest.getConnection(DB_NAME); Statement stmt = con.createStatement();) {

			String query = "DROP TABLE IF EXISTS " + TABLE_NAME;

			int result = stmt.executeUpdate(query);

			System.out.println("DROP TABLE " + TABLE_NAME);

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public static void deleteOwnerTable() {
		final String DB_NAME = "database1.db";
		final String TABLE_NAME = "owner";

		try (Connection con = ConnectionTest.getConnection(DB_NAME); Statement stmt = con.createStatement();) {

			String query = "DROP TABLE IF EXISTS " + TABLE_NAME;

			int result = stmt.executeUpdate(query);

			System.out.println("DROP TABLE " + TABLE_NAME);
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public static void deleteProjectTable() {
		final String DB_NAME = "database1.db";
		final String TABLE_NAME = "project";

		try (Connection con = ConnectionTest.getConnection(DB_NAME); Statement stmt = con.createStatement();) {

			String query = "DROP TABLE IF EXISTS " + TABLE_NAME;

			int result = stmt.executeUpdate(query);

			System.out.println("DROP TABLE " + TABLE_NAME);

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public static void deleteStudentTable() {
		final String DB_NAME = "database1.db";
		final String TABLE_NAME = "student";

		try (Connection con = ConnectionTest.getConnection(DB_NAME); Statement stmt = con.createStatement();) {

			String query = "DROP TABLE IF EXISTS " + TABLE_NAME;

			int result = stmt.executeUpdate(query);

			System.out.println("DROP TABLE " + TABLE_NAME);

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

}
