package TeamFormation;

import java.util.ArrayList;
import java.util.HashMap;

public class Student {

	private String studentID;
	private HashMap<String, Integer> grade = new HashMap<String, Integer>();
	private String personalityType;
	private ArrayList<String> conflict = new ArrayList<String>();
	private HashMap<String, Integer> preferences = new HashMap<String, Integer>();

	public Student(String studentID, HashMap<String, Integer> grade) {
		this.studentID = studentID;
		this.grade = grade;
	}

	public Student(String studentID, HashMap<String, Integer> grade, String type, ArrayList<String> conflicts) {
		this.studentID = studentID;
		this.grade = grade;
		this.personalityType = type;
		this.conflict = conflicts;

	}

	public String getStudentID() {
		return studentID;
	}

	public HashMap<String, Integer> getGrade() {
		return grade;
	}

	public String getPersonalityType() {
		return personalityType;
	}

	public ArrayList<String> getConflict() {
		return conflict;
	}

	public HashMap<String, Integer> getPreferences() {
		return preferences;
	}

	public String toString() {
		return new String(" StudentID:" + studentID + " Grade: " + grade + " PersonalityType: " + personalityType
				+ " Conflicts: " + conflict + " Preferences: " + preferences + "\n");
	}

	public void setPersonalityType(String type) {
		this.personalityType = type;

	}

	public void setConflict(ArrayList<String> conf) {
		this.conflict = conf;

	}

	public void setPreferences(HashMap<String, Integer> preference) {

		this.preferences = preference;

	}

}
