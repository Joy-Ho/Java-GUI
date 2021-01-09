package TeamFormation;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashMap;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class TestCasesException {
	
	HashMap<String, Student> students = new HashMap<String, Student>();
	HashMap<String, Company> companies = new HashMap<String, Company>();
	HashMap<String, Owner> owners = new HashMap<String, Owner>();
	HashMap<String, Project> projects = new HashMap<String, Project>();
	HashMap<String, TeamMember> team = new HashMap<String, TeamMember>();
	TeamFormation tf = new TeamFormation();
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		
		tf.readProject("projects.txt");
		tf.readStudentinfo("studentinfo.txt");	
		tf.setPreferences("preferences.txt");
		HashMap<String,Student> members = new HashMap<String,Student>();
		members.put("S1", tf.students.get("S1")); members.put("S2", tf.students.get("S2")); members.put("S20", tf.students.get("S20"));members.put("S10", tf.students.get("S10"));
		tf.team.put("Pr1", new TeamMember("Pr1",members));
		
		tf.team.put("Pr2", new TeamMember("Pr2"));
		tf.team.get("Pr2").addMember(tf.students.get("S5"));
		
	}
	

	@Test(expected = InvalidMemberException.class)
	public void InvalidMember() throws Exception {
		tf.team.get("Pr2").checkInvalidMember("S1", tf.team);
		fail("The student is already assigned to another project team.");
	}
	

	
	@Test(expected = StudentConflictException.class)
	public void StudentConflictException() throws Exception{
		tf.team.get("Pr2").checkStudentConflict(tf.students.get("S9"));
		fail("students members who have indicated prior conflicts are assigned to the same team");
	}
	

	
	@Test(expected = RepeatedMemberException.class)
	public void RepeatedMemberException() throws Exception{
		tf.team.get("Pr2").checkRepeatedMember("S5");
		fail("A student is added twice to the same team");
	}
	

	
	@Test(expected = PersonalityImbalanceException.class)
	public void PersonalityImbalanceException() throws Exception{
		tf.team.get("Pr2").addMember(tf.students.get("S3"));
		tf.team.get("Pr2").addMember(tf.students.get("S13"));
		
		tf.team.get("Pr2").checkPersonalityIsBalance(tf.students.get("S7"), "type2");
		fail("A team has less than three different personality types");
	}
	

	
	@Test(expected = NoLeaderException.class)
	public void NoLeaderException() throws Exception{
		tf.team.put("Pr3", new TeamMember("Pr3"));
		tf.team.get("Pr3").addMember(tf.students.get("S19"));
		tf.team.get("Pr3").addMember(tf.students.get("S16"));
		tf.team.get("Pr3").addMember(tf.students.get("S11"));
		tf.team.get("Pr3").addMember(tf.students.get("S7"));
		
		tf.team.get("Pr3").checkNoLeader();
		fail("A team does not have a leader personality type.");
	}
	

	
	
	@After
	public void tearDown() throws Exception {
	}

	

}
