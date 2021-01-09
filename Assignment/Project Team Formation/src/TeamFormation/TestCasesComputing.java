package TeamFormation;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashMap;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class TestCasesComputing {

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
		HashMap<String, Student> members = new HashMap<String, Student>();
		members.put("S1", tf.students.get("S1"));
		members.put("S2", tf.students.get("S2"));
		members.put("S20", tf.students.get("S20"));
		members.put("S10", tf.students.get("S10"));
		tf.team.put("Pr1", new TeamMember("Pr1", members));

		members = new HashMap<String, Student>();
		members.put("S5", tf.students.get("S5"));
		members.put("S3", tf.students.get("S3"));
		members.put("S6", tf.students.get("S6"));
		members.put("S8", tf.students.get("S8"));
		tf.team.put("Pr2", new TeamMember("Pr2", members));

		members = new HashMap<String, Student>();
		members.put("S17", tf.students.get("S17"));
		members.put("S19", tf.students.get("S19"));
		members.put("S16", tf.students.get("S16"));
		members.put("S11", tf.students.get("S11"));
		tf.team.put("Pr3", new TeamMember("Pr3", members));

		members = new HashMap<String, Student>();
		members.put("S13", tf.students.get("S13"));
		members.put("S4", tf.students.get("S4"));
		members.put("S15", tf.students.get("S15"));
		members.put("S18", tf.students.get("S18"));
		tf.team.put("Pr4", new TeamMember("Pr4", members));

		members = new HashMap<String, Student>();
		members.put("S9", tf.students.get("S9"));
		members.put("S7", tf.students.get("S7"));
		members.put("S14", tf.students.get("S14"));
		members.put("S12", tf.students.get("S12"));
		tf.team.put("Pr5", new TeamMember("Pr5", members));
	}

	@Test
	public void testAverage() {
		ArrayList<String> storeProject = new ArrayList<String>();
		for (String s : tf.team.keySet()) {
			storeProject.add(s);
		}
		for (int i = 0; i < storeProject.size(); i++) {
			tf.team.get(storeProject.get(i)).CalculatePercentage();
			;
		}
		assertEquals("Valid result", 100, tf.team.get("Pr2").getPercentage(), 0.001);
	}

	@Test
	public void testCalculateStandardDeviationInSkillCompetency() {

		ArrayList<String> storeProject = new ArrayList<String>();
		for (String s : tf.team.keySet()) {
			storeProject.add(s);
		}

		for (int i = 0; i < storeProject.size(); i++) {
			tf.team.get(storeProject.get(i)).averageSkillCompetency();
		}

		for (int i = 0; i < storeProject.size(); i++) {
			tf.team.get(storeProject.get(i)).shortfall(tf.projects);
		}

		for (String projectID : tf.team.keySet()) {
			tf.team.get(projectID).calculateSDinSkillCompetency();
		}

		assertEquals("Valid result", 0.45069, tf.team.get("Pr4").getStandardDeviationInSkillCompetency(), 0.0001);

	}

	@Test
	public void testCalculateStandardDeviationForPercentage() {

		ArrayList<String> storeProject = new ArrayList<String>();
		for (String s : tf.team.keySet()) {
			storeProject.add(s);
		}

		for (int i = 0; i < storeProject.size(); i++) {
			tf.team.get(storeProject.get(i)).CalculatePercentage();
		}

		assertEquals("Valid result", 0.122, tf.calculateSDforPercentage(), 0.001);

	}

	@After
	public void tearDown() throws Exception {
	}

}
