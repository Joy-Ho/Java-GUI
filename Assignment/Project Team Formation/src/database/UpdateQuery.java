package database;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import TeamFormation.TeamMember;

public class UpdateQuery {
	public static void updateTeamMember(TeamMember team, String projectID) throws SQLException {	
		final String DB_NAME = "database1.db";
		final String TABLE_NAME = "team";
		
		try (Connection con = ConnectionTest.getConnection(DB_NAME);
				Statement stmt = con.createStatement();
		) {		
			ArrayList <String> teamMember = new ArrayList<String>();
			for (String t : team.getMember().keySet()) {	
				teamMember.add(t);
			}
			
			String query = "UPDATE " + TABLE_NAME +
					" SET member1 = '"+teamMember.get(0)+"',member2 = '"+teamMember.get(1)+"',member3 = '"+teamMember.get(2)+"',member4 = '"+teamMember.get(3)+"'"+
					" WHERE (projectID LIKE "+"'"+projectID+"') ";
			
			int result = stmt.executeUpdate(query);
			
			System.out.println("Update table " + TABLE_NAME + " executed successfully");
			System.out.println(result + " row(s) affected");

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
}