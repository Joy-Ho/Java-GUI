package database;

import java.sql.Connection;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

import TeamFormation.*;

import java.sql.*;

public class InsertMultipleRows {

	public static void insertOwner(HashMap<String, Owner> owners) {
		final String DB_NAME = "database1.db";
		final String TABLE_NAME = "owner";

		try (Connection con = ConnectionTest.getConnection(DB_NAME); Statement stmt = con.createStatement();) {
			String insertQuery = "INSERT INTO " + TABLE_NAME;
			for (String o : owners.keySet()) {
				String sql1 = owners.get(o).getFirstName();
				String sql2 = owners.get(o).getSurName();
				String sql3 = owners.get(o).getOwnerID();
				String sql4 = owners.get(o).getRole();
				String sql5 = owners.get(o).getEmail();
				String sql6 = owners.get(o).getCompanyID();

				stmt.addBatch(insertQuery + " VALUES ('" + sql1 + "', '" + sql2 + "', '" + sql3 + "','" + sql4 + "', '"
						+ sql5 + "', '" + sql6 + "')");

			}
			int count[] = stmt.executeBatch();

			System.out.println("Insert " + count.length + " row(s) into TABLE " + TABLE_NAME);

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public static void insertProject(HashMap<String, Project> projects) {
		final String DB_NAME = "database1.db";
		final String TABLE_NAME = "project";

		try (Connection con = ConnectionTest.getConnection(DB_NAME); Statement stmt = con.createStatement();) {
			String insertQuery = "INSERT INTO " + TABLE_NAME;

			for (String p : projects.keySet()) {
				String sql1 = projects.get(p).getTitle();
				String sql2 = projects.get(p).getProjectID();
				String sql3 = projects.get(p).getDescription();
				String sql4 = projects.get(p).getOwnerID();

				String sql5 = "", sql6 = "", sql7 = "", sql8 = "";
				HashMap<String, String> rankingSkill = projects.get(p).getRankingSkill();
				for (String r : rankingSkill.keySet()) {
					if (r.equals("W"))
						sql5 = projects.get(p).getRankingSkill().get("W");
					if (r.equals("P"))
						sql6 = projects.get(p).getRankingSkill().get("P");
					if (r.equals("N"))
						sql7 = projects.get(p).getRankingSkill().get("N");
					if (r.equals("A"))
						sql8 = projects.get(p).getRankingSkill().get("A");
				}

				stmt.addBatch(insertQuery + " VALUES ('" + sql1 + "', '" + sql2 + "', '" + sql3 + "','" + sql4 + "', '"
						+ sql5 + "', '" + sql6 + "', '" + sql7 + "', '" + sql8 + "')");

			}

			int count[] = stmt.executeBatch();

			System.out.println("Insert " + count.length + " row(s) into TABLE " + TABLE_NAME);

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public static void insertStudent(HashMap<String, Student> students) {
		final String DB_NAME = "database1.db";
		final String TABLE_NAME = "student";

		try (Connection con = ConnectionTest.getConnection(DB_NAME); Statement stmt = con.createStatement();) {
			String insertQuery = "INSERT INTO " + TABLE_NAME;

			for (String s : students.keySet()) {
				String sql1 = students.get(s).getStudentID();

				String sql2 = "", sql3 = "", sql4 = "", sql5 = "";
				HashMap<String, Integer> grade = students.get(s).getGrade();
				for (String g : grade.keySet()) {
					if (g.equals("P"))
						sql2 = Integer.toString(grade.get(g));
					if (g.equals("N"))
						sql3 = Integer.toString(grade.get(g));
					if (g.equals("A"))
						sql4 = Integer.toString(grade.get(g));
					if (g.equals("W"))
						sql5 = Integer.toString(grade.get(g));
				}

				String sql6 = students.get(s).getPersonalityType();

				String sql7 = "", sql8 = "";
				ArrayList<String> conflict = students.get(s).getConflict();
				sql7 = conflict.get(0);
				sql8 = conflict.get(1);

				stmt.addBatch(insertQuery + " VALUES ('" + sql1 + "', '" + sql2 + "', '" + sql3 + "','" + sql4 + "', '"
						+ sql5 + "', '" + sql6 + "', '" + sql7 + "', '" + sql8 + "')");

			}

			int count[] = stmt.executeBatch();

			System.out.println("Insert " + count.length + " row(s) into TABLE " + TABLE_NAME);

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public static void insertTeamMember(TeamMember team, String projectID) {
		final String DB_NAME = "database1.db";
		final String TABLE_NAME = "team";

		String teamID = "";
		if (projectID.equals("Pr1"))
			teamID = "Team1";
		if (projectID.equals("Pr2"))
			teamID = "Team2";
		if (projectID.equals("Pr3"))
			teamID = "Team3";
		if (projectID.equals("Pr4"))
			teamID = "Team4";
		if (projectID.equals("Pr5"))
			teamID = "Team5";

		try (Connection con = ConnectionTest.getConnection(DB_NAME); Statement stmt = con.createStatement();) {
			String insertQuery = "INSERT INTO " + TABLE_NAME;

			ArrayList<String> teamMember = new ArrayList<String>();
			for (String t : team.getMember().keySet()) {

				teamMember.add(t);
			}

			stmt.addBatch(insertQuery + " VALUES ('" + teamID + "', '" + projectID + "', '" + teamMember.get(0) + "','"
					+ teamMember.get(1) + "', '" + teamMember.get(2) + "', '" + teamMember.get(3) + "')");

			int count[] = stmt.executeBatch();

			System.out.println("Insert " + count.length + " row(s) into TABLE " + TABLE_NAME);

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

}